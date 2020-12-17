/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 127.0.0.1:3306
 Source Schema         : zyc_base

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 17/12/2020 13:50:19
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
-- Records of tb_comments
-- ----------------------------

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
-- Records of tb_notice
-- ----------------------------

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
INSERT INTO `tb_pic` VALUES ('5fdaa99e60f7c072cdce0518', '1 - 副本 (2).jpg', 'schoolTitle', 'E:\\tmp\\upload\\1 - 副本 (2)5fdaa99e60f7c072cdce0517.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9a160f7c072cdce051a', '1 - 副本 (2).jpg', 'schoolDetail', 'E:\\tmp\\upload\\1 - 副本 (2)5fdaa9a160f7c072cdce0519.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9a460f7c072cdce051c', '1.jpg', 'schoolDetail', 'E:\\tmp\\upload\\15fdaa9a460f7c072cdce051b.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9a660f7c072cdce051e', '1 - 副本 (3).jpg', 'schoolDetail', 'E:\\tmp\\upload\\1 - 副本 (3)5fdaa9a660f7c072cdce051d.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9a960f7c072cdce0520', '1 - 副本 (4).jpg', 'schoolDetail', 'E:\\tmp\\upload\\1 - 副本 (4)5fdaa9a960f7c072cdce051f.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9ad60f7c072cdce0522', '1 - 副本.jpg', 'schoolPhoto', 'E:\\tmp\\upload\\1 - 副本5fdaa9ad60f7c072cdce0521.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9b360f7c072cdce0524', 'QQ图片20200910201852.png', 'schoolPhoto', 'E:\\tmp\\upload\\QQ图片202009102018525fdaa9b360f7c072cdce0523.png', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9b460f7c072cdce0526', '1.jpg', 'schoolPhoto', 'E:\\tmp\\upload\\15fdaa9b460f7c072cdce0525.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9b760f7c072cdce0528', '1 - 副本 (4).jpg', 'schoolPhoto', 'E:\\tmp\\upload\\1 - 副本 (4)5fdaa9b760f7c072cdce0527.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9b960f7c072cdce052a', '1 - 副本 (3).jpg', 'schoolPhoto', 'E:\\tmp\\upload\\1 - 副本 (3)5fdaa9b960f7c072cdce0529.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9bc60f7c072cdce052c', '1 - 副本 (2).jpg', 'schoolPhoto', 'E:\\tmp\\upload\\1 - 副本 (2)5fdaa9bc60f7c072cdce052b.jpg', NULL, NULL, NULL);
INSERT INTO `tb_pic` VALUES ('5fdaa9fa60f7c072cdce052e', 'QQ图片20200910201852.png', 'schoolTitle', 'E:\\tmp\\upload\\QQ图片202009102018525fdaa9fa60f7c072cdce052d.png', NULL, NULL, NULL);

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
  `school_seach_size` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '查看次数',
  `is_ranking` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否推荐 0否，1是',
  `school_pic` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '图片表',
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_school
-- ----------------------------
INSERT INTO `tb_school` VALUES ('5fc3b8e7f100db4e740103e4', '测试学校', '我的学校想在哪里就在哪里', '0', '我的学校就是牛逼', 'http://www.baidu.com', NULL, '13788541254', '测试小哥', '2020-11-12 15:05:28', NULL, NULL, NULL, NULL, NULL, NULL, '5fc3f160f1002002b9f495f6,5fc3f1a1f1002002b9f495f8,5fc3f22cf1002002b9f495fa,5fc3f297f1002002b9f495fc,5fc3f453f1002002b9f495fe,5fc3f540f1002002b9f49601,5fc3f594f1002002b9f49604,5fc3f5def1002002b9f49607,5fc3f621f1002002b9f4960a,5fc3f7d5f100e908244635a6,5fdaa99e60f7c072cdce0518,5fdaa9a160f7c072cdce051a,5fdaa9a460f7c072cdce051c,5fdaa9a660f7c072cdce051e,5fdaa9a960f7c072cdce0520,5fdaa9ad60f7c072cdce0522,5fdaa9b360f7c072cdce0524,5fdaa9b460f7c072cdce0526,5fdaa9b760f7c072cdce0528,5fdaa9b960f7c072cdce052a,5fdaa9bc60f7c072cdce052c');
INSERT INTO `tb_school` VALUES ('5fdaa9fe60f7c072cdce052f', '测试学校二', '在很远的地方', '0', '有一天我和妈妈图书馆买书，我正在挑我要看什么好，有一本叫《爱丽丝梦游奇镜》的书出现在我的眼里，我非常好奇这本书为什么叫梦游奇镜，我就把它买回家，一到家我就坐在沙发上看，明知道自己的学校作业，课外班作业都没有写完，还是看了好长时间，妈妈让我快去写作业，我只好去写了，写了一会儿我又偷偷地把书拿出来津津有味的读起来，这时，我突然听到外面有脚步声传来，我就又把书放在作业下面，妈妈来了生气的对我说：“作业写完了吗？就在那看课外书，过了半个小时了才写了两页，再过半个小时，我来检查。”我只好把作业写完再看，要不然还要被妈妈吵。我等到妈妈来检查的时候说：“作业写完了”，妈妈说：“这么多空的也能叫写完了，给我补。”我还是坐在那里，我想了一办法，把书先放在床上，然后以最快的速度把作业补完了，然后去告诉妈妈：“我的作业补完了，我早上没睡好，能不能再去睡一会儿？”妈妈同意了，我就躺在床上，又看了起来，30分钟、50分钟、一个半小时，不知不觉我已看了好长时间，直到妈妈来叫我的时候，我才停止。', 'www.taobao.com', NULL, '13855412541', '负责人二', '2020-12-17 00:44:12', NULL, NULL, NULL, NULL, '3', '', '5fdaa9fa60f7c072cdce052e');

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
INSERT INTO `tb_score` VALUES ('5fce4361f1006b30ed78015b', '5fc3b8e7f100db4e740103e4', '2021', '2222', '5fc3e5c5f1000289792a0874', NULL, NULL, NULL);
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
