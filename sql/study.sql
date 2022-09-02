/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 02/09/2022 17:34:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blogId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `postTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`blogId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '我的第一篇博客', '一二三四五，六七八九十', 1, '2022-07-06 15:12:40');
INSERT INTO `blog` VALUES (2, '我的第二篇博客', '一二三四五，十九八七六', 1, '2022-07-06 15:12:40');
INSERT INTO `blog` VALUES (3, '我的第三篇博客', '一二三四五，六六六六六', 1, '2022-07-06 15:12:40');
INSERT INTO `blog` VALUES (4, '我的第一篇博客', '一二三四五，上山打老虎', 2, '2022-07-06 15:12:40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `indexName`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123');
INSERT INTO `user` VALUES (2, 'lisi', '123');

-- ----------------------------
-- Table structure for user_s
-- ----------------------------
DROP TABLE IF EXISTS `user_s`;
CREATE TABLE `user_s`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_s
-- ----------------------------
INSERT INTO `user_s` VALUES (1, 'user', '123', 'user', NULL);
INSERT INTO `user_s` VALUES (2, 'admin', '123', 'admin', NULL);

-- ----------------------------
-- Table structure for v_grade
-- ----------------------------
DROP TABLE IF EXISTS `v_grade`;
CREATE TABLE `v_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '年纪分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_grade
-- ----------------------------
INSERT INTO `v_grade` VALUES (1, '一年级（上）');
INSERT INTO `v_grade` VALUES (2, '一年级（下）');
INSERT INTO `v_grade` VALUES (3, '二年级（上）');
INSERT INTO `v_grade` VALUES (4, '二年级（下）');
INSERT INTO `v_grade` VALUES (5, '三年级（上）');
INSERT INTO `v_grade` VALUES (6, '三年级（下）');

-- ----------------------------
-- Table structure for v_link
-- ----------------------------
DROP TABLE IF EXISTS `v_link`;
CREATE TABLE `v_link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '显隐状态 0显示1隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_link
-- ----------------------------
INSERT INTO `v_link` VALUES (1, '测试', 'https', '2021-12-27 14:06:16', '张', '0');

