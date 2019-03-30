/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 50168
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 50168
 File Encoding         : 65001

 Date: 27/03/2019 21:09:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_name` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123456', '张三');
INSERT INTO `user` VALUES (2, 'lisi', '123456', '李四');
INSERT INTO `user` VALUES (3, 'wangwu', '123456', '王五');
INSERT INTO `user` VALUES (4, 'zhangwei', '123456', '张伟');

SET FOREIGN_KEY_CHECKS = 1;
