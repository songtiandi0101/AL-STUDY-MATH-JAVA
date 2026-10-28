package com.math.alstudymathjava.system.service;


import com.math.alstudymathjava.system.entity.math.MathQuestionKpRelation;

import java.util.List;

/**
 * 题目与知识点关联业务接口
 */
public interface MathQuestionKpRelationService {

    /**
     * 批量绑定题目与知识点关联
     * （通常在新增或更新题目时调用）
     * @param questionId 题目ID
     * @param kpIds 知识点ID列表
     * @return 影响行数
     */
    int batchBindRelations(Long questionId, List<Long> kpIds);

    /**
     * 根据题目ID解除该题目的所有知识点关联
     * （通常在更新题目（先删后插）或删除题目时调用）
     * @param questionId 题目ID
     * @return 影响行数
     */
    int unbindRelationsByQuestionId(Long questionId);

    /**
     * 根据知识点ID解除该知识点与所有题目的关联
     * （通常在删除知识点时，需要校验或清理关联数据）
     * @param kpId 知识点ID
     * @return 影响行数
     */
    int unbindRelationsByKpId(Long kpId);

    /**
     * 根据题目ID查询该题目关联的所有知识点ID列表
     * @param questionId 题目ID
     * @return 知识点ID列表
     */
    List<Long> getKpIdsByQuestionId(Long questionId);

    /**
     * 根据知识点ID查询关联的所有题目数量
     * （用于在删除知识点前校验是否还有题目在使用）
     * @param kpId 知识点ID
     * @return 关联的题目数量
     */
    int countQuestionsByKpId(Long kpId);
}