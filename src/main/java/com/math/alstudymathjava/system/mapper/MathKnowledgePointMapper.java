package com.math.alstudymathjava.system.mapper;


import com.math.alstudymathjava.system.entity.math.MathKnowledgePoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MathKnowledgePointMapper {

    /**
     * 新增知识点
     */
    int insert(MathKnowledgePoint knowledgePoint);

    /**
     * 更新知识点
     */
    int update(MathKnowledgePoint knowledgePoint);

    /**
     * 根据ID删除知识点
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据ID查询知识点
     */
    MathKnowledgePoint selectById(@Param("id") Long id);

    /**
     * 查询所有知识点，用于构建树
     */
    List<MathKnowledgePoint> selectAll();

    /**
     * 根据父ID查询子知识点
     */
    List<MathKnowledgePoint> selectByParentId(@Param("parentId") Long parentId);

    /**
     * 根据题目ID查询关联的知识点
     */
    List<MathKnowledgePoint> selectKpsByQuestionId(@Param("questionId") Long questionId);

    /**
     * 新增题目-知识点关联关系
     */
    int insertQuestionKpRelation(@Param("questionId") Long questionId,
                                 @Param("kpId") Long kpId);

    /**
     * 删除某题目的所有知识点关联关系
     */
    int deleteQuestionKpRelationByQuestionId(@Param("questionId") Long questionId);
}