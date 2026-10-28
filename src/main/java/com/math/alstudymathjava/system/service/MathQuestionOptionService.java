package com.math.alstudymathjava.system.service;


import com.math.alstudymathjava.system.entity.math.MathQuestionOption;

import java.util.List;

/**
 * 选择题选项业务接口
 */
public interface MathQuestionOptionService {

    /**
     * 新增单个选项
     * @param option 选项实体
     * @return 影响行数
     */
    int addOption(MathQuestionOption option);

    /**
     * 批量新增选项（通常配合题目新增时使用）
     * @param options 选项列表
     * @return 影响行数
     */
    int addBatchOptions(List<MathQuestionOption> options);

    /**
     * 根据题目ID查询该题的所有选项（按 sortOrder 排序）
     * @param questionId 题目ID
     * @return 选项列表
     */
    List<MathQuestionOption> getOptionsByQuestionId(Long questionId);

    /**
     * 更新单个选项信息
     * @param option 选项实体
     * @return 影响行数
     */
    int updateOption(MathQuestionOption option);

    /**
     * 根据题目ID物理删除该题下的所有选项（通常在更新或删除题目时，先删后插）
     * @param questionId 题目ID
     * @return 影响行数
     */
    int deleteOptionsByQuestionId(Long questionId);

    /**
     * 根据选项ID删除单个选项
     * @param id 选项ID
     * @return 影响行数
     */
    int deleteOptionById(Long id);
}