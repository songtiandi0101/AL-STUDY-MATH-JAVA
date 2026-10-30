package com.math.alstudymathjava.system.service.impl;


import com.math.alstudymathjava.system.entity.math.MathKnowledgePoint;
import com.math.alstudymathjava.system.mapper.MathKnowledgePointMapper;
import com.math.alstudymathjava.system.service.MathKnowledgePointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.Resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MathKnowledgePointServiceImpl implements MathKnowledgePointService {

    @Resource
    private MathKnowledgePointMapper mathKnowledgePointMapper;

    // 假设你有一个专门处理 题目-知识点 关联关系的 Mapper
    // @Resource
    // private MathQuestionKpRelationMapper relationMapper;

    @Override
    public int addKnowledgePoint(MathKnowledgePoint knowledgePoint) {
        // 根据之前的表结构，知识点表只有 create_time
        knowledgePoint.setCreateTime(LocalDateTime.now());
        return mathKnowledgePointMapper.insert(knowledgePoint);
    }

    @Override
    public int updateKnowledgePoint(MathKnowledgePoint knowledgePoint) {
        // 知识点表结构中没有 update_time，直接更新
        return mathKnowledgePointMapper.update(knowledgePoint);
    }

    @Override
    public int deleteKnowledgePoint(Long id) {
        // 1. 校验是否存在子节点
        List<MathKnowledgePoint> children = mathKnowledgePointMapper.selectByParentId(id);
        if (children != null && !children.isEmpty()) {
            throw new RuntimeException("该知识点下存在子节点，无法直接删除"); // 或者抛出自定义的业务异常
        }

        // 2. 校验是否有题目关联了该知识点（可选，视业务严谨度而定）
        // int count = relationMapper.countByKpId(id);
        // if (count > 0) { throw new RuntimeException("已有题目关联该知识点，无法删除"); }

        // 3. 执行删除
        return mathKnowledgePointMapper.deleteById(id);
    }

    @Override
    public MathKnowledgePoint getKnowledgePointById(Long id) {
        return mathKnowledgePointMapper.selectById(id);
    }

    @Override
    public List<MathKnowledgePoint> getKnowledgePointTree() {
        // 查询所有未删除的知识点（扁平列表）
        List<MathKnowledgePoint> flatList = mathKnowledgePointMapper.selectAll();
        // 构建树形结构，从顶级节点（parent_id = 0）开始
        return buildTree(flatList, 0L);
    }

    @Override
    public List<MathKnowledgePoint> getChildrenByParentId(Long parentId) {
        return mathKnowledgePointMapper.selectByParentId(parentId);
    }

    @Override
    public List<MathKnowledgePoint> getKnowledgePointsByQuestionId(Long questionId) {
        // 这是一个连表查询：通过题目ID去关联表查知识点ID，再查出知识点详情
        return mathKnowledgePointMapper.selectKpsByQuestionId(questionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int bindQuestionKnowledgePoints(Long questionId, List<Long> kpIds) {
        // 1. 先解绑该题目原有的所有知识点（全量覆盖逻辑）
        unbindQuestionKnowledgePoints(questionId);

        // 2. 如果传入的新知识点列表不为空，则进行批量插入
        if (kpIds != null && !kpIds.isEmpty()) {
            // 这里假设有一个插入关联关系的方法，返回插入的行数
            // return relationMapper.batchInsert(questionId, kpIds);

            // 如果没有独立的 relationMapper，可能需要循环插入或者在 XML 里写 foreach
            int rows = 0;
            for (Long kpId : kpIds) {
                rows += mathKnowledgePointMapper.insertQuestionKpRelation(questionId, kpId);
            }
            return rows;
        }
        return 0;
    }

    @Override
    public int unbindQuestionKnowledgePoints(Long questionId) {
        // 删除关联表中该题目的所有记录
        return mathKnowledgePointMapper.deleteQuestionKpRelationByQuestionId(questionId);
    }

    // ==================== ⭐ 核心工具方法：构建树 ====================

    /**
     * 递归构建树形结构 (完美复用你提供的 SysMenuServiceImpl 的思路)
     * @param allKps 所有的知识点扁平列表
     * @param parentId 当前需要查找的父节点ID
     * @return 带有 children 的树形列表
     */
    private List<MathKnowledgePoint> buildTree(List<MathKnowledgePoint> allKps, Long parentId) {
        List<MathKnowledgePoint> treeList = new ArrayList<>();
        for (MathKnowledgePoint kp : allKps) {
            // 注意：这里要防止 parentId 为空引发空指针异常
            if (kp.getParentId() != null && kp.getParentId().equals(parentId)) {
                // 递归查找当前节点的子节点
                List<MathKnowledgePoint> children = buildTree(allKps, kp.getId());
                kp.setChildren(children);
                treeList.add(kp);
            }
        }
        return treeList;
    }
}