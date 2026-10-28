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

 Date: 28/10/2026 18:17:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for math_question_kp_relation
-- ----------------------------
DROP TABLE IF EXISTS `math_question_kp_relation`;
CREATE TABLE `math_question_kp_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` bigint NOT NULL COMMENT '题目ID',
  `kp_id` bigint NOT NULL COMMENT '知识点ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_q_kp`(`question_id` ASC, `kp_id` ASC) USING BTREE COMMENT '防止重复关联'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目与知识点关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of math_question_kp_relation
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
