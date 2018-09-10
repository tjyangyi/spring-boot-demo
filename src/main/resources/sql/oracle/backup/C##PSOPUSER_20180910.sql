/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : 10.2.108.238_C##PSOPUSER_123456
Source Server Version : 120200
Source Host           : 10.2.108.238:1521
Source Schema         : C##PSOPUSER

Target Server Type    : ORACLE
Target Server Version : 120200
File Encoding         : 65001

Date: 2018-09-10 10:30:40
*/


-- ----------------------------
-- Table structure for ORACLE_DEMO_TABLE
-- ----------------------------
DROP TABLE  "ORACLE_DEMO_TABLE";
CREATE TABLE  "ORACLE_DEMO_TABLE" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"CONTENT" VARCHAR2(255 BYTE) NULL ,
"NUM" NUMBER NULL ,
"CREATE_TIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of ORACLE_DEMO_TABLE
-- ----------------------------
INSERT INTO  "ORACLE_DEMO_TABLE" VALUES ('8a82ec6b655cf2da01655cf41eda0000', '的', '1', null);
INSERT INTO  "ORACLE_DEMO_TABLE" VALUES ('1', '2', '3', TO_DATE('2018-08-07 22:56:03', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for SYS_PERMISSION
-- ----------------------------
DROP TABLE  "SYS_PERMISSION";
CREATE TABLE  "SYS_PERMISSION" (
"ID" NUMBER(10) NOT NULL ,
"AVAILABLE" NUMBER(1) NULL ,
"NAME" VARCHAR2(255 CHAR) NULL ,
"PARENT_ID" NUMBER(19) NULL ,
"PARENT_IDS" VARCHAR2(255 CHAR) NULL ,
"PERMISSION" VARCHAR2(255 CHAR) NULL ,
"RESOURCE_TYPE" VARCHAR2(255 CHAR) NULL ,
"URL" VARCHAR2(255 CHAR) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_PERMISSION
-- ----------------------------
INSERT INTO  "SYS_PERMISSION" VALUES ('1', '0', '用户管理', '0', '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO  "SYS_PERMISSION" VALUES ('2', '0', '用户添加', '1', '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO  "SYS_PERMISSION" VALUES ('3', '0', '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');

-- ----------------------------
-- Table structure for SYS_ROLE
-- ----------------------------
DROP TABLE  "SYS_ROLE";
CREATE TABLE  "SYS_ROLE" (
"ID" NUMBER(10) NOT NULL ,
"AVAILABLE" NUMBER(1) NULL ,
"DESCRIPTION" VARCHAR2(255 CHAR) NULL ,
"ROLE" VARCHAR2(255 CHAR) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------
INSERT INTO  "SYS_ROLE" VALUES ('1', '0', '管理员', 'admin');
INSERT INTO  "SYS_ROLE" VALUES ('2', '0', 'VIP会员', 'vip');
INSERT INTO  "SYS_ROLE" VALUES ('3', '1', 'test', 'test');

-- ----------------------------
-- Table structure for SYS_ROLE_PERMISSION
-- ----------------------------
DROP TABLE  "SYS_ROLE_PERMISSION";
CREATE TABLE  "SYS_ROLE_PERMISSION" (
"PERMISSION_ID" NUMBER(10) NOT NULL ,
"ROLE_ID" NUMBER(10) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_ROLE_PERMISSION
-- ----------------------------
INSERT INTO  "SYS_ROLE_PERMISSION" VALUES ('1', '1');
INSERT INTO  "SYS_ROLE_PERMISSION" VALUES ('1', '1');
INSERT INTO  "SYS_ROLE_PERMISSION" VALUES ('2', '1');
INSERT INTO  "SYS_ROLE_PERMISSION" VALUES ('3', '2');

-- ----------------------------
-- Table structure for SYS_USER_ROLE
-- ----------------------------
DROP TABLE  "SYS_USER_ROLE";
CREATE TABLE  "SYS_USER_ROLE" (
"ROLE_ID" NUMBER(10) NOT NULL ,
"ID" NUMBER(10) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_USER_ROLE
-- ----------------------------
INSERT INTO  "SYS_USER_ROLE" VALUES ('1', '1');

-- ----------------------------
-- Table structure for T_ATTENDANCE
-- ----------------------------
DROP TABLE  "T_ATTENDANCE";
CREATE TABLE  "T_ATTENDANCE" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"YEAR" VARCHAR2(8 BYTE) NULL ,
"MONTH" VARCHAR2(8 BYTE) NULL ,
"FILE_NAME" VARCHAR2(255 BYTE) NULL ,
"TITLE" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ATTENDANCE
-- ----------------------------

-- ----------------------------
-- Table structure for T_ATTENDANCE_DETAIL
-- ----------------------------
DROP TABLE  "T_ATTENDANCE_DETAIL";
CREATE TABLE  "T_ATTENDANCE_DETAIL" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"ATTENDACE_ID" VARCHAR2(255 BYTE) NULL ,
"USERNAME" VARCHAR2(16 BYTE) NULL ,
"REALNAME" VARCHAR2(32 BYTE) NULL ,
"CARD_NUMBER" VARCHAR2(32 BYTE) NULL ,
"A" NUMBER(38) NULL ,
"B" NUMBER(38) NULL ,
"C" NUMBER(38) NULL ,
"D" NUMBER(38) NULL ,
"E" NUMBER(38) NULL ,
"F" NUMBER(38) NULL ,
"G" NUMBER(38) NULL ,
"INDEX_NUMBER" NUMBER(38) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ATTENDANCE_DETAIL
-- ----------------------------

-- ----------------------------
-- Table structure for T_ATTENDANCE_RECORD
-- ----------------------------
DROP TABLE  "T_ATTENDANCE_RECORD";
CREATE TABLE  "T_ATTENDANCE_RECORD" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"USERNAME" VARCHAR2(255 BYTE) NULL ,
"LOGIN_IP" VARCHAR2(64 BYTE) NULL ,
"MODIFY_DETAIL_ID" VARCHAR2(255 BYTE) NULL ,
"PRE_B" NUMBER(38) NULL ,
"NEW_B" NUMBER(38) NULL ,
"PRE_C" NUMBER(38) NULL ,
"NEW_C" NUMBER(38) NULL ,
"PRE_G" NUMBER(38) NULL ,
"NEW_G" NUMBER(38) NULL ,
"MODIFY_TIME" DATE NULL ,
"TARGET_USERNAME" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ATTENDANCE_RECORD
-- ----------------------------

-- ----------------------------
-- Table structure for USER_INFO
-- ----------------------------
DROP TABLE  "USER_INFO";
CREATE TABLE  "USER_INFO" (
"ID" NUMBER(10) NOT NULL ,
"NAME" VARCHAR2(255 CHAR) NULL ,
"PASSWORD" VARCHAR2(255 CHAR) NULL ,
"SALT" VARCHAR2(255 CHAR) NULL ,
"STATE" NUMBER(3) NOT NULL ,
"USERNAME" VARCHAR2(255 CHAR) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of USER_INFO
-- ----------------------------
INSERT INTO  "USER_INFO" VALUES ('1', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0', 'admin');

-- ----------------------------
-- Sequence structure for HIBERNATE_SEQUENCE
-- ----------------------------
DROP SEQUENCE  "HIBERNATE_SEQUENCE";
CREATE SEQUENCE  "HIBERNATE_SEQUENCE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 21
 CACHE 20;

-- ----------------------------
-- Indexes structure for table ORACLE_DEMO_TABLE
-- ----------------------------

-- ----------------------------
-- Checks structure for table ORACLE_DEMO_TABLE
-- ----------------------------
ALTER TABLE  "ORACLE_DEMO_TABLE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table ORACLE_DEMO_TABLE
-- ----------------------------
ALTER TABLE  "ORACLE_DEMO_TABLE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table SYS_PERMISSION
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_PERMISSION
-- ----------------------------
ALTER TABLE  "SYS_PERMISSION" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_PERMISSION
-- ----------------------------
ALTER TABLE  "SYS_PERMISSION" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table SYS_ROLE
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_ROLE
-- ----------------------------
ALTER TABLE  "SYS_ROLE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_ROLE
-- ----------------------------
ALTER TABLE  "SYS_ROLE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table SYS_ROLE_PERMISSION
-- ----------------------------
ALTER TABLE  "SYS_ROLE_PERMISSION" ADD CHECK ("PERMISSION_ID" IS NOT NULL);
ALTER TABLE  "SYS_ROLE_PERMISSION" ADD CHECK ("ROLE_ID" IS NOT NULL);

-- ----------------------------
-- Checks structure for table SYS_USER_ROLE
-- ----------------------------
ALTER TABLE  "SYS_USER_ROLE" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE  "SYS_USER_ROLE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Indexes structure for table T_ATTENDANCE
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ATTENDANCE
-- ----------------------------
ALTER TABLE  "T_ATTENDANCE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE  "T_ATTENDANCE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ATTENDANCE
-- ----------------------------
ALTER TABLE  "T_ATTENDANCE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_ATTENDANCE_DETAIL
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ATTENDANCE_DETAIL
-- ----------------------------
ALTER TABLE  "T_ATTENDANCE_DETAIL" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE  "T_ATTENDANCE_DETAIL" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE  "T_ATTENDANCE_DETAIL" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ATTENDANCE_DETAIL
-- ----------------------------
ALTER TABLE  "T_ATTENDANCE_DETAIL" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_ATTENDANCE_RECORD
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ATTENDANCE_RECORD
-- ----------------------------
ALTER TABLE  "T_ATTENDANCE_RECORD" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE  "T_ATTENDANCE_RECORD" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ATTENDANCE_RECORD
-- ----------------------------
ALTER TABLE  "T_ATTENDANCE_RECORD" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table USER_INFO
-- ----------------------------

-- ----------------------------
-- Uniques structure for table USER_INFO
-- ----------------------------
ALTER TABLE  "USER_INFO" ADD UNIQUE ("USERNAME");

-- ----------------------------
-- Checks structure for table USER_INFO
-- ----------------------------
ALTER TABLE  "USER_INFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE  "USER_INFO" ADD CHECK ("STATE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table USER_INFO
-- ----------------------------
ALTER TABLE  "USER_INFO" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Foreign Key structure for table  "SYS_ROLE_PERMISSION"
-- ----------------------------
ALTER TABLE  "SYS_ROLE_PERMISSION" ADD FOREIGN KEY ("ROLE_ID") REFERENCES  "SYS_ROLE" ("ID");
ALTER TABLE  "SYS_ROLE_PERMISSION" ADD FOREIGN KEY ("PERMISSION_ID") REFERENCES  "SYS_PERMISSION" ("ID");

-- ----------------------------
-- Foreign Key structure for table  "SYS_USER_ROLE"
-- ----------------------------
ALTER TABLE  "SYS_USER_ROLE" ADD FOREIGN KEY ("ID") REFERENCES  "USER_INFO" ("ID");
ALTER TABLE  "SYS_USER_ROLE" ADD FOREIGN KEY ("ROLE_ID") REFERENCES  "SYS_ROLE" ("ID");