-- ----------------------------
-- Table structure for v_menu
-- ----------------------------
DROP TABLE IF EXISTS `v_menu`;
CREATE TABLE `v_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `parent_id` int(11) NULL DEFAULT NULL,
  `level` int(12) NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'el-icon-menu',
  `show_flag` int(1) NULL DEFAULT 1 COMMENT '1菜单 2按钮',
  `sort` int(12) NULL DEFAULT 0 COMMENT '排序',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_menu
-- ----------------------------
INSERT INTO `v_menu` VALUES (1, '用户管理', 'user:all', -1, 1, NULL, 'user/', 'el-icon-user-solid', 1, 1, '2022-07-22 16:23:57', '2022-09-01 15:04:34', '0');
INSERT INTO `v_menu` VALUES (2, '教师列表', 'teacher:list', 1, 2, 'test', 'user/teacherInfo', 'el-icon-s-custom', 1, 1, '2022-07-22 16:24:16', '2022-09-01 15:04:40', '0');
INSERT INTO `v_menu` VALUES (3, '学生列表', 'student:list', 1, 2, NULL, 'user/studentInfo', 'el-icon-s-custom', 1, 2, '2022-07-22 16:24:28', '2022-09-01 15:04:41', '0');
INSERT INTO `v_menu` VALUES (4, '管理员列表', 'admin:list', 1, 2, NULL, 'user/adminInfo', 'el-icon-s-custom', 1, 3, '2022-07-22 16:24:33', '2022-09-01 15:04:43', '0');
INSERT INTO `v_menu` VALUES (5, '添加用户', 'teacher:add', 2, 3, '描述', '/', 'el-icon-menu', 2, 1, '2022-07-22 16:25:15', '2022-09-01 15:05:01', '0');
INSERT INTO `v_menu` VALUES (6, '修改', 'teacher:edit', 2, 3, 'xiugai', '--', 'el-icon-menu', 2, 2, '2022-07-22 16:25:17', '2022-09-01 15:05:02', '0');
INSERT INTO `v_menu` VALUES (7, '删除', 'teacher:del', 2, 3, NULL, NULL, 'el-icon-menu', 2, 3, '2022-07-22 16:25:19', '2022-09-01 15:05:02', '0');
INSERT INTO `v_menu` VALUES (8, '分配角色', 'teacher:roles', 2, 3, NULL, NULL, 'el-icon-menu', 2, 4, '2022-07-22 16:25:23', '2022-09-01 15:05:03', '0');
INSERT INTO `v_menu` VALUES (9, '添加用户', 'student:add', 3, 3, NULL, NULL, 'el-icon-menu', 2, 5, '2022-07-22 16:25:42', '2022-09-01 15:05:03', '0');
INSERT INTO `v_menu` VALUES (10, '修改', 'student:edit', 3, 3, NULL, NULL, 'el-icon-menu', 2, 6, '2022-07-22 16:25:42', '2022-09-01 15:05:04', '0');
INSERT INTO `v_menu` VALUES (11, '删除', 'student:del', 3, 3, NULL, NULL, 'el-icon-menu', 2, 7, '2022-07-22 16:25:42', '2022-09-01 15:05:04', '0');
INSERT INTO `v_menu` VALUES (12, '分配角色', 'student:roles', 3, 3, NULL, NULL, 'el-icon-menu', 2, 8, '2022-07-22 16:25:42', '2022-09-01 15:05:05', '0');
INSERT INTO `v_menu` VALUES (13, '添加用户', 'admin:add', 4, 3, NULL, NULL, 'el-icon-menu', 2, 9, '2022-07-22 16:25:47', '2022-09-01 15:05:06', '0');
INSERT INTO `v_menu` VALUES (14, '修改', 'admin:edit', 4, 3, NULL, NULL, 'el-icon-menu', 2, 10, '2022-07-22 16:25:47', '2022-09-01 15:05:07', '0');
INSERT INTO `v_menu` VALUES (15, '删除', 'admin:del', 4, 3, NULL, NULL, 'el-icon-menu', 2, 11, '2022-07-22 16:25:47', '2022-09-01 15:05:08', '0');
INSERT INTO `v_menu` VALUES (16, '分配角色', 'admin:roles', 4, 3, NULL, NULL, 'el-icon-menu', 2, 12, '2022-07-22 16:25:47', '2022-09-01 15:05:08', '0');
INSERT INTO `v_menu` VALUES (17, '系统管理', 'sys:all', -1, 1, NULL, 'system/', 'el-icon-s-tools', 1, 2, '2022-07-22 16:26:54', '2022-09-01 15:04:36', '0');
INSERT INTO `v_menu` VALUES (18, '角色列表', 'role:list', 17, 2, NULL, 'system/roleIndex', 'el-icon-s-custom', 1, 4, '2022-07-22 16:27:05', '2022-09-01 15:04:44', '0');
INSERT INTO `v_menu` VALUES (19, '菜单列表', 'menu:list', 17, 2, NULL, 'menu/menuInfo', 'el-icon-folder', 1, 5, '2022-07-22 16:27:08', '2022-09-01 16:34:30', '0');
INSERT INTO `v_menu` VALUES (20, 'demo列表', 'demo:list', 17, 2, 'ceshi', 'system/permissionDemo', 'el-icon-menu', 1, 6, '2022-07-22 16:27:12', '2022-09-01 15:04:46', '0');
INSERT INTO `v_menu` VALUES (21, '添加角色', 'role:add', 18, 3, NULL, NULL, 'el-icon-menu', 2, 13, '2022-07-22 16:29:04', '2022-09-01 15:05:09', '0');
INSERT INTO `v_menu` VALUES (22, '修改角色', 'role:edit', 18, 3, NULL, NULL, 'el-icon-menu', 2, 14, '2022-07-22 16:29:04', '2022-09-01 15:05:10', '0');
INSERT INTO `v_menu` VALUES (23, '删除角色', 'role:del', 18, 3, NULL, NULL, 'el-icon-menu', 2, 15, '2022-07-22 16:29:04', '2022-09-01 15:05:12', '0');
INSERT INTO `v_menu` VALUES (24, '分配权限', 'role:permission', 18, 3, NULL, NULL, 'el-icon-menu', 2, 16, '2022-07-22 16:29:04', '2022-09-01 15:05:13', '0');
INSERT INTO `v_menu` VALUES (25, '添加分类', 'menu:add', 19, 3, NULL, NULL, 'el-icon-menu', 2, 17, '2022-07-22 16:32:13', '2022-09-01 15:05:14', '0');
INSERT INTO `v_menu` VALUES (26, '新增', 'menu:addInfo', 19, 3, NULL, NULL, 'el-icon-menu', 2, 18, '2022-07-22 16:32:15', '2022-09-01 15:05:15', '0');
INSERT INTO `v_menu` VALUES (27, '修改', 'menu:edit', 19, 3, NULL, NULL, 'el-icon-menu', 2, 19, '2022-07-22 16:32:17', '2022-09-01 15:05:17', '0');
INSERT INTO `v_menu` VALUES (28, '删除', 'menu:del', 19, 3, NULL, NULL, 'el-icon-menu', 2, 20, '2022-07-22 16:32:22', '2022-09-01 15:05:19', '0');
INSERT INTO `v_menu` VALUES (31, 'demo三', 'dem', 20, 3, NULL, '/dem', 'el-icon-menu', 2, 23, '2022-08-31 09:50:06', '2022-09-01 15:05:21', '0');
INSERT INTO `v_menu` VALUES (32, '列表查询', 'student:select', 3, 3, NULL, 'user/studentInfo', 'el-icon-menu', 2, 24, '2022-08-31 16:04:56', '2022-09-01 15:05:23', '0');
INSERT INTO `v_menu` VALUES (33, '列表查询', 'teacher:select', 2, 3, NULL, 'demo', 'el-icon-menu', 2, 25, '2022-08-31 16:09:50', '2022-09-01 15:05:24', '0');
INSERT INTO `v_menu` VALUES (34, 'demo3333', 'demo33', 20, 3, NULL, 'demo33', 'el-icon-menu', 2, 26, '2022-08-31 16:53:34', '2022-09-01 15:05:26', '0');
INSERT INTO `v_menu` VALUES (42, 'demo2页面', 'demo2', 17, 2, 'demo2', 'system/permissionIndex', 'el-icon-menu', 1, 7, '2022-09-01 11:20:26', '2022-09-01 15:04:47', '0');
INSERT INTO `v_menu` VALUES (45, '学科管理', 'subject:all', -1, 1, '', 'subject/', 'el-icon-s-management', 1, 999, '2022-09-01 16:01:32', '2022-09-01 16:31:21', '0');
INSERT INTO `v_menu` VALUES (46, '学科列表', 'subject:list', 45, 2, NULL, 'subject/list', 'el-icon-notebook-2', 1, 999, '2022-09-01 16:02:59', '2022-09-01 16:36:12', '0');
INSERT INTO `v_menu` VALUES (47, '新增', 'subject:add', 46, 3, NULL, '/', 'el-icon-menu', 2, 999, '2022-09-01 16:07:09', NULL, '0');
INSERT INTO `v_menu` VALUES (48, '删除', 'subject:del', 46, 3, NULL, '/', 'el-icon-menu', 2, 999, '2022-09-01 16:08:55', NULL, '0');
INSERT INTO `v_menu` VALUES (49, '编辑', 'subject:edit', 46, 3, NULL, '/', 'el-icon-menu', 2, 999, '2022-09-01 16:12:34', NULL, '0');
INSERT INTO `v_menu` VALUES (50, '列表', 'subject:list', 46, 3, NULL, '/', 'el-icon-menu', 2, 999, '2022-09-01 16:16:36', '2022-09-01 16:16:53', '0');
INSERT INTO `v_menu` VALUES (51, '题库管理', 'topic:all', -1, 1, NULL, 'topic/', 'el-icon-s-order', 1, 999, '2022-09-01 16:23:23', '2022-09-01 16:32:20', '0');
INSERT INTO `v_menu` VALUES (52, '列表', 'topic:list', 51, 2, NULL, 'topic/list', 'el-icon-menu', 1, 999, '2022-09-01 16:24:21', NULL, '0');

-- ----------------------------
-- Table structure for v_role
-- ----------------------------
DROP TABLE IF EXISTS `v_role`;
CREATE TABLE `v_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_role
-- ----------------------------
INSERT INTO `v_role` VALUES (1, 'student', '学生', '2022-07-14 14:53:00', NULL, '0');
INSERT INTO `v_role` VALUES (2, 'teacher', '老师', '2022-07-14 14:53:00', NULL, '0');
INSERT INTO `v_role` VALUES (3, 'admin', '管理员', '2022-07-14 14:53:00', '2022-07-19 11:05:39', '0');

