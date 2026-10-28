/*
 Navicat Premium Dump SQL

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80046 (8.0.46)
 Source Host           : localhost:3306
 Source Schema         : al_study_math

 Target Server Type    : MySQL
 Target Server Version : 80046 (8.0.46)
 File Encoding         : 65001

 Date: 28/10/2026 18:17:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for math_question
-- ----------------------------
DROP TABLE IF EXISTS `math_question`;
CREATE TABLE `math_question`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `question_type` tinyint NOT NULL COMMENT '题型: 1单选, 2多选, 3填空, 4解答, 5判断',
  `grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学段年级',
  `difficulty` tinyint NULL DEFAULT 3 COMMENT '难度: 1-5星',
  `has_image` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否有图: 0无图, 1有图',
  `image_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MinIO对象路径',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '题干内容(支持Markdown/LaTeX)',
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '标准答案',
  `analysis` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '答案解析步骤',
  `source` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '题目来源',
  `is_published` tinyint(1) NOT NULL DEFAULT 0 COMMENT '发布状态: 0草稿, 1已发布',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_type`(`question_type` ASC) USING BTREE,
  INDEX `idx_grade`(`grade` ASC) USING BTREE,
  INDEX `idx_published`(`is_published` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数学题目主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of math_question
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
