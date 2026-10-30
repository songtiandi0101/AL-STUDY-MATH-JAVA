package com.math.alstudymathjava.system.mapper;



import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目与知识点关联 Mapper
 * 对应表：math_question_kp_relation
 */
public interface MathQuestionKpRelationMapper {

    /**
     * 批量插入题目-知识点关联
     *
     * @param questionId 题目ID
     * @param kpIds      知识点ID列表
     * @return 影响行数
     */
    int batchInsert(@Param("questionId") Long questionId,
                    @Param("kpIds") List<Long> kpIds);

    /**
     * 根据题目ID删除所有关联
     *
     * @param questionId 题目ID
     * @return 影响行数
     */
    int deleteByQuestionId(@Param("questionId") Long questionId);

    /**
     * 根据知识点ID删除所有关联
     *
     * @param kpId 知识点ID
     * @return 影响行数
     */
    int deleteByKpId(@Param("kpId") Long kpId);

    /**
     * 根据题目ID查询关联的知识点ID列表
     *
     * @param questionId 题目ID
     * @return 知识点ID列表
     */
    List<Long> selectKpIdsByQuestionId(@Param("questionId") Long questionId);

    /**
     * 根据知识点ID统计关联的题目数量
     *
     * @param kpId 知识点ID
     * @return 题目数量
     */
    int countQuestionsByKpId(@Param("kpId") Long kpId);
}