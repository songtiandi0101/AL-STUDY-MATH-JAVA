package com.math.alstudymathjava.system.service;


import com.math.alstudymathjava.system.entity.math.MathKnowledgePoint;

import java.util.List;

/**
 * 数学知识点业务接口
 */
public interface MathKnowledgePointService {

    /**
     * 新增知识点
     * @param knowledgePoint 知识点实体
     * @return 影响行数
     */
    int addKnowledgePoint(MathKnowledgePoint knowledgePoint);

    /**
     * 更新知识点信息
     * @param knowledgePoint 知识点实体
     * @return 影响行数
     */
    int updateKnowledgePoint(MathKnowledgePoint knowledgePoint);

    /**
     * 删除知识点（通常需要校验是否存在子节点，或是否存在题目关联）
     * @param id 知识点ID
     * @return 影响行数
     */
    int deleteKnowledgePoint(Long id);

    /**
     * 根据ID查询知识点详情
     * @param id 知识点ID
     * @return 知识点详情
     */
    MathKnowledgePoint getKnowledgePointById(Long id);

    // ==================== ⭐ 核心：树形与列表查询 ====================

    /**
     * 获取完整的知识点树形结构（返回顶级节点，内部包含嵌套的 children）
     * @return 树形结构列表
     */
    List<MathKnowledgePoint> getKnowledgePointTree();

    /**
     * 根据父级ID获取直接子节点列表（懒加载模式）
     * @param parentId 父级ID（0代表顶级）
     * @return 子节点列表
     */
    List<MathKnowledgePoint> getChildrenByParentId(Long parentId);

    /**
     * 根据题目ID查询其关联的所有知识点
     * @param questionId 题目ID
     * @return 知识点列表
     */
    List<MathKnowledgePoint> getKnowledgePointsByQuestionId(Long questionId);

    /**
     * 批量绑定题目与知识点（常用于新增或更新题目时操作）
     * @param questionId 题目ID
     * @param kpIds 知识点ID列表
     * @return 影响行数
     */
    int bindQuestionKnowledgePoints(Long questionId, List<Long> kpIds);

    /**
     * 解绑题目下的所有知识点
     * @param questionId 题目ID
     * @return 影响行数
     */
    int unbindQuestionKnowledgePoints(Long questionId);
}