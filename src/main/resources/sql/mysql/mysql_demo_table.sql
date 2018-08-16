/*
 Navicat Premium Data Transfer

 Source Server         : 10.2.108.238_root_Fhzz@123456
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 10.2.108.238:3306
 Source Schema         : medb

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 16/08/2018 23:50:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mysql_demo_table
-- ----------------------------
DROP TABLE IF EXISTS `mysql_demo_table`;
CREATE TABLE `mysql_demo_table`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `num` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
