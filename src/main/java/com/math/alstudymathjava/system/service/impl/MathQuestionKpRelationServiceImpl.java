package com.math.alstudymathjava.system.service.impl;


import com.math.alstudymathjava.system.mapper.MathQuestionKpRelationMapper;
import com.math.alstudymathjava.system.service.MathQuestionKpRelationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * 题目与知识点关联业务实现
 */
@Service
public class MathQuestionKpRelationServiceImpl implements MathQuestionKpRelationService {

    @Resource
    private MathQuestionKpRelationMapper relationMapper;

    /**
     * 批量绑定题目与知识点关联
     * 采用「先删后插」的全量覆盖逻辑，保证幂等
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchBindRelations(Long questionId, List<Long> kpIds) {
        if (questionId == null) {
            throw new IllegalArgumentException("题目ID不能为空");
        }

        // 1. 先清除该题目原有的所有关联
        relationMapper.deleteByQuestionId(questionId);

        // 2. 如果新列表为空，则只完成解绑
        if (kpIds == null || kpIds.isEmpty()) {
            return 0;
        }

        // 3. 批量插入新的关联
        return relationMapper.batchInsert(questionId, kpIds);
    }

    /**
     * 根据题目ID解除该题目的所有知识点关联
     */
    @Override
    public int unbindRelationsByQuestionId(Long questionId) {
        if (questionId == null) {
            return 0;
        }
        return relationMapper.deleteByQuestionId(questionId);
    }

    /**
     * 根据知识点ID解除该知识点与所有题目的关联
     */
    @Override
    public int unbindRelationsByKpId(Long kpId) {
        if (kpId == null) {
            return 0;
        }
        return relationMapper.deleteByKpId(kpId);
    }

    /**
     * 根据题目ID查询该题目关联的所有知识点ID列表
     */
    @Override
    public List<Long> getKpIdsByQuestionId(Long questionId) {
        if (questionId == null) {
            return Collections.emptyList();
        }
        return relationMapper.selectKpIdsByQuestionId(questionId);
    }

    /**
     * 根据知识点ID查询关联的所有题目数量
     */
    @Override
    public int countQuestionsByKpId(Long kpId) {
        if (kpId == null) {
            return 0;
        }
        return relationMapper.countQuestionsByKpId(kpId);
    }
}
