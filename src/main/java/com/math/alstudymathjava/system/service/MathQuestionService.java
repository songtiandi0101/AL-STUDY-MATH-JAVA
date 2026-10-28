package com.math.alstudymathjava.system.service;


import com.math.alstudymathjava.system.entity.math.MathQuestion;
import com.math.alstudymathjava.system.entity.user.PageResult;
import java.util.List;

/**
 * 数学题目业务接口
 */
public interface MathQuestionService {

    /**
     * 新增题目（包含选项和知识点关联）
     * @param mathQuestion 题目实体（内含 options 和 kpIds）
     * @return 影响行数
     */
    int addQuestion(MathQuestion mathQuestion);

    /**
     * 根据ID查询题目详情（包含选项和知识点详细信息，用于前端回显/预览）
     * @param id 题目ID
     * @return 题目完整信息
     */
    MathQuestion getQuestionById(Long id);

    /**
     * 更新题目（包含选项和知识点关联的覆盖更新）
     * @param mathQuestion 题目实体
     * @return 影响行数
     */
    int updateQuestion(MathQuestion mathQuestion);

    /**
     * 物理删除题目（根据你的需求，直接从数据库 DELETE）
     * 注意：需级联删除关联的选项表和知识点关联表数据
     * @param id 题目ID
     * @return 影响行数
     */
    int deleteQuestion(Long id);

    /**
     * 批量物理删除题目
     * @param ids 题目ID列表
     * @return 影响行数
     */
    int deleteBatchQuestions(List<Long> ids);

    // ==================== ⭐ 核心：分页与列表查询 ====================

    /**
     * 分页查询题目列表（支持按年级、题型、难度、关键字模糊搜索等）
     * @param query 查询参数封装体
     * @return 分页结果
     */
    PageResult<MathQuestion> getQuestionPageList(MathQuestion query);

    /**
     * 修改题目发布状态（上架/下架）
     * @param id 题目ID
     * @param isPublished 发布状态：0草稿, 1已发布
     * @return 影响行数
     */
    int updatePublishStatus(Long id, Integer isPublished);
}