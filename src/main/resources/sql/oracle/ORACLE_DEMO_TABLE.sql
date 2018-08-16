CREATE TABLE "ORACLE_DEMO_TABLE" (
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
-- Indexes structure for table ORACLE_DEMO_TABLE
-- ----------------------------

-- ----------------------------
-- Checks structure for table ORACLE_DEMO_TABLE
-- ----------------------------
ALTER TABLE "ORACLE_DEMO_TABLE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table ORACLE_DEMO_TABLE
-- ----------------------------
ALTER TABLE "ORACLE_DEMO_TABLE" ADD PRIMARY KEY ("ID");
