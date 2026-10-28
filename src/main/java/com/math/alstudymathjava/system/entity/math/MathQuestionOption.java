package com.math.alstudymathjava.system.entity.math;


import lombok.Data;

/**
 * 选择题选项实体类
 */
@Data
public class MathQuestionOption {
    /**
     * 选项主键ID
     */
    private Long id;

    /**
     * 关联的题目ID
     */
    private Long questionId;

    /**
     * 选项标签（A, B, C, D等）
     */
    private String optionLabel;

    /**
     * 选项内容（支持LaTeX）
     */
    private String optionContent;

    /**
     * 是否正确：0错误, 1正确
     */
    private Integer isCorrect;

    /**
     * 排序号
     */
    private Integer sortOrder;
}