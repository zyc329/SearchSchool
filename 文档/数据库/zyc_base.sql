/*
 Navicat Premium Data Transfer

 Source Server         : zyc
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 127.0.0.1:3306
 Source Schema         : zyc_base

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 15/12/2020 09:10:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_college
-- ----------------------------
DROP TABLE IF EXISTS `tb_college`;
CREATE TABLE `tb_college`  (
  `college_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学院id',
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校id',
  `college_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学院名称',
  `college_introduction` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '学院介绍',
  `college_head` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学院负责人',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`college_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_college
-- ----------------------------
INSERT INTO `tb_college` VALUES ('1', '5fc3b8e7f100db4e740103e4', '3', '4', '5', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_comments
-- ----------------------------
DROP TABLE IF EXISTS `tb_comments`;
CREATE TABLE `tb_comments`  (
  `comments_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '评论回复id',
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评论的学校id',
  `to_comments_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '被评论评论表的id,,\r\n\r\n不是回复别人这为空',
  `to_critics_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '被评论人用户名',
  `to_critics_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '被评论人id',
  `is_comments` int(2) NULL DEFAULT NULL COMMENT '1是评论 0是回复',
  `comments` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '评论的内容',
  `comments_status` int(2) NULL DEFAULT NULL COMMENT '评论状态\r\n  -1删除 1正常',
  `comments_pic` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '评论图片src',
  `critics_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评论人用户名',
  `critics_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评论人id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`comments_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice`  (
  `notification_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '通知id',
  `notification_state` int(2) NULL DEFAULT NULL COMMENT '通知状态 0是已读 1是未读',
  `notifier_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '通知人id',
  `get_id` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '通知内容id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`notification_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_pic`;
CREATE TABLE `tb_pic`  (
  `pic_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '图片id',
  `pic_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片名称',
  `pic_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片类型',
  `pic_src` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片地址src',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_pic
-- ----------------------------
INSERT INTO `tb_pic` VALUES ('5fc3b199f100db4e740103d4', 'a.jpg', 'schoolTitle', 'E:\\tmp\\upload\\a5fc3b199f100db4e740103d3.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3b19df100db4e740103d6', 'bj.jpg', 'schoolDetail', 'E:\\tmp\\upload\\bj5fc3b19df100db4e740103d5.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3b1a0f100db4e740103d8', 'a.jpg', 'schoolPhoto', 'E:\\tmp\\upload\\a5fc3b19ff100db4e740103d7.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3b8cdf100db4e740103db', 'a.jpg', 'schoolTitle', 'E:\\tmp\\upload\\a5fc3b8cdf100db4e740103da.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3b8d0f100db4e740103dd', 'bj.jpg', 'schoolDetail', 'E:\\tmp\\upload\\bj5fc3b8d0f100db4e740103dc.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3b8d9f100db4e740103df', 'bj.jpg', 'schoolPhoto', 'E:\\tmp\\upload\\bj5fc3b8d9f100db4e740103de.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3b8dcf100db4e740103e1', 'a.jpg', 'schoolPhoto', 'E:\\tmp\\upload\\a5fc3b8dcf100db4e740103e0.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3b8e4f100db4e740103e3', 'a.jpg', 'schoolDetail', 'E:\\tmp\\upload\\a5fc3b8e4f100db4e740103e2.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f160f1002002b9f495f6', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f160f1002002b9f495f5.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f1a1f1002002b9f495f8', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f1a1f1002002b9f495f7.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f22cf1002002b9f495fa', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f22cf1002002b9f495f9.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f297f1002002b9f495fc', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f297f1002002b9f495fb.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f453f1002002b9f495fe', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f453f1002002b9f495fd.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f540f1002002b9f49601', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f53ff1002002b9f49600.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f594f1002002b9f49604', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f594f1002002b9f49603.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f5def1002002b9f49607', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f5def1002002b9f49606.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f621f1002002b9f4960a', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f621f1002002b9f49609.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fc3f7d5f100e908244635a6', 'a.jpg', 'profilePicture', 'E:\\tmp\\upload\\a5fc3f7d5f100e908244635a5.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fd479abf10051fa1617494c', 'bj.jpg', 'schoolTitle', 'E:\\tmp\\upload\\bj5fd479abf10051fa1617494b.jpg', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_professional
-- ----------------------------
DROP TABLE IF EXISTS `tb_professional`;
CREATE TABLE `tb_professional`  (
  `professional_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '专业id',
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校id',
  `professional_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '专业名称',
  `college_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学院id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`professional_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_professional
-- ----------------------------
INSERT INTO `tb_professional` VALUES ('121', '5fc3b8e7f100db4e740103e4', '专业1', '1', NULL, NULL, NULL);
INSERT INTO `tb_professional` VALUES ('5fc3e5c5f1000289792a0874', '5fc3b8e7f100db4e740103e4', '测试分数数据专业', '1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_school
-- ----------------------------
DROP TABLE IF EXISTS `tb_school`;
CREATE TABLE `tb_school`  (
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学校id',
  `school_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校名称',
  `school_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校具体地址',
  `school_type` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校类型 0专科 1本科',
  `school_introduction` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '学校简介',
  `school_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校官网网址',
  `school_img_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校校标src',
  `school_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '招生热线',
  `school_head` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '招生办负责人',
  `school_time` datetime(0) NULL DEFAULT NULL COMMENT '创校时间',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `school_ranking_size` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '推荐排名',
  `school_seach_size` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '查看次数',
  `is_ranking` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否推荐 0否，1是',
  `school_pic` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '图片表',
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_school
-- ----------------------------
INSERT INTO `tb_school` VALUES ('5fc3b8e7f100db4e740103e4', '测试学校', '我的学校想在哪里就在哪里', '0', '我的学校就是牛逼', 'http://www.baidu.com', NULL, '13788541254', '测试小哥', '2020-11-12 15:05:28', NULL, NULL, NULL, NULL, '15', '', '5fc3b8cdf100db4e740103db,5fc3b8d0f100db4e740103dd,5fc3b8d9f100db4e740103df,5fc3b8dcf100db4e740103e1,5fc3b8e4f100db4e740103e3');
INSERT INTO `tb_school` VALUES ('5fd479dcf10051fa1617494f', '测试学校二', '测试学校二的具体地址', '1', '测试学校二的学校简介', 'www.taobao.com', NULL, '13788541254', '测试学校二', '2020-12-10 08:03:37', NULL, NULL, NULL, NULL, '1', '', '5fd479abf10051fa1617494c');

-- ----------------------------
-- Table structure for tb_score
-- ----------------------------
DROP TABLE IF EXISTS `tb_score`;
CREATE TABLE `tb_score`  (
  `score_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '分数表id',
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校id',
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '年份',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '分数',
  `professional_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '专业id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`score_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_score
-- ----------------------------
INSERT INTO `tb_score` VALUES ('1', '5fc3b8e7f100db4e740103e4', '2020', '550', '121', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('2', '5fc3b8e7f100db4e740103e4', '2019', '211', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('3', '5fc3b8e7f100db4e740103e4', '2020', '540', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce651cf1006b30ed78015c', '5fc3b8e7f100db4e740103e4', '2010', '11', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce6527f1006b30ed78015d', '5fc3b8e7f100db4e740103e4', '2011', '2011', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce6530f1006b30ed78015e', '5fc3b8e7f100db4e740103e4', '2012', '123', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce6539f1006b30ed78015f', '5fc3b8e7f100db4e740103e4', '2013', '22', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce6540f1006b30ed780160', '5fc3b8e7f100db4e740103e4', '2014', '441', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce654bf1006b30ed780161', '5fc3b8e7f100db4e740103e4', '2015', '551', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce6556f1006b30ed780162', '5fc3b8e7f100db4e740103e4', '2016', '664', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce6564f1006b30ed780163', '5fc3b8e7f100db4e740103e4', '2017', '171', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
INSERT INTO `tb_score` VALUES ('5fce656ef1006b30ed780164', '5fc3b8e7f100db4e740103e4', '2018', '181', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `teacher_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '教师id',
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所处学校id',
  `teacher_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '教师名称',
  `profile_picture` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '教师证件照',
  `teacher_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '教师简介',
  `teaching_age` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '教学工龄',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '职称',
  `college_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学院id',
  `professional_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '专业id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES ('5fc3f638f1002002b9f4960c', '5fc3b8e7f100db4e740103e4', '12222', '5fc3f621f1002002b9f4960a', '1111', '22', '11', '1', '121', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_text
-- ----------------------------
DROP TABLE IF EXISTS `tb_text`;
CREATE TABLE `tb_text`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `text_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `text_age` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `text_sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

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
CREATE TABLE `tb_user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `img_src` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '头像src',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别 1男 0女 2保密',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `salt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '盐值',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色 admin管理员  user普通用户',
  `state` int(255) NULL DEFAULT 0 COMMENT ' 状态  0是正常 1是冻结',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('5fa40f0df100754f9e389d2d', '1165472536@qq.com', '1165472536', '曾帅管理员', 'D:\\123\\246', 2, 23, '123456', 'admin', 0, '2020-11-05 15:20:33', '2020-11-05 15:20:33', '1234567890');
INSERT INTO `tb_user` VALUES ('5fa63b177b8f84827687dfe9', 'zzz', '555555', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('5fb31af1f100b0dd6da5956e', '1212', '123456', '1212', NULL, 1, 11, NULL, 'user', 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('5fc3b6abf100db4e740103d9', '1165472536@qq.com111', '1165472536', '1165472536@qq.com', NULL, 1, 11111, NULL, 'user', 0, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
