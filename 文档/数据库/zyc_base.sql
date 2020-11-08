/*
Navicat MySQL Data Transfer

Source Server         : 陈大帅
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : zyc_base

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-11-08 13:21:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_college
-- ----------------------------
DROP TABLE IF EXISTS `tb_college`;
CREATE TABLE `tb_college` (
  `college_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '学院id',
  `school_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校id',
  `college_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学院名称',
  `college_introduction` text COLLATE utf8_unicode_ci COMMENT '学院介绍',
  `college_head` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学院负责人',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`college_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_college
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comments
-- ----------------------------
DROP TABLE IF EXISTS `tb_comments`;
CREATE TABLE `tb_comments` (
  `comments_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '评论回复id',
  `school_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论的学校id',
  `to_comments_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被评论评论表的id,,\r\n\r\n不是回复别人这为空',
  `to_critics_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被评论人用户名',
  `to_critics_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被评论人id',
  `is_comments` int(2) DEFAULT NULL COMMENT '1是评论 0是回复',
  `comments` text COLLATE utf8_unicode_ci COMMENT '评论的内容',
  `comments_status` int(2) DEFAULT NULL COMMENT '评论状态\r\n  -1删除 1正常',
  `comments_pic` text COLLATE utf8_unicode_ci COMMENT '评论图片src',
  `critics_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论人用户名',
  `critics_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论人id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`comments_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_comments
-- ----------------------------

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `notification_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '通知id',
  `notification_state` int(2) DEFAULT NULL COMMENT '通知状态 0是已读 1是未读',
  `notifier_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通知人id',
  `get_id` text COLLATE utf8_unicode_ci COMMENT '通知内容id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`notification_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------

-- ----------------------------
-- Table structure for tb_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_pic`;
CREATE TABLE `tb_pic` (
  `pic_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '图片id',
  `pic_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片名称',
  `pic_type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片类型',
  `pic_src` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片地址src',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pic_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_pic
-- ----------------------------
INSERT INTO `tb_pic` VALUES ('111', '野鸡大学1', null, null, null, null, null);
INSERT INTO `tb_pic` VALUES ('1211', '野鸡大学2', null, null, null, null, null);

-- ----------------------------
-- Table structure for tb_professional
-- ----------------------------
DROP TABLE IF EXISTS `tb_professional`;
CREATE TABLE `tb_professional` (
  `professional_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '专业id',
  `school_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校id',
  `professional_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '专业名称',
  `college_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学院id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`professional_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_professional
-- ----------------------------

-- ----------------------------
-- Table structure for tb_school
-- ----------------------------
DROP TABLE IF EXISTS `tb_school`;
CREATE TABLE `tb_school` (
  `school_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '学校id',
  `school_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校名称',
  `school_src` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校具体地址',
  `school_type` char(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校类型 0专科 1本科',
  `school_introduction` text COLLATE utf8_unicode_ci COMMENT '学校简介',
  `school_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校官网网址',
  `school_img_src` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校校标src',
  `school_tel` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '招生热线',
  `school_head` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '招生办负责人',
  `school_time` datetime DEFAULT NULL COMMENT '创校时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `school_ranking_size` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '推荐排名',
  `school_seach_size` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '查看次数',
  `is_ranking` char(1) CHARACTER SET utf8 DEFAULT '' COMMENT '是否推荐 0否，1是',
  `school_pic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片表',
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_school
-- ----------------------------
INSERT INTO `tb_school` VALUES ('121', '野鸡大学1', '地球上', '1', '', '', '', '', '', '2020-11-04 12:54:15', '2020-11-20 12:54:40', '2020-11-26 12:54:47', '', '23', '3', '1', '111,1211');
INSERT INTO `tb_school` VALUES ('1211', '野鸡大学2', '地球上', '1', '', '', '', '', '', '2020-11-04 12:54:15', '2020-11-20 12:54:40', '2020-11-26 12:54:47', '', '454', '21', '', '111,1211');
INSERT INTO `tb_school` VALUES ('5fa74dc67b8fe077b8185879', '野鸡大学3', '地球上', '1', null, null, null, null, null, '2020-11-04 12:54:15', null, '2020-11-19 12:48:03', null, '211', '1', '', '111,1211');

-- ----------------------------
-- Table structure for tb_score
-- ----------------------------
DROP TABLE IF EXISTS `tb_score`;
CREATE TABLE `tb_score` (
  `score_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '分数表id',
  `school_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校id',
  `year` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年份',
  `score` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分数',
  `professional_id` varchar(0) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '专业id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`score_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_score
-- ----------------------------

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `teacher_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '教师id',
  `school_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所处学校id',
  `teacher_name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '教师名称',
  `profile_picture` text COLLATE utf8_unicode_ci COMMENT '教师证件照',
  `teacher_introduction` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '教师简介',
  `teaching_age` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '教学工龄',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '职称',
  `college_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学院id',
  `professional_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '专业id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for tb_text
-- ----------------------------
DROP TABLE IF EXISTS `tb_text`;
CREATE TABLE `tb_text` (
  `id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `text_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `text_age` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `text_sex` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_text
-- ----------------------------
INSERT INTO `tb_text` VALUES ('26', '曾帅', '23', '1');
INSERT INTO `tb_text` VALUES ('5f846aebf100b6ebf5c65a8e', '测试update', '100', '1');
INSERT INTO `tb_text` VALUES ('1', 'asdas测撒大苏打', '222', '0');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `account` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `user_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `img_src` text COLLATE utf8_unicode_ci COMMENT '头像src',
  `sex` int(2) DEFAULT NULL COMMENT '性别 1男 0女 2保密',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `salt_value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '盐值',
  `role` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色 admin管理员  user普通用户',
  `state` int(255) DEFAULT '0' COMMENT ' 状态  0是正常 1是冻结',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `operator` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('5fa40f0df100754f9e389d2d', '1165472536@qq.com', '1165472536', '曾帅管理员', 'D:\\123\\246', '2', '23', '123456', 'admin', '0', '2020-11-05 15:20:33', '2020-11-05 15:20:33', '1234567890');
INSERT INTO `tb_user` VALUES ('5fa63b177b8f84827687dfe9', 'zzz', '555555', null, null, null, null, null, null, '0', null, null, null);