-- ----------------------------
-- Table structure for v_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `v_role_menu`;
CREATE TABLE `v_role_menu`  (
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_role_menu
-- ----------------------------
INSERT INTO `v_role_menu` VALUES (2, 33);
INSERT INTO `v_role_menu` VALUES (2, 9);
INSERT INTO `v_role_menu` VALUES (2, 10);
INSERT INTO `v_role_menu` VALUES (2, 11);
INSERT INTO `v_role_menu` VALUES (2, 20);
INSERT INTO `v_role_menu` VALUES (2, 31);
INSERT INTO `v_role_menu` VALUES (3, 5);
INSERT INTO `v_role_menu` VALUES (3, 6);
INSERT INTO `v_role_menu` VALUES (3, 7);
INSERT INTO `v_role_menu` VALUES (3, 8);
INSERT INTO `v_role_menu` VALUES (3, 9);
INSERT INTO `v_role_menu` VALUES (3, 10);
INSERT INTO `v_role_menu` VALUES (3, 11);
INSERT INTO `v_role_menu` VALUES (3, 12);
INSERT INTO `v_role_menu` VALUES (3, 4);
INSERT INTO `v_role_menu` VALUES (3, 13);
INSERT INTO `v_role_menu` VALUES (3, 14);
INSERT INTO `v_role_menu` VALUES (3, 15);
INSERT INTO `v_role_menu` VALUES (3, 16);
INSERT INTO `v_role_menu` VALUES (3, 18);
INSERT INTO `v_role_menu` VALUES (3, 21);
INSERT INTO `v_role_menu` VALUES (3, 22);
INSERT INTO `v_role_menu` VALUES (3, 23);
INSERT INTO `v_role_menu` VALUES (3, 24);
INSERT INTO `v_role_menu` VALUES (3, 19);
INSERT INTO `v_role_menu` VALUES (3, 25);
INSERT INTO `v_role_menu` VALUES (3, 26);
INSERT INTO `v_role_menu` VALUES (3, 27);
INSERT INTO `v_role_menu` VALUES (3, 28);
INSERT INTO `v_role_menu` VALUES (3, 20);
INSERT INTO `v_role_menu` VALUES (3, 31);
INSERT INTO `v_role_menu` VALUES (3, 34);
INSERT INTO `v_role_menu` VALUES (3, 45);
INSERT INTO `v_role_menu` VALUES (3, 46);
INSERT INTO `v_role_menu` VALUES (3, 47);
INSERT INTO `v_role_menu` VALUES (3, 48);
INSERT INTO `v_role_menu` VALUES (3, 49);
INSERT INTO `v_role_menu` VALUES (3, 50);
INSERT INTO `v_role_menu` VALUES (3, 51);
INSERT INTO `v_role_menu` VALUES (3, 52);
INSERT INTO `v_role_menu` VALUES (3, 1);
INSERT INTO `v_role_menu` VALUES (3, 2);
INSERT INTO `v_role_menu` VALUES (3, 3);
INSERT INTO `v_role_menu` VALUES (3, 17);
INSERT INTO `v_role_menu` VALUES (1, 33);
INSERT INTO `v_role_menu` VALUES (1, 32);
INSERT INTO `v_role_menu` VALUES (1, 20);
INSERT INTO `v_role_menu` VALUES (1, 31);
INSERT INTO `v_role_menu` VALUES (1, 34);
INSERT INTO `v_role_menu` VALUES (1, 1);
INSERT INTO `v_role_menu` VALUES (1, 2);
INSERT INTO `v_role_menu` VALUES (1, 3);
INSERT INTO `v_role_menu` VALUES (1, 17);

-- ----------------------------
-- Table structure for v_subject
-- ----------------------------
DROP TABLE IF EXISTS `v_subject`;
CREATE TABLE `v_subject`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade_id` int(11) NULL DEFAULT NULL COMMENT '年级id',
  `grade_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年纪name',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学科表哦' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_subject
-- ----------------------------
INSERT INTO `v_subject` VALUES (1, '数学', 1, '一年级（上）', '2022-09-02 16:48:18', '2022-09-02 16:48:36', '0');
INSERT INTO `v_subject` VALUES (2, '语文', 1, '一年级（上）', '2022-09-02 16:48:22', '2022-09-02 16:48:36', '0');

-- ----------------------------
-- Table structure for v_user
-- ----------------------------
DROP TABLE IF EXISTS `v_user`;
CREATE TABLE `v_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `birth_day` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_level` int(11) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `last_active_time` timestamp(0) NULL DEFAULT NULL,
  `wx_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of v_user
-- ----------------------------
INSERT INTO `v_user` VALUES (1, NULL, 'student', '123', '学生', 18, 1, '2022-02-02', 1, '19171171610', 1, 'https://www.mindskip.net:9008/image/ba607a75-83ba-4530-8e23-660b72dc4953/头像.jpg', '2019-09-07 18:55:02', '2022-07-12 15:16:53', NULL, NULL, '0');
INSERT INTO `v_user` VALUES (2, NULL, 'admin', '123', '管理员', 44, 1, '2022-02-02 00:00:00.0', NULL, '17610183611', 2, NULL, '2019-09-07 18:56:21', '2022-07-12 14:17:34', NULL, NULL, '0');
INSERT INTO `v_user` VALUES (3, NULL, 'teacher', '123', '老师', 34, 2, '2022-02-02', NULL, NULL, 2, NULL, '2022-07-12 10:09:36', '2022-07-12 15:16:50', NULL, NULL, '0');
INSERT INTO `v_user` VALUES (21, NULL, 'test', '123456', '里斯', 33, 2, '2022-07-13 08:00:00.0', NULL, '33333', 2, NULL, '2022-07-13 09:11:44', '2022-07-13 13:52:43', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (23, NULL, 'test', '123', 'test', 12, 2, '2022-07-12 08:00:00.0', NULL, '44444', 1, NULL, '2022-07-14 10:41:14', '2022-07-14 14:11:34', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (24, NULL, 'd', 'd', '1', 1, 2, '2022-07-14 08:00:00.0', NULL, '1', 1, NULL, '2022-07-14 10:41:28', NULL, NULL, NULL, '0');
INSERT INTO `v_user` VALUES (25, NULL, '111', '123457', '11', 11, 2, '2022-07-14 08:00:00.0', NULL, '11111', 1, NULL, '2022-07-14 14:04:53', '2022-09-01 09:32:12', NULL, NULL, '0');
INSERT INTO `v_user` VALUES (26, NULL, '7', '7', '7', 7, 1, '2022-07-14 08:00:00.0', NULL, '7', 1, NULL, '2022-07-14 14:10:02', '2022-09-01 09:33:36', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (27, NULL, 'dd', 'dddddd', 'dd', NULL, 1, NULL, NULL, 'ddddd', 1, NULL, '2022-07-19 14:42:05', '2022-07-19 14:42:18', NULL, NULL, '1');

-- ----------------------------
-- Table structure for v_user_role
-- ----------------------------
DROP TABLE IF EXISTS `v_user_role`;
CREATE TABLE `v_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_user_role
-- ----------------------------
INSERT INTO `v_user_role` VALUES (2, 2, 3);
INSERT INTO `v_user_role` VALUES (11, 3, 2);
INSERT INTO `v_user_role` VALUES (22, 25, 3);
INSERT INTO `v_user_role` VALUES (36, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
