package com.math.alstudymathjava.system.mapper;


import com.math.alstudymathjava.system.entity.math.MathQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MathQuestionMapper {

    /**
     * 新增题目
     */
    int insert(MathQuestion question);

    /**
     * 更新题目
     */
    int update(MathQuestion question);

    /**
     * 根据ID删除题目
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据ID列表批量删除题目
     */
    int deleteBatchByIds(@Param("ids") List<Long> ids);

    /**
     * 根据ID查询题目
     */
    MathQuestion selectById(@Param("id") Long id);

    /**
     * 分页查询题目列表
     */
    List<MathQuestion> selectPage(MathQuestion query);

    /**
     * 统计分页查询的题目总数
     */
    long countPage(MathQuestion query);
}
