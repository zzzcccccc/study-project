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

 Date: 24/02/2023 17:33:51
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
-- Table structure for sys_upload_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_task`;
CREATE TABLE `sys_upload_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `upload_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分片上传的uploadId',
  `file_identifier` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件唯一标识（md5）',
  `file_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属桶名',
  `object_key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件的key',
  `total_size` bigint(20) NOT NULL COMMENT '文件大小（byte）',
  `chunk_size` bigint(20) NOT NULL COMMENT '每个分片大小（byte）',
  `chunk_num` int(11) NOT NULL COMMENT '分片数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_file_identifier`(`file_identifier`) USING BTREE,
  UNIQUE INDEX `uq_upload_id`(`upload_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分片上传-分片任务记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_upload_task
-- ----------------------------
INSERT INTO `sys_upload_task` VALUES (2, 'N2M0Mjc0NjMtYTMzYi00NzQyLWFmZDMtYWIyOWEzNDQ3MzI2LmFhNDljNjdhLWMwYmYtNDNiYi1iNmM2LWVlZTcxNjQxYzQwZg', '6cfc9cc0feb7cf5f0a18de407587b7da', 'nacos-server-1.1.4.zip', 'atest', '2023-02-07/66929bac-bd85-4ed6-9bae-14b1cf43c3b6.zip', 52118820, 5242880, 10);
INSERT INTO `sys_upload_task` VALUES (3, 'N2M0Mjc0NjMtYTMzYi00NzQyLWFmZDMtYWIyOWEzNDQ3MzI2LjFjMTMyY2QwLTVlMmEtNGU1Mi04NGVjLWExZjAxZmVjYjkwOA', '3adde09f65d7e8954d95bed8334ed985', 'th.jpg', 'atest', '2023-02-07/401f9ac6-f985-417b-9895-0ad1af8f8d06.jpg', 19683, 5242880, 1);
INSERT INTO `sys_upload_task` VALUES (4, 'N2M0Mjc0NjMtYTMzYi00NzQyLWFmZDMtYWIyOWEzNDQ3MzI2LmQ4ZDRmMjA4LThkMjQtNDlkZS05Mjc5LWY0ZGFhNjhjNDIxYw', '88be2764c8060c6b1520b0163523329c', 'nacos-server-2.0.3.zip', 'atest', '2023-02-07/f0919f53-9b52-4ad4-b290-67dcf6bead81.zip', 117598819, 5242880, 23);
INSERT INTO `sys_upload_task` VALUES (5, 'N2M0Mjc0NjMtYTMzYi00NzQyLWFmZDMtYWIyOWEzNDQ3MzI2LjMzMjM0MTQ3LTMxMmItNDNkOS04YTY2LTRkNjgxYzQ4ZjFkNw', '1245ddaa852c99278e555a672e8b9da6', '5fcde24849ccf5330321c30ad266082d.mp4', 'atest', '2023-02-07/0e8c1c2d-6ea1-4154-94b5-c693d1ab68ae.mp4', 579466, 5242880, 1);
INSERT INTO `sys_upload_task` VALUES (6, 'N2M0Mjc0NjMtYTMzYi00NzQyLWFmZDMtYWIyOWEzNDQ3MzI2LmM2YmQwNzVjLTQ4NWMtNDcwMC1iYzFiLTEzMWI4NTRlNWRjYg', 'c5773bf98e692de2cd268bd5b7129292', 'caaaea7604424821136927011de9fabd.mp4', 'atest', '2023-02-07/d05e8597-eea1-42e0-8534-f8d7850e4932.mp4', 31028266, 5242880, 6);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sc` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `indexName`(`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123', 2.00);
INSERT INTO `user` VALUES (2, 'lisi', '123', 2.60);

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
INSERT INTO `user_s` VALUES (1, 'user', '123', 'user', '\r\n组内相互配合和领导交办工作的完成情况 \r\n组长、行业研究牵头人打分，每半年进行一次；');
INSERT INTO `user_s` VALUES (2, 'admin', '123', 'admin', '利用投研平台进行模拟组合配置，根据模拟组合的超额收益（相对行业标准组合）计算该项考核得分\r\n\r\n二级部绩效评审小组根据各研究员模拟组合相对可比基准的超额收益情况评议打分。利用投研平台进行模拟组合配置，根据模拟组合的超额收益（相对行业标准组合）计算该项考核得分');

-- ----------------------------
-- Table structure for v_class
-- ----------------------------
DROP TABLE IF EXISTS `v_class`;
CREATE TABLE `v_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade_id` int(11) NULL DEFAULT NULL COMMENT '年级id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '班级和年级详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_class
-- ----------------------------
INSERT INTO `v_class` VALUES (1, '一班', 1, '2022-09-06 13:52:09', NULL, '0');
INSERT INTO `v_class` VALUES (2, '一班', 4, '2022-09-06 13:52:09', NULL, '0');
INSERT INTO `v_class` VALUES (3, '一班', 2, '2022-09-06 13:52:09', NULL, '0');
INSERT INTO `v_class` VALUES (4, '一班', 5, '2022-09-06 13:52:09', NULL, '0');
INSERT INTO `v_class` VALUES (5, '一班', 3, '2022-09-06 13:52:09', NULL, '0');
INSERT INTO `v_class` VALUES (6, '一班', 6, '2022-09-06 13:52:09', NULL, '0');
INSERT INTO `v_class` VALUES (7, '二班', 1, '2022-09-06 13:52:21', NULL, '0');
INSERT INTO `v_class` VALUES (8, '二班', 4, '2022-09-06 13:52:21', NULL, '0');
INSERT INTO `v_class` VALUES (9, '二班', 2, '2022-09-06 13:52:21', NULL, '0');
INSERT INTO `v_class` VALUES (10, '二班', 5, '2022-09-06 13:52:21', NULL, '0');
INSERT INTO `v_class` VALUES (11, '二班', 3, '2022-09-06 13:52:21', NULL, '0');
INSERT INTO `v_class` VALUES (12, '二班', 6, '2022-09-06 13:52:21', NULL, '0');
INSERT INTO `v_class` VALUES (13, '三班', 1, '2022-09-06 13:54:37', NULL, '0');
INSERT INTO `v_class` VALUES (14, '三班', 2, '2022-09-06 13:54:37', NULL, '0');
INSERT INTO `v_class` VALUES (21, '四班', 1, '2023-02-14 16:23:05', NULL, '0');
INSERT INTO `v_class` VALUES (22, '五班', 1, '2023-02-14 16:23:29', NULL, '0');
INSERT INTO `v_class` VALUES (23, '六班', 1, '2023-02-14 16:23:35', NULL, '0');
INSERT INTO `v_class` VALUES (24, '七班', 1, '2023-02-14 16:23:58', NULL, '0');
INSERT INTO `v_class` VALUES (25, '八班', 1, '2023-02-14 16:24:03', NULL, '0');
INSERT INTO `v_class` VALUES (26, '九班', 1, '2023-02-14 16:24:08', NULL, '0');
INSERT INTO `v_class` VALUES (27, '实验班', 1, '2023-02-14 16:24:12', NULL, '0');
INSERT INTO `v_class` VALUES (28, '冲刺班', 1, '2023-02-14 16:24:32', NULL, '0');
INSERT INTO `v_class` VALUES (29, '补课班', 1, '2023-02-14 16:25:04', NULL, '0');
INSERT INTO `v_class` VALUES (30, '十一班', 1, '2023-02-14 16:25:25', NULL, '0');
INSERT INTO `v_class` VALUES (31, '1班', 1, '2023-02-15 14:34:02', NULL, '0');
INSERT INTO `v_class` VALUES (32, '2班', 1, '2023-02-15 14:34:08', NULL, '0');
INSERT INTO `v_class` VALUES (33, '6666', 1, '2023-02-15 14:35:18', NULL, '0');

-- ----------------------------
-- Table structure for v_cron
-- ----------------------------
DROP TABLE IF EXISTS `v_cron`;
CREATE TABLE `v_cron`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `cron` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `class_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求地址',
  `stauts` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态 0启用1停止',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务类型 0启动时判断使用 1测试',
  `last_execution_time` timestamp(0) NULL DEFAULT NULL COMMENT '上次执行时间',
  `lately_execution_time` timestamp(0) NULL DEFAULT NULL COMMENT '最近一次执行时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_cron
-- ----------------------------
INSERT INTO `v_cron` VALUES (1, '定时1', '0/5 * * * * ?', '1', 'http://127.0.0.1:8081/cron/getPage', '1', '1', '2022-10-20 16:03:43', '2022-10-21 16:03:48', '2022-10-20 16:03:52', '2023-01-30 14:48:28', '0');
INSERT INTO `v_cron` VALUES (2, '定时2', '0/5 * * * * ?', '0', 'http://127.0.0.1:8081/cron/getPage', '1', '1', '2022-10-20 16:03:43', '2022-10-21 16:03:48', '2022-10-20 17:11:59', '2023-01-30 14:48:28', '0');

-- ----------------------------
-- Table structure for v_cron_exception
-- ----------------------------
DROP TABLE IF EXISTS `v_cron_exception`;
CREATE TABLE `v_cron_exception`  (
  `task_id` bigint(20) NOT NULL,
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标记 0正常 1删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_cron_exception
-- ----------------------------

-- ----------------------------
-- Table structure for v_exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `v_exam_paper`;
CREATE TABLE `v_exam_paper`  (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `headline` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '试卷题目',
  `paper_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '试卷类型 1计时 2不计时',
  `deadline` timestamp(0) NULL DEFAULT NULL COMMENT '截止时间',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '学科id',
  `grade_id` int(11) NULL DEFAULT NULL COMMENT '年级id',
  `class_id_array` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级ids',
  `total_points` float(12, 1) NULL DEFAULT NULL COMMENT '总分',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '试卷详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_exam_paper
-- ----------------------------
INSERT INTO `v_exam_paper` VALUES (7, '三年级试卷-语文', NULL, '2023-02-24 00:00:00', 10, 6, '[6, 12]', NULL, '2023-02-24 15:45:01', NULL, '0');

-- ----------------------------
-- Table structure for v_exam_quest
-- ----------------------------
DROP TABLE IF EXISTS `v_exam_quest`;
CREATE TABLE `v_exam_quest`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) NULL DEFAULT NULL,
  `quest_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_exam_quest
-- ----------------------------
INSERT INTO `v_exam_quest` VALUES (6, 7, 44);

-- ----------------------------
-- Table structure for v_grade
-- ----------------------------
DROP TABLE IF EXISTS `v_grade`;
CREATE TABLE `v_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '年级分类表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `index_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_menu
-- ----------------------------
INSERT INTO `v_menu` VALUES (1, '用户管理', 'user:all', -1, 1, NULL, 'user/', 'el-icon-user-solid', 1, 1, '2022-07-22 16:23:57', '2022-09-01 15:04:34', '0');
INSERT INTO `v_menu` VALUES (3, '用户列表', 'user:list', 1, 2, NULL, 'user/userInfo', 'el-icon-s-custom', 1, 1010, '2022-07-22 16:24:28', '2023-02-24 14:58:42', '0');
INSERT INTO `v_menu` VALUES (9, '添加用户', 'user:add', 3, 3, NULL, NULL, 'el-icon-menu', 2, 101030, '2022-07-22 16:25:42', '2023-02-24 14:58:59', '0');
INSERT INTO `v_menu` VALUES (10, '修改', 'user:edit', 3, 3, NULL, NULL, 'el-icon-menu', 2, 101040, '2022-07-22 16:25:42', '2023-02-24 14:59:02', '0');
INSERT INTO `v_menu` VALUES (11, '删除', 'user:del', 3, 3, NULL, NULL, 'el-icon-menu', 2, 101050, '2022-07-22 16:25:42', '2023-02-24 14:59:04', '0');
INSERT INTO `v_menu` VALUES (12, '分配角色', 'user:roles', 3, 3, NULL, NULL, 'el-icon-menu', 2, 101020, '2022-07-22 16:25:42', '2023-02-24 14:58:56', '0');
INSERT INTO `v_menu` VALUES (17, '系统管理', 'sys:all', -1, 1, NULL, 'system/', 'el-icon-s-tools', 1, 2, '2022-07-22 16:26:54', '2022-09-01 15:04:36', '0');
INSERT INTO `v_menu` VALUES (18, '角色列表', 'role:list', 17, 2, NULL, 'system/roleIndex', 'el-icon-s-help', 1, 2010, '2022-07-22 16:27:05', '2023-02-24 14:59:24', '0');
INSERT INTO `v_menu` VALUES (19, '菜单列表', 'menu:list', 17, 2, NULL, 'menu/menuInfo', 'el-icon-menu', 1, 2020, '2022-07-22 16:27:08', '2023-02-24 14:59:26', '0');
INSERT INTO `v_menu` VALUES (20, 'demo列表', 'demo:list', 17, 2, 'ceshi', 'system/permissionDemo', 'el-icon-s-opportunity', 1, 2030, '2022-07-22 16:27:12', '2023-02-24 14:59:29', '0');
INSERT INTO `v_menu` VALUES (21, '添加角色', 'role:add', 18, 3, NULL, NULL, 'el-icon-menu', 2, 201010, '2022-07-22 16:29:04', '2023-02-24 14:59:44', '0');
INSERT INTO `v_menu` VALUES (22, '修改角色', 'role:edit', 18, 3, NULL, NULL, 'el-icon-menu', 2, 201020, '2022-07-22 16:29:04', '2023-02-24 14:59:57', '0');
INSERT INTO `v_menu` VALUES (23, '删除角色', 'role:del', 18, 3, NULL, NULL, 'el-icon-menu', 2, 201030, '2022-07-22 16:29:04', '2023-02-24 15:00:01', '0');
INSERT INTO `v_menu` VALUES (24, '分配权限', 'role:permission', 18, 3, NULL, NULL, 'el-icon-menu', 2, 201040, '2022-07-22 16:29:04', '2023-02-24 15:00:05', '0');
INSERT INTO `v_menu` VALUES (25, '添加分类', 'menu:add', 19, 3, NULL, NULL, 'el-icon-menu', 2, 202030, '2022-07-22 16:32:13', '2023-02-24 15:00:39', '0');
INSERT INTO `v_menu` VALUES (26, '新增', 'menu:addInfo', 19, 3, NULL, NULL, 'el-icon-menu', 2, 202040, '2022-07-22 16:32:15', '2023-02-24 15:00:42', '0');
INSERT INTO `v_menu` VALUES (27, '修改', 'menu:edit', 19, 3, NULL, NULL, 'el-icon-menu', 2, 202010, '2022-07-22 16:32:17', '2023-02-24 15:00:32', '0');
INSERT INTO `v_menu` VALUES (28, '删除', 'menu:del', 19, 3, NULL, NULL, 'el-icon-menu', 2, 202020, '2022-07-22 16:32:22', '2023-02-24 15:00:36', '0');
INSERT INTO `v_menu` VALUES (31, 'demo三', 'dem', 20, 3, NULL, '/dem', 'el-icon-menu', 2, 203010, '2022-08-31 09:50:06', '2023-02-24 15:01:04', '0');
INSERT INTO `v_menu` VALUES (32, '用户列表', 'user:select', 3, 3, NULL, 'user/studentInfo', 'el-icon-menu', 2, 101010, '2022-08-31 16:04:56', '2023-02-24 14:58:53', '0');
INSERT INTO `v_menu` VALUES (34, 'demo3333', 'demo33', 20, 3, NULL, 'demo33', 'el-icon-menu', 2, 203020, '2022-08-31 16:53:34', '2023-02-24 15:01:08', '0');
INSERT INTO `v_menu` VALUES (45, '学科管理', 'subject:all', -1, 1, '', 'subject/', 'el-icon-s-management', 1, 3, '2022-09-01 16:01:32', '2023-02-24 14:36:51', '0');
INSERT INTO `v_menu` VALUES (46, '学科创编', 'subject:list', 45, 2, NULL, 'subject/list', 'el-icon-notebook-2', 1, 3010, '2022-09-01 16:02:59', '2023-02-24 15:01:53', '0');
INSERT INTO `v_menu` VALUES (47, '新增', 'subject:add', 46, 3, NULL, '/', 'el-icon-menu', 2, 301010, '2022-09-01 16:07:09', '2023-02-24 15:02:11', '0');
INSERT INTO `v_menu` VALUES (48, '删除', 'subject:del', 46, 3, NULL, '/', 'el-icon-menu', 2, 301020, '2022-09-01 16:08:55', '2023-02-24 15:02:18', '0');
INSERT INTO `v_menu` VALUES (49, '编辑', 'subject:edit', 46, 3, NULL, '/', 'el-icon-menu', 2, 301030, '2022-09-01 16:12:34', '2023-02-24 15:02:23', '0');
INSERT INTO `v_menu` VALUES (50, '列表', 'subject:list', 46, 3, NULL, '/', 'el-icon-menu', 2, 301040, '2022-09-01 16:16:36', '2023-02-24 15:02:26', '0');
INSERT INTO `v_menu` VALUES (51, '题库管理', 'topic:all', -1, 1, NULL, 'topic/', 'el-icon-s-order', 1, 4, '2022-09-01 16:23:23', '2023-02-24 14:37:04', '0');
INSERT INTO `v_menu` VALUES (52, '列表', 'topic:list', 51, 2, NULL, 'topic/list', 'el-icon-location', 1, 4010, '2022-09-01 16:24:21', '2023-02-24 15:03:16', '0');
INSERT INTO `v_menu` VALUES (53, '班级创编', 'class:list', 45, 2, '年级-班级', 'class/list', 'el-icon-s-flag', 1, 3020, '2022-09-05 17:23:24', '2023-02-24 15:01:57', '0');
INSERT INTO `v_menu` VALUES (54, '定时任务', 'admin:task', -1, 1, NULL, 'task', 'el-icon-message-solid', 1, 6, '2022-10-19 09:15:25', '2023-02-24 14:37:08', '0');
INSERT INTO `v_menu` VALUES (55, '定时器详情', 'task:all', 54, 2, NULL, 'task/all', 'el-icon-location', 1, 6010, '2022-10-19 09:16:44', '2023-02-24 15:03:57', '0');
INSERT INTO `v_menu` VALUES (56, '文件上传', 'topic:file', 51, 2, NULL, 'topic/file', 'el-icon-upload', 1, 4040, '2023-01-30 15:26:49', '2023-02-24 15:03:25', '0');
INSERT INTO `v_menu` VALUES (57, '创编', 'topic:create', 51, 2, NULL, 'topic/create', 'el-icon-s-opportunity', 1, 4020, '2023-02-10 13:56:51', '2023-02-24 15:03:19', '0');
INSERT INTO `v_menu` VALUES (58, '卷库管理', 'exam:paper', -1, 1, NULL, 'exam', 'el-icon-s-data', 1, 5, '2023-02-15 09:30:35', '2023-02-24 14:37:05', '0');
INSERT INTO `v_menu` VALUES (59, '列表', 'exam:paper', 58, 2, NULL, 'exam/paper', 'el-icon-s-data', 1, 5010, '2023-02-15 09:31:25', '2023-02-24 15:03:42', '0');
INSERT INTO `v_menu` VALUES (60, '修改题库', 'topic:editQues', 52, 3, NULL, '/topic/editQues', 'el-icon-menu', 2, 401020, '2023-02-20 10:53:10', '2023-02-24 15:03:22', '0');
INSERT INTO `v_menu` VALUES (61, '删除', 'topic:del', 52, 3, NULL, '/topic/del', 'el-icon-menu', 2, 401010, '2023-02-21 17:18:43', '2023-02-24 15:14:19', '0');
INSERT INTO `v_menu` VALUES (62, 'test', 'test', 52, 3, NULL, 'test', 'el-icon-menu', 2, 999, '2023-02-21 17:21:01', '2023-02-24 15:10:40', '1');
INSERT INTO `v_menu` VALUES (63, '创编', 'paper:create', 58, 2, NULL, 'paper/create', 'el-icon-s-opportunity', 1, 5020, '2023-02-24 14:33:06', '2023-02-24 15:03:44', '0');

-- ----------------------------
-- Table structure for v_question
-- ----------------------------
DROP TABLE IF EXISTS `v_question`;
CREATE TABLE `v_question`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '题目',
  `analyze` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '解析',
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '正确答案 （多选题是数组[\"A\",\"B\"]）',
  `difficult` int(11) NULL DEFAULT NULL COMMENT '难度',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '单多判题 选项 [{},{}]',
  `konwlege_id_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '知识点',
  `ques_type_id` int(11) NULL DEFAULT NULL COMMENT '问题类型1单选 2多选 3填空 4简答 5判断',
  `score` float(12, 1) NULL DEFAULT NULL COMMENT '分数',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '学科id',
  `grade_id` int(11) NULL DEFAULT NULL COMMENT '年级id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_question
-- ----------------------------
INSERT INTO `v_question` VALUES (1, '<p style=\"text-align: center\"><img class=\"xzs-image\" src=\"https://www.mindskip.net:7000/resource/image/8e5a1c74-89df-40ee-9424-a82878675d01/img01_r.jpg\" alt=\"img01_r.jpg\"></p><p><span style=\"color: rgb(255, 255, 0);\">88888888888888888888888</span><span style=\"color: rgb(255, 255, 0);\"></span></p>', '<span style=\"color: rgb(255, 0, 0);\">JIEXI</span>', 'A', 2, '[{\"prefix\": \"A\", \"content\": \"<span style=\\\"text-decoration: underline;\\\">AAAAAAAAAAAAAAAAADDD</span>\"}, {\"prefix\": \"B\", \"content\": \"<p><span style=\\\"color: rgb(255, 255, 0);\\\">BBBBBBBBBBBBBBBBBBBBBBBB</span></p><p>bbbbbbbbbbbbbbb</p>\"}, {\"prefix\": \"C\", \"content\": \"<p><span style=\\\"color: rgb(255, 0, 0);\\\">CCCCCCCCCCCCCCCCCCCC</span></p><p><span style=\\\"color: rgb(0, 0, 0);\\\">LALALALLALAA</span><span style=\\\"color: rgb(0, 0, 0);\\\"></span></p>\"}, {\"prefix\": \"D\", \"content\": \"DDDDDDDDDDDDDDDDDDDD\"}]', NULL, 1, 1.0, 1, 1, '2023-02-10 09:40:01', '2023-02-17 17:21:35', '1');
INSERT INTO `v_question` VALUES (36, '题干是', '解析零零零零', '[\"参考答案是\"]', 3, '[]', NULL, 4, 5.0, 11, 3, '2023-02-17 17:22:29', '2023-02-21 17:13:17', '1');
INSERT INTO `v_question` VALUES (37, '他们全让我走路，他们让我在雨里散步， ______?', '歌词', '[\"他们说我是怪物\"]', 5, '[]', NULL, 4, 777.0, 5, 2, '2023-02-17 17:23:43', '2023-02-24 11:02:00', '0');
INSERT INTO `v_question` VALUES (38, '7777777', 'jic', '[\"7777\"]', 3, '[\"7777\",\"564\",\"45\",\"34\"]', NULL, 1, 7.0, 2, 1, '2023-02-20 14:35:54', '2023-02-21 15:59:41', '1');
INSERT INTO `v_question` VALUES (39, '333333', '33334344', '[\"对\"]', 3, '[]', NULL, 5, 3.0, 2, 1, '2023-02-20 14:35:54', '2023-02-24 11:02:04', '1');
INSERT INTO `v_question` VALUES (40, '写词都用12345 也听起来很俗 谁在乎呜呜呜呜~~', 'SDFSDFSD', '[\"BBBBBBB\"]', 2, '[\"BBBBBBB\",\"FFFFFFFFFFF\",\"CCCCCCCC\",\"DDDDDDDDD\"]', NULL, 1, 1.0, 1, 1, '2023-02-21 16:04:50', '2023-02-24 11:01:14', '0');
INSERT INTO `v_question` VALUES (41, '高脚杯喝水真的很酷！！！', '45', '[\"6667676\"]', 2, '[\"6667676\",\"78\",\"56\",\"3\"]', NULL, 1, 9.0, 2, 1, '2023-02-21 16:35:13', '2023-02-21 16:36:16', '0');
INSERT INTO `v_question` VALUES (42, '请问2023年2月24日星期天？', '自行百度', '[\"星期五\"]', 2, '[\"星期一\",\"星期五\",\"星期六\",\"星期四\"]', NULL, 1, 3.0, 4, 1, '2023-02-24 10:59:36', NULL, '0');
INSERT INTO `v_question` VALUES (43, '1+1=2对吗', '有有有', '[\"对\"]', 4, '[]', NULL, 5, 2.0, 4, 1, '2023-02-24 10:59:36', NULL, '0');
INSERT INTO `v_question` VALUES (44, '多选题 三年级下册 语文 一班二班 测试', '绑定卡了大家考虑进去看我Joe u恶女老总的', '[\"史蒂芬德尔呃呃\",\"大苏打JFK是\"]', 2, '[\"大苏打JFK是\",\"似懂非懂\",\"是的方法\",\"史蒂芬德尔呃呃\"]', NULL, 2, 10.0, 10, 6, '2023-02-24 15:45:01', NULL, '0');

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
INSERT INTO `v_role_menu` VALUES (1, 33);
INSERT INTO `v_role_menu` VALUES (1, 32);
INSERT INTO `v_role_menu` VALUES (1, 20);
INSERT INTO `v_role_menu` VALUES (1, 31);
INSERT INTO `v_role_menu` VALUES (1, 34);
INSERT INTO `v_role_menu` VALUES (1, 1);
INSERT INTO `v_role_menu` VALUES (1, 2);
INSERT INTO `v_role_menu` VALUES (1, 3);
INSERT INTO `v_role_menu` VALUES (1, 17);
INSERT INTO `v_role_menu` VALUES (2, 9);
INSERT INTO `v_role_menu` VALUES (2, 10);
INSERT INTO `v_role_menu` VALUES (2, 11);
INSERT INTO `v_role_menu` VALUES (2, 20);
INSERT INTO `v_role_menu` VALUES (2, 31);
INSERT INTO `v_role_menu` VALUES (2, 34);
INSERT INTO `v_role_menu` VALUES (2, 62);
INSERT INTO `v_role_menu` VALUES (2, 56);
INSERT INTO `v_role_menu` VALUES (2, 57);
INSERT INTO `v_role_menu` VALUES (2, 1);
INSERT INTO `v_role_menu` VALUES (2, 3);
INSERT INTO `v_role_menu` VALUES (2, 17);
INSERT INTO `v_role_menu` VALUES (2, 51);
INSERT INTO `v_role_menu` VALUES (2, 52);
INSERT INTO `v_role_menu` VALUES (3, 1);
INSERT INTO `v_role_menu` VALUES (3, 3);
INSERT INTO `v_role_menu` VALUES (3, 32);
INSERT INTO `v_role_menu` VALUES (3, 12);
INSERT INTO `v_role_menu` VALUES (3, 9);
INSERT INTO `v_role_menu` VALUES (3, 10);
INSERT INTO `v_role_menu` VALUES (3, 11);
INSERT INTO `v_role_menu` VALUES (3, 17);
INSERT INTO `v_role_menu` VALUES (3, 18);
INSERT INTO `v_role_menu` VALUES (3, 21);
INSERT INTO `v_role_menu` VALUES (3, 22);
INSERT INTO `v_role_menu` VALUES (3, 23);
INSERT INTO `v_role_menu` VALUES (3, 24);
INSERT INTO `v_role_menu` VALUES (3, 19);
INSERT INTO `v_role_menu` VALUES (3, 27);
INSERT INTO `v_role_menu` VALUES (3, 28);
INSERT INTO `v_role_menu` VALUES (3, 25);
INSERT INTO `v_role_menu` VALUES (3, 26);
INSERT INTO `v_role_menu` VALUES (3, 20);
INSERT INTO `v_role_menu` VALUES (3, 31);
INSERT INTO `v_role_menu` VALUES (3, 34);
INSERT INTO `v_role_menu` VALUES (3, 45);
INSERT INTO `v_role_menu` VALUES (3, 46);
INSERT INTO `v_role_menu` VALUES (3, 47);
INSERT INTO `v_role_menu` VALUES (3, 48);
INSERT INTO `v_role_menu` VALUES (3, 49);
INSERT INTO `v_role_menu` VALUES (3, 50);
INSERT INTO `v_role_menu` VALUES (3, 53);
INSERT INTO `v_role_menu` VALUES (3, 51);
INSERT INTO `v_role_menu` VALUES (3, 52);
INSERT INTO `v_role_menu` VALUES (3, 61);
INSERT INTO `v_role_menu` VALUES (3, 60);
INSERT INTO `v_role_menu` VALUES (3, 57);
INSERT INTO `v_role_menu` VALUES (3, 56);
INSERT INTO `v_role_menu` VALUES (3, 58);
INSERT INTO `v_role_menu` VALUES (3, 59);
INSERT INTO `v_role_menu` VALUES (3, 63);
INSERT INTO `v_role_menu` VALUES (3, 54);
INSERT INTO `v_role_menu` VALUES (3, 55);

-- ----------------------------
-- Table structure for v_subject
-- ----------------------------
DROP TABLE IF EXISTS `v_subject`;
CREATE TABLE `v_subject`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade_id` int(11) NULL DEFAULT NULL COMMENT '年级id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学科表（学科和年级）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_subject
-- ----------------------------
INSERT INTO `v_subject` VALUES (1, '数学', 1, '2022-09-02 16:48:18', '2022-09-02 16:48:36', '0');
INSERT INTO `v_subject` VALUES (2, '语文', 1, '2022-09-02 16:48:22', '2022-09-02 16:48:36', '0');
INSERT INTO `v_subject` VALUES (3, '英语', 2, '2022-09-05 14:44:43', NULL, '0');
INSERT INTO `v_subject` VALUES (4, '英语', 1, '2022-09-05 14:45:00', NULL, '0');
INSERT INTO `v_subject` VALUES (5, '数学', 2, '2022-09-05 14:46:24', '2022-09-05 15:02:19', '0');
INSERT INTO `v_subject` VALUES (6, '数学888', 2, '2022-09-05 15:04:34', '2022-09-05 15:06:31', '1');
INSERT INTO `v_subject` VALUES (7, '语文', 2, '2022-09-05 15:11:38', NULL, '0');
INSERT INTO `v_subject` VALUES (8, '体育', 2, '2022-09-05 15:11:49', NULL, '0');
INSERT INTO `v_subject` VALUES (9, '语文', 5, '2022-09-05 16:53:27', NULL, '0');
INSERT INTO `v_subject` VALUES (10, '语文', 6, '2022-09-05 16:53:27', NULL, '0');
INSERT INTO `v_subject` VALUES (11, '体育', 3, '2022-09-09 10:32:11', NULL, '0');
INSERT INTO `v_subject` VALUES (12, '体育', 4, '2022-09-09 10:32:11', NULL, '0');

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of v_user
-- ----------------------------
INSERT INTO `v_user` VALUES (1, NULL, 'student', '123456', '学生', 18, 1, '2022-02-02 00:00:00.0', 1, '19171171610', 1, 'https://www.mindskip.net:9008/image/ba607a75-83ba-4530-8e23-660b72dc4953/头像.jpg', '2019-09-07 18:55:02', '2022-09-08 17:15:20', NULL, NULL, '0');
INSERT INTO `v_user` VALUES (2, NULL, 'admin', '123456', '管理员', 44, 1, '2022-02-02 00:00:00.0', NULL, '17610183611', 1, NULL, '2019-09-07 18:56:21', '2022-09-09 10:08:19', NULL, NULL, '0');
INSERT INTO `v_user` VALUES (3, NULL, 'teacher', '123456', '老师', 34, 2, '2022-02-02 00:00:00.0', NULL, '19873829399', 1, NULL, '2022-07-12 10:09:36', '2022-09-08 16:52:57', NULL, NULL, '0');
INSERT INTO `v_user` VALUES (21, NULL, 'test', '123456', '里斯', 33, 2, '2022-07-13 08:00:00.0', NULL, '33333', 2, NULL, '2022-07-13 09:11:44', '2022-07-13 13:52:43', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (23, NULL, 'test', '123', 'test', 12, 2, '2022-07-12 08:00:00.0', NULL, '44444', 1, NULL, '2022-07-14 10:41:14', '2022-07-14 14:11:34', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (24, NULL, 'd', 'd', '1', 1, 2, '2022-07-14 08:00:00.0', NULL, '1', 1, NULL, '2022-07-14 10:41:28', '2023-01-09 16:33:58', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (25, NULL, '111', '123457', '11', 11, 2, '2022-07-14 08:00:00.0', NULL, '11111', 1, NULL, '2022-07-14 14:04:53', '2022-09-01 09:32:12', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (26, NULL, '7', '7', '7', 7, 1, '2022-07-14 08:00:00.0', NULL, '7', 1, NULL, '2022-07-14 14:10:02', '2022-09-01 09:33:36', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (27, NULL, 'dd', 'dddddd', 'dd', NULL, 1, NULL, NULL, 'ddddd', 1, NULL, '2022-07-19 14:42:05', '2022-07-19 14:42:18', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (28, NULL, '张三', '123456', '张三', 12, 1, '2022-09-28 08:00:00.0', NULL, '173890839237', 1, NULL, '2022-09-06 15:15:56', NULL, NULL, NULL, '0');
INSERT INTO `v_user` VALUES (29, NULL, '李老师', '123456', '李四', 44, 2, '2022-09-05 08:00:00.0', NULL, '1937472933', 1, NULL, '2022-09-06 15:31:57', NULL, NULL, NULL, '0');
INSERT INTO `v_user` VALUES (30, NULL, '王老师', '123456', '王五', 555, 1, '2021-09-07 08:00:00.0', NULL, '57382739021', 1, NULL, '2022-09-06 15:35:01', NULL, NULL, NULL, '0');
INSERT INTO `v_user` VALUES (31, NULL, '00', '000000', '00', 0, 2, '2022-09-13 08:00:00.0', NULL, '00889786556', 1, NULL, '2022-09-07 10:39:58', '2022-09-09 10:07:05', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (32, NULL, '33', '333333', '33', 33, 1, '2022-09-20 08:00:00.0', NULL, '33333333333', 1, NULL, '2022-09-07 10:53:09', NULL, NULL, NULL, '0');
INSERT INTO `v_user` VALUES (33, NULL, '665', '555555', '55555', 55, 1, '2022-09-13 08:00:00.0', NULL, '54534455', 1, NULL, '2022-09-07 10:58:05', '2023-02-21 17:26:26', NULL, NULL, '1');
INSERT INTO `v_user` VALUES (34, NULL, 'test', '123456', 'test', 12, 1, '2022-09-06 08:00:00.0', NULL, '12223424675', 1, NULL, '2022-09-07 16:51:15', NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for v_user_role
-- ----------------------------
DROP TABLE IF EXISTS `v_user_role`;
CREATE TABLE `v_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_user_role
-- ----------------------------
INSERT INTO `v_user_role` VALUES (38, 29, 2);
INSERT INTO `v_user_role` VALUES (39, 30, 2);
INSERT INTO `v_user_role` VALUES (41, 32, NULL);
INSERT INTO `v_user_role` VALUES (59, 1, 1);
INSERT INTO `v_user_role` VALUES (60, 3, 2);
INSERT INTO `v_user_role` VALUES (68, 34, 2);
INSERT INTO `v_user_role` VALUES (69, 34, 3);
INSERT INTO `v_user_role` VALUES (70, 2, 3);
INSERT INTO `v_user_role` VALUES (74, 28, 2);

-- ----------------------------
-- Table structure for v_user_subject_class
-- ----------------------------
DROP TABLE IF EXISTS `v_user_subject_class`;
CREATE TABLE `v_user_subject_class`  (
  `user_id` int(11) NULL DEFAULT NULL,
  `subject_id` int(11) NULL DEFAULT NULL,
  `grade_id` int(11) NULL DEFAULT NULL COMMENT '年级id',
  `class_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_user_subject_class
-- ----------------------------
INSERT INTO `v_user_subject_class` VALUES (1, 3, 2, '[3]');
INSERT INTO `v_user_subject_class` VALUES (3, 3, 2, '[3]');
INSERT INTO `v_user_subject_class` VALUES (34, 10, 6, '[12, 6]');
INSERT INTO `v_user_subject_class` VALUES (2, NULL, NULL, '[]');
INSERT INTO `v_user_subject_class` VALUES (28, 4, 1, '[1]');

SET FOREIGN_KEY_CHECKS = 1;
