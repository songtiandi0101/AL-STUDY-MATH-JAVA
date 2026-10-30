    package com.math.alstudymathjava.system.entity.math;


    import lombok.Data;
    import java.time.LocalDateTime;
    import java.util.List;

    /**
     * 数学知识点实体类
     */
    @Data
    public class MathKnowledgePoint {
        /**
         * 知识点主键ID
         */
        private Long id;

        /**
         * 父级ID（0为顶级）
         */
        private Long parentId;

        /**
         * 知识点名称
         */
        private String name;

        /**
         * 层级（1, 2, 3...）
         */
        private Integer level;

        /**
         * 排序号
         */
        private Integer sortOrder;

        /**
         * 创建时间
         */
        private LocalDateTime createTime;

        // ========== ⭐ 新增：树形结构子节点（数据库无对应字段，用于构建树形结构）==========
        /**
         * 子级知识点列表
         */
        private List<MathKnowledgePoint> children;
    }
