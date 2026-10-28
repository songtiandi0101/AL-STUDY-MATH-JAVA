package com.math.alstudymathjava.system.entity.math;

import lombok.Data;

/**
 * 题目与知识点关联实体类
 */
@Data
public class MathQuestionKpRelation {
    /**
     * 关联主键ID
     */
    private Long id;

    /**
     * 关联的题目ID
     */
    private Long questionId;

    /**
     * 关联的知识点ID
     */
    private Long kpId;
}