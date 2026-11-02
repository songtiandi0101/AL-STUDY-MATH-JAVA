package com.math.alstudymathjava.system.mapper;


import com.math.alstudymathjava.system.entity.math.MathQuestionOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 选择题选项 Mapper
 * 对应表：math_question_option
 */
public interface MathQuestionOptionMapper {

    /**
     * 新增单个选项
     */
    int insert(MathQuestionOption option);

    /**
     * 批量新增选项
     */
    int batchInsert(@Param("list") List<MathQuestionOption> list);

    /**
     * 更新单个选项
     */
    int update(MathQuestionOption option);

    /**
     * 根据ID删除单个选项
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据题目ID删除该题目的所有选项
     */
    int deleteByQuestionId(@Param("questionId") Long questionId);

    /**
     * 根据题目ID查询该题目的所有选项（按 sortOrder 排序）
     */
    List<MathQuestionOption> selectByQuestionId(@Param("questionId") Long questionId);
}
