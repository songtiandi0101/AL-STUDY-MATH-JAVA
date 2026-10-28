package com.math.alstudymathjava.system.entity.math;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 数学题目实体类
 */
@Data
public class MathQuestion {
    /**
     * 题目主键ID
     */
    private Long id;

    /**
     * 题型：1单选, 2多选, 3填空, 4解答, 5判断
     */
    private Integer questionType;

    /**
     * 学段年级：如 七年级、高一
     */
    private String grade;

    /**
     * 难度：1-5星
     */
    private Integer difficulty;

    /**
     * 是否有图：0无图, 1有图
     */
    private Integer hasImage;

    /**
     * MinIO对象路径（当前版本不使用，保留字段）
     */
    private String imagePath;

    /**
     * 题干内容（支持Markdown/LaTeX）
     */
    private String content;

    /**
     * 标准答案
     */
    private String answer;

    /**
     * 答案解析步骤
     */
    private String analysis;

    /**
     * 题目来源
     */
    private String source;

    /**
     * 发布状态：0草稿, 1已发布
     */
    private Integer isPublished;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    // ========== 新增分页参数（MyBatis分页用，数据库无对应字段）==========
    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * limit起始偏移量，(pageNum-1)*pageSize，数据库无此字段
     */
    private Integer start;

    // ========== ⭐ 新增：关联数据列表（数据库无对应字段，用于连表查询或组装回显）==========

    /**
     * 题目包含的选项列表（针对选择题）
     */
    private List<MathQuestionOption> options;

    /**
     * 题目关联的知识点ID列表
     */
    private List<Long> kpIds;

    /**
     * 题目关联的知识点详细信息列表（用于查询回显）
     */
    private List<MathKnowledgePoint> knowledgePoints;
}
