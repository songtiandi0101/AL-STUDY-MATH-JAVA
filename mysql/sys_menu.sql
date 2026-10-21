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

 Date: 21/10/2026 18:32:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由地址',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '图标',
  `sort` int NULL DEFAULT 0 COMMENT '显示顺序',
  `visible` int NULL DEFAULT 1 COMMENT '是否可见 (1可见 0隐藏)',
  `type` int NULL DEFAULT 1 COMMENT '类型 (1目录 2页面)',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志 (0存在 1删除)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '/system', 'Setting', 1, 1, 1, 0, '2026-10-10 09:13:46', '2026-10-10 09:48:26');
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', '/system/user', 'User', 1, 1, 2, 0, '2026-10-10 09:13:46', '2026-10-10 09:48:29');
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', '/system/role', 'UserFilled', 2, 1, 2, 0, '2026-10-10 09:13:46', '2026-10-10 09:48:34');
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', '/system/menu', 'Menu', 3, 1, 2, 0, '2026-10-13 09:47:26', '2026-10-13 09:47:26');
INSERT INTO `sys_menu` VALUES (6, 0, '题库中心', '/questions', '', 0, 1, 1, 0, '2026-10-21 09:22:42', '2026-10-21 09:22:42');
INSERT INTO `sys_menu` VALUES (7, 0, '题库中心', '/questions', '', 0, 1, 1, 0, '2026-10-21 10:00:06', '2026-10-21 10:00:06');
INSERT INTO `sys_menu` VALUES (8, 7, '习题导入', '/questions/import', '', 0, 1, 2, 0, '2026-10-21 10:01:13', '2026-10-21 10:01:13');

SET FOREIGN_KEY_CHECKS = 1;
