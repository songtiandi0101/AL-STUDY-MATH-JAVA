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

 Date: 28/10/2026 18:18:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for math_question_option
-- ----------------------------
DROP TABLE IF EXISTS `math_question_option`;
CREATE TABLE `math_question_option`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `question_id` bigint NOT NULL COMMENT '关联题目ID',
  `option_label` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '选项标签(A, B, C, D...)',
  `option_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '选项内容(支持LaTeX)',
  `is_correct` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否正确: 0错误, 1正确',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_question_id`(`question_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '选择题选项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of math_question_option
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
