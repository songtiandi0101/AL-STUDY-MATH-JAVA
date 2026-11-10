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

 Date: 10/11/2026 18:28:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for math_knowledge_point
-- ----------------------------
DROP TABLE IF EXISTS `math_knowledge_point`;
CREATE TABLE `math_knowledge_point`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父级ID(0为顶级)',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '知识点名称',
  `level` tinyint NULL DEFAULT 1 COMMENT '层级(1, 2, 3...)',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序号',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数学知识点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of math_knowledge_point
-- ----------------------------
INSERT INTO `math_knowledge_point` VALUES (1, 0, '集合', 1, 1, '2026-11-10 10:59:49');
INSERT INTO `math_knowledge_point` VALUES (2, 1, '属于（∈）', 2, 1, '2026-11-10 11:06:40');
INSERT INTO `math_knowledge_point` VALUES (3, 1, '交集（∩）', 2, 1, '2026-11-10 15:43:46');
INSERT INTO `math_knowledge_point` VALUES (4, 1, '并集（∪）', 2, 1, '2026-11-10 15:44:21');
INSERT INTO `math_knowledge_point` VALUES (5, 1, '子集（⊆）', 2, 1, '2026-11-10 16:42:24');
INSERT INTO `math_knowledge_point` VALUES (6, 1, '真子集（⊊）', 1, 1, '2026-11-10 16:42:59');
INSERT INTO `math_knowledge_point` VALUES (7, 1, '全集（U）', 1, 1, '2026-11-10 16:59:25');
INSERT INTO `math_knowledge_point` VALUES (8, 1, '补集（∁）', 1, 1, '2026-11-10 17:04:54');

SET FOREIGN_KEY_CHECKS = 1;
