/*
Navicat MySQL Data Transfer

Source Server         : SecondStageProgram
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : course_manager

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-12-17 12:20:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for askcourse
-- ----------------------------
DROP TABLE IF EXISTS `askcourse`;
CREATE TABLE `askcourse` (
  `askcou_id` int(11) NOT NULL AUTO_INCREMENT,
  `askcou_tea_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `askcou_cou_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`askcou_id`),
  KEY `askcou_tea_num` (`askcou_tea_num`),
  KEY `askcou_cou_num` (`askcou_cou_num`),
  CONSTRAINT `askcourse_ibfk_1` FOREIGN KEY (`askcou_tea_num`) REFERENCES `teacherinfo` (`tea_num`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `askcourse_ibfk_2` FOREIGN KEY (`askcou_cou_num`) REFERENCES `courseinfo` (`cou_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of askcourse
-- ----------------------------
INSERT INTO `askcourse` VALUES ('7', '16011001', '1611201');

-- ----------------------------
-- Table structure for courseinfo
-- ----------------------------
DROP TABLE IF EXISTS `courseinfo`;
CREATE TABLE `courseinfo` (
  `cou_id` int(11) DEFAULT NULL,
  `cou_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '课程号',
  `cou_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '课程名',
  `cou_xs` int(10) DEFAULT NULL COMMENT '学时',
  `cou_redit` int(10) DEFAULT NULL COMMENT '学分',
  `cou_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '课程性质',
  `cou_term` int(11) NOT NULL COMMENT '学期',
  `cou_xxknum` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '先行课程',
  PRIMARY KEY (`cou_num`),
  KEY `cou_term` (`cou_term`,`cou_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of courseinfo
-- ----------------------------
INSERT INTO `courseinfo` VALUES ('1', '1602001', '思修', '54', '3', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('2', '1602002', '中国近代史纲要', '36', '2', '必修', '2', '');
INSERT INTO `courseinfo` VALUES ('3', '1602003', '马克思主义基本原理概论', '54', '3', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('4', '1602004', '毛概', '108', '6', '必修', '2', null);
INSERT INTO `courseinfo` VALUES ('5', '1605001', '大学英语1', '72', '4', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('6', '1605002', '大学英语2', '72', '4', '必修', '2', null);
INSERT INTO `courseinfo` VALUES ('11', '1611101', '高数1', '108', '6', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('12', '1611102', '高数2', '72', '4', '必修', '2', '1611101');
INSERT INTO `courseinfo` VALUES ('13', '1611103', '高数3', '72', '4', '必修', '1', '1611102');
INSERT INTO `courseinfo` VALUES ('14', '1611104', '线性代数', '72', '4', '必修', '2', '1611101');
INSERT INTO `courseinfo` VALUES ('15', '1611105', '概率统计', '72', '4', '必修', '2', '1611101');
INSERT INTO `courseinfo` VALUES ('16', '1611106', '信息技术导论', '72', '2', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('17', '1611107', 'c语言程序设计', '108', '5', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('18', '1611201', '数字电路', '90', '5', '必修', '2', null);
INSERT INTO `courseinfo` VALUES ('19', '1611202', '数据结构', '108', '5', '必修', '2', '1611107');
INSERT INTO `courseinfo` VALUES ('20', '1613201', '操作系统', '108', '5', '必修', '1', '1611202');
INSERT INTO `courseinfo` VALUES ('21', '1613202', '离散数学', '72', '4', '必修', '2', '1611101');
INSERT INTO `courseinfo` VALUES ('22', '1613203', '数据库', '72', '4', '必修', '2', '1611202');
INSERT INTO `courseinfo` VALUES ('23', '1613204', 'OOP', '72', '3', '必修', '1', '1611107');
INSERT INTO `courseinfo` VALUES ('24', '1613205', '计算机网络', '72', '4', '必修', '1', '1613201');
INSERT INTO `courseinfo` VALUES ('25', '1613206', '计算机组成原理', '72', '4', '必修', '1', '1611201');
INSERT INTO `courseinfo` VALUES ('26', '1613302', 'JAVA', '108', '5', '选修', '2', '1611107');
INSERT INTO `courseinfo` VALUES ('27', '1613303', '软件工程', '72', '4', '选修', '1', null);
INSERT INTO `courseinfo` VALUES ('28', '1613306', '算法设计和分析', '54', '3', '选修', '1', '1611202');
INSERT INTO `courseinfo` VALUES ('29', '1613309', '人工智能导论', '36', '2', '选修', '1', null);
INSERT INTO `courseinfo` VALUES ('30', '1613318', '计算方法', '90', '4', '选修', '1', '1611104');
INSERT INTO `courseinfo` VALUES ('31', '1613328', '数据库实用技术', '72', '3', '选修', '1', '1613203');
INSERT INTO `courseinfo` VALUES ('7', '1615001', '大学体育1', '26', '1', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('8', '1615002', '大学体育2', '26', '1', '必修', '2', null);
INSERT INTO `courseinfo` VALUES ('9', '1615003', '大学体育3', '26', '1', '必修', '1', null);
INSERT INTO `courseinfo` VALUES ('10', '1615004', '大学体育4', '26', '1', '必修', '2', '10003');

-- ----------------------------
-- Table structure for majorplaninfo
-- ----------------------------
DROP TABLE IF EXISTS `majorplaninfo`;
CREATE TABLE `majorplaninfo` (
  `majorplan_id` int(11) DEFAULT NULL,
  `majorplan_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '专业编号',
  `majorplan_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '专业名',
  `majorplan_grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '所属年级',
  `majorplan_comcre` int(10) DEFAULT NULL COMMENT '必修学分',
  `majorplan_elcre` int(10) DEFAULT NULL COMMENT '选修学分',
  PRIMARY KEY (`majorplan_num`,`majorplan_grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of majorplaninfo
-- ----------------------------
INSERT INTO `majorplaninfo` VALUES ('1', 'mjp11001', '计算机科学与技术', '2017', '60', '100');
INSERT INTO `majorplaninfo` VALUES ('2', 'mjp11002', '通信工程', '2017', '60', '100');
INSERT INTO `majorplaninfo` VALUES ('3', 'mjp11003', '物联网工程', '2017', '60', '100');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticecntent` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `noticetime` date DEFAULT NULL,
  PRIMARY KEY (`noticecntent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('version 1.0 公测版', '2019-11-29');
INSERT INTO `notice` VALUES ('version 1.1 增加了一些功能', '2019-11-30');
INSERT INTO `notice` VALUES ('选课系统上线啦！！！', '2019-11-28');

-- ----------------------------
-- Table structure for ordercourseinfo
-- ----------------------------
DROP TABLE IF EXISTS `ordercourseinfo`;
CREATE TABLE `ordercourseinfo` (
  `ordcou_id` int(11) DEFAULT NULL COMMENT 'id',
  `ordcou_major_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业号',
  `ordcou_grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级',
  `ordcou_year` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学年',
  `ordcou_term` int(11) NOT NULL DEFAULT '0' COMMENT '学期',
  `ordcou_cou_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '课程号',
  `ordcou_tea_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '教师工号',
  `ordcou_max` int(10) NOT NULL COMMENT '学生容量',
  `ordcou_qzz` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '起止周',
  `ordcou_teatime` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '上课时间',
  `ordcou_teaplace` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '上课地点',
  PRIMARY KEY (`ordcou_tea_num`,`ordcou_cou_num`,`ordcou_grade`,`ordcou_term`,`ordcou_year`,`ordcou_teatime`),
  KEY `ordcou_major_num` (`ordcou_major_num`,`ordcou_grade`,`ordcou_term`,`ordcou_max`,`ordcou_year`),
  KEY `ordcou_teatime` (`ordcou_teatime`),
  KEY `ordcou_teaplace` (`ordcou_teaplace`),
  KEY `ordcou_tea_num` (`ordcou_tea_num`,`ordcou_cou_num`,`ordcou_grade`,`ordcou_term`,`ordcou_year`,`ordcou_teatime`),
  KEY `ordcou_tea_num_2` (`ordcou_tea_num`,`ordcou_cou_num`,`ordcou_grade`,`ordcou_term`,`ordcou_year`,`ordcou_teatime`,`ordcou_teaplace`),
  KEY `ordercourseinfo_ibfk_4` (`ordcou_max`,`ordcou_teaplace`),
  KEY `ordercourseinfo_ibfk_5` (`ordcou_major_num`,`ordcou_grade`,`ordcou_year`,`ordcou_cou_num`),
  KEY `ordercourseinfo_ibfk_7` (`ordcou_term`,`ordcou_cou_num`),
  CONSTRAINT `ordercourseinfo_ibfk_1` FOREIGN KEY (`ordcou_tea_num`, `ordcou_cou_num`) REFERENCES `teacouinfo` (`teacou_teanum`, `teacou_conum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordercourseinfo_ibfk_3` FOREIGN KEY (`ordcou_teatime`) REFERENCES `showtime` (`classtime`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordercourseinfo_ibfk_5` FOREIGN KEY (`ordcou_major_num`, `ordcou_grade`, `ordcou_year`, `ordcou_cou_num`) REFERENCES `termplaninfo` (`tp_majornum`, `tp_grade`, `tp_year`, `tp_coursenum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordercourseinfo_ibfk_7` FOREIGN KEY (`ordcou_term`, `ordcou_cou_num`) REFERENCES `courseinfo` (`cou_term`, `cou_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ordercourseinfo
-- ----------------------------
INSERT INTO `ordercourseinfo` VALUES ('1', 'mjp11001', '2017', '2017-2018', '1', '1602001', '16011001', '70', '1-18', '1-3', 'B-121');
INSERT INTO `ordercourseinfo` VALUES ('0', 'mjp11001', '2017', '2017-2018', '1', '1602003', '16011001', '100', '1-18', '4-4', 'A-102');
INSERT INTO `ordercourseinfo` VALUES ('3', 'mjp11001', '2017', '2017-2018', '1', '1605001', '16011002', '100', '1-18', '2-2', 'C-125');
INSERT INTO `ordercourseinfo` VALUES ('0', 'mjp11001', '2017', '2017-2018', '1', '1611101', '16011003', '60', '1-18', '1-1', 'A-106');
INSERT INTO `ordercourseinfo` VALUES ('0', 'mjp11001', '2017', '2017-2018', '1', '1611101', '16011003', '60', '1-18', '3-1', 'A-106');
INSERT INTO `ordercourseinfo` VALUES ('0', 'mjp11001', '2017', '2017-2018', '1', '1611107', '16110006', '110', '1-18', '2-1', 'C-129');
INSERT INTO `ordercourseinfo` VALUES ('0', 'mjp11001', '2017', '2017-2018', '1', '1611106', '16111005', '110', '1-18', '1-3', 'A-106');

-- ----------------------------
-- Table structure for selcourse
-- ----------------------------
DROP TABLE IF EXISTS `selcourse`;
CREATE TABLE `selcourse` (
  `select_id` int(11) DEFAULT NULL,
  `select_year` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学年',
  `select_grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级',
  `select_term` int(11) NOT NULL COMMENT '学期',
  `seltea_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '教师工号',
  `selstu_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `selcourse_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程号',
  `sel_teatime` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程时间',
  `sel_teaplace` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`selstu_num`,`selcourse_num`,`sel_teatime`),
  KEY `seltea_num` (`seltea_num`,`selcourse_num`,`select_grade`,`select_term`,`select_year`),
  KEY `selstu_num` (`selstu_num`),
  KEY `selcourse_ibfk_3` (`seltea_num`,`selcourse_num`,`select_grade`,`select_term`,`select_year`,`sel_teatime`,`sel_teaplace`),
  CONSTRAINT `selcourse_ibfk_2` FOREIGN KEY (`selstu_num`) REFERENCES `studentinfo` (`stu_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `selcourse_ibfk_3` FOREIGN KEY (`seltea_num`, `selcourse_num`, `select_grade`, `select_term`, `select_year`, `sel_teatime`, `sel_teaplace`) REFERENCES `ordercourseinfo` (`ordcou_tea_num`, `ordcou_cou_num`, `ordcou_grade`, `ordcou_term`, `ordcou_year`, `ordcou_teatime`, `ordcou_teaplace`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of selcourse
-- ----------------------------
INSERT INTO `selcourse` VALUES ('1', '2017-2018', '2017', '1', '16011001', '2017110101', '1602001', '1-3', 'B-121');
INSERT INTO `selcourse` VALUES (null, '2017-2018', '2017', '1', '16011001', '2017110101', '1602003', '4-4', 'A-102');
INSERT INTO `selcourse` VALUES (null, '2017-2018', '2017', '1', '16011003', '2017110101', '1611101', '1-1', 'A-106');
INSERT INTO `selcourse` VALUES (null, '2017-2018', '2017', '1', '16011003', '2017110101', '1611101', '3-1', 'A-106');
INSERT INTO `selcourse` VALUES (null, '2017-2018', '2017', '1', '16111005', '2017110101', '1611106', '1-3', 'A-106');
INSERT INTO `selcourse` VALUES (null, '2017-2018', '2017', '1', '16011001', '2017110102', '1602001', '1-3', 'B-121');

-- ----------------------------
-- Table structure for showplace
-- ----------------------------
DROP TABLE IF EXISTS `showplace`;
CREATE TABLE `showplace` (
  `classplace` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `course_size` int(11) DEFAULT NULL,
  PRIMARY KEY (`classplace`),
  KEY `course_size` (`course_size`),
  KEY `course_size_2` (`course_size`,`classplace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of showplace
-- ----------------------------
INSERT INTO `showplace` VALUES ('B-121', '70');
INSERT INTO `showplace` VALUES ('C-125', '100');
INSERT INTO `showplace` VALUES ('A-102', '110');
INSERT INTO `showplace` VALUES ('A-106', '110');
INSERT INTO `showplace` VALUES ('C-129', '110');
INSERT INTO `showplace` VALUES ('E-106', '110');
INSERT INTO `showplace` VALUES ('D-210', '120');
INSERT INTO `showplace` VALUES ('B-112', '130');
INSERT INTO `showplace` VALUES ('C-127', '130');
INSERT INTO `showplace` VALUES ('D-221', '130');
INSERT INTO `showplace` VALUES ('D-323', '130');
INSERT INTO `showplace` VALUES ('D-301', '140');
INSERT INTO `showplace` VALUES ('A-109', '150');
INSERT INTO `showplace` VALUES ('C-128', '150');
INSERT INTO `showplace` VALUES ('D-310', '150');
INSERT INTO `showplace` VALUES ('B-111', '160');
INSERT INTO `showplace` VALUES ('A-108', '170');
INSERT INTO `showplace` VALUES ('C-123', '170');
INSERT INTO `showplace` VALUES ('A-103', '200');

-- ----------------------------
-- Table structure for showtime
-- ----------------------------
DROP TABLE IF EXISTS `showtime`;
CREATE TABLE `showtime` (
  `classtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`classtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of showtime
-- ----------------------------
INSERT INTO `showtime` VALUES ('1-1');
INSERT INTO `showtime` VALUES ('1-2');
INSERT INTO `showtime` VALUES ('1-3');
INSERT INTO `showtime` VALUES ('1-4');
INSERT INTO `showtime` VALUES ('2-1');
INSERT INTO `showtime` VALUES ('2-2');
INSERT INTO `showtime` VALUES ('2-3');
INSERT INTO `showtime` VALUES ('2-4');
INSERT INTO `showtime` VALUES ('3-1');
INSERT INTO `showtime` VALUES ('3-2');
INSERT INTO `showtime` VALUES ('3-3');
INSERT INTO `showtime` VALUES ('3-4');
INSERT INTO `showtime` VALUES ('4-1');
INSERT INTO `showtime` VALUES ('4-2');
INSERT INTO `showtime` VALUES ('4-3');
INSERT INTO `showtime` VALUES ('4-4');
INSERT INTO `showtime` VALUES ('5-1');
INSERT INTO `showtime` VALUES ('5-2');
INSERT INTO `showtime` VALUES ('5-3');
INSERT INTO `showtime` VALUES ('5-4');

-- ----------------------------
-- Table structure for studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo` (
  `stu_id` int(11) DEFAULT NULL,
  `stu_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '学号',
  `stu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_sex` char(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_birthday` date DEFAULT NULL,
  `stu_home` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_major` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_nation` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_policy` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_inschooldata` date DEFAULT NULL,
  `stu_state` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_grade` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`stu_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of studentinfo
-- ----------------------------
INSERT INTO `studentinfo` VALUES ('1', '2017110101', '张三', '12345', '男', '15061401201', '1998-02-05', '山东省济南市长清区大学路一号', '计算机科学与技术', '汉', '党员', '2017-09-03', '1', '2017');
INSERT INTO `studentinfo` VALUES ('2', '2017110102', '李四', '12345', '男', '15061401202', '1998-02-06', '山东省济南市长清区大学路一号', '通信工程', '汉', '党员', '2017-09-03', '1', '2017');
INSERT INTO `studentinfo` VALUES ('2', '2017110103', '王五', '12345', '男', '15061401203', '1998-02-08', '山东省济南市长清区大学路1号', '物联网工程', '汉', '党员', '2017-09-03', '1', '2017');

-- ----------------------------
-- Table structure for studentprize
-- ----------------------------
DROP TABLE IF EXISTS `studentprize`;
CREATE TABLE `studentprize` (
  `stprize_id` int(11) NOT NULL,
  `stprize_stu_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '学生学号',
  `stprize_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '获奖编号',
  `stprize_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '获奖类型',
  `stprize_des` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '获奖描述',
  PRIMARY KEY (`stprize_id`),
  KEY `stprize_stu_num` (`stprize_stu_num`),
  CONSTRAINT `studentprize_ibfk_1` FOREIGN KEY (`stprize_stu_num`) REFERENCES `studentinfo` (`stu_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of studentprize
-- ----------------------------

-- ----------------------------
-- Table structure for student_grade
-- ----------------------------
DROP TABLE IF EXISTS `student_grade`;
CREATE TABLE `student_grade` (
  `grade_id` int(11) DEFAULT NULL,
  `grade_cou_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程编号',
  `grade_stu_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生编号',
  `grade_c` double(10,0) DEFAULT NULL COMMENT '分数',
  `grade_credit` int(11) DEFAULT NULL COMMENT '学分',
  PRIMARY KEY (`grade_cou_num`,`grade_stu_num`),
  KEY `grade_cou_num` (`grade_cou_num`),
  KEY `grade_stu_num` (`grade_stu_num`),
  CONSTRAINT `student_grade_ibfk_1` FOREIGN KEY (`grade_cou_num`) REFERENCES `courseinfo` (`cou_num`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `student_grade_ibfk_2` FOREIGN KEY (`grade_stu_num`) REFERENCES `studentinfo` (`stu_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student_grade
-- ----------------------------
INSERT INTO `student_grade` VALUES (null, '1602001', '2017110101', '61', '3');
INSERT INTO `student_grade` VALUES (null, '1602001', '2017110102', '88', '3');

-- ----------------------------
-- Table structure for student_struction
-- ----------------------------
DROP TABLE IF EXISTS `student_struction`;
CREATE TABLE `student_struction` (
  `action_id` int(11) DEFAULT NULL,
  `action_stu_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `action_date` date NOT NULL COMMENT '异动日期',
  `action_des` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '异动描述',
  PRIMARY KEY (`action_stu_num`,`action_date`),
  KEY `action_stu_num` (`action_stu_num`),
  CONSTRAINT `student_struction_ibfk_1` FOREIGN KEY (`action_stu_num`) REFERENCES `studentinfo` (`stu_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student_struction
-- ----------------------------

-- ----------------------------
-- Table structure for teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo` (
  `tea_id` int(11) DEFAULT NULL,
  `tea_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '教师工号',
  `tea_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `tea_pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tea_sex` char(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `tea_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '联系方式',
  `tea_birthday` date DEFAULT NULL COMMENT '出生日期',
  `tea_home` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '家庭住址',
  `tea_major` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '专业',
  `tea_nation` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '民族',
  `tea_policy` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '政治面貌',
  `tea_inschooltime` date DEFAULT NULL COMMENT '入校日期',
  `tea_xl` char(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '学历',
  `tea_title` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`tea_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teacherinfo
-- ----------------------------
INSERT INTO `teacherinfo` VALUES ('1', '16011001', '张老师', '12345', '男', '17820654201', '1978-08-08', '山东省济南市天桥区', '计算机科学与技术', '汉', '中共党员', '2000-09-01', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('2', '16011002', '李老师', '12345', '男', '17820654202', '1978-09-08', '山东省济南市历下区', '通信工程', '汉', '中共党员', '2000-09-01', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('3', '16011003', '王老师', '12345', '女', '17812010086', '1978-10-08', '山东省济南市长清区', '物联网', '汉', '中共党员', '1999-09-09', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('4', '16011004', '孙老师', '12345', '女', '17811910086', '1977-02-05', '山东省济南市章丘区', '物联网', '汉', '中共党员', '1999-11-01', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('6', '16110006', '钱老师', '12345', '男', '17812010084', '1977-10-07', '山东省济南市', '计算机科学与技术', '汉', '中共党员', '2000-09-03', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('7', '16110007', '周老师', '12345', '女', '17811910095', '1978-09-03', '山东省济南市', '计算机科学与技术', '汉', '中共党员', '1999-09-02', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('8', '16110008', '吴老师', '12345', '女', '17811910052', '1978-08-06', '山东省济南市', '计算机科学与技术', '汉', '中共党员', '1999-09-02', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('9', '16110009', '郑老师', '12345', '男', '17815405156', '1977-09-02', '山东省济南市', '计算机科学与技术', '汉', '中共党员', '1999-09-02', '博士', '教授');
INSERT INTO `teacherinfo` VALUES ('1', '16111005', '赵老师', '12345', '男', '17812010085', '1978-10-08', '山东省济南市', '计算机科学与技术', '汉', '中共党员', '2000-09-03', '博士', '教授');

-- ----------------------------
-- Table structure for teacherprize
-- ----------------------------
DROP TABLE IF EXISTS `teacherprize`;
CREATE TABLE `teacherprize` (
  `teapri_id` int(11) DEFAULT NULL,
  `teapri_tea_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '教师工号',
  `teapri_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '获奖编号',
  `teapri_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '获奖类型',
  `teapri_des` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '获奖描述',
  PRIMARY KEY (`teapri_num`),
  KEY `teapri_tea_num` (`teapri_tea_num`),
  CONSTRAINT `teacherprize_ibfk_1` FOREIGN KEY (`teapri_tea_num`) REFERENCES `teacherinfo` (`tea_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teacherprize
-- ----------------------------

-- ----------------------------
-- Table structure for teachprop
-- ----------------------------
DROP TABLE IF EXISTS `teachprop`;
CREATE TABLE `teachprop` (
  `prop_id` int(11) DEFAULT NULL,
  `prop_tea_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '教师工号',
  `prop_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '著作名称',
  `prop_time` date DEFAULT NULL COMMENT '出版时间',
  `prop_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '作者排名',
  PRIMARY KEY (`prop_num`),
  KEY `prop_tea_num` (`prop_tea_num`),
  CONSTRAINT `teachprop_ibfk_1` FOREIGN KEY (`prop_tea_num`) REFERENCES `teacherinfo` (`tea_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teachprop
-- ----------------------------

-- ----------------------------
-- Table structure for teacouinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacouinfo`;
CREATE TABLE `teacouinfo` (
  `teacou_id` int(11) DEFAULT NULL,
  `teacou_teanum` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '教师工号',
  `teacou_conum` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '课程号',
  PRIMARY KEY (`teacou_teanum`,`teacou_conum`),
  KEY `teacou_conum` (`teacou_conum`),
  CONSTRAINT `teacouinfo_ibfk_1` FOREIGN KEY (`teacou_teanum`) REFERENCES `teacherinfo` (`tea_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacouinfo_ibfk_2` FOREIGN KEY (`teacou_conum`) REFERENCES `courseinfo` (`cou_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teacouinfo
-- ----------------------------
INSERT INTO `teacouinfo` VALUES ('1', '16011001', '1602001');
INSERT INTO `teacouinfo` VALUES ('1', '16011001', '1602002');
INSERT INTO `teacouinfo` VALUES ('1', '16011001', '1602003');
INSERT INTO `teacouinfo` VALUES ('1', '16011001', '1602004');
INSERT INTO `teacouinfo` VALUES ('1', '16011001', '1611102');
INSERT INTO `teacouinfo` VALUES ('1', '16011002', '1602002');
INSERT INTO `teacouinfo` VALUES ('1', '16011002', '1605001');
INSERT INTO `teacouinfo` VALUES ('1', '16011002', '1605002');
INSERT INTO `teacouinfo` VALUES ('1', '16011003', '1602004');
INSERT INTO `teacouinfo` VALUES ('1', '16011003', '1611101');
INSERT INTO `teacouinfo` VALUES ('1', '16011003', '1611102');
INSERT INTO `teacouinfo` VALUES ('1', '16011003', '1611103');
INSERT INTO `teacouinfo` VALUES ('1', '16011004', '1605001');
INSERT INTO `teacouinfo` VALUES ('1', '16011004', '1611104');
INSERT INTO `teacouinfo` VALUES ('1', '16011004', '1613318');
INSERT INTO `teacouinfo` VALUES ('1', '16110006', '1611107');
INSERT INTO `teacouinfo` VALUES ('1', '16110009', '1615001');
INSERT INTO `teacouinfo` VALUES ('1', '16110009', '1615002');
INSERT INTO `teacouinfo` VALUES ('1', '16110009', '1615003');
INSERT INTO `teacouinfo` VALUES ('1', '16110009', '1615004');
INSERT INTO `teacouinfo` VALUES ('1', '16111005', '1611106');

-- ----------------------------
-- Table structure for termplaninfo
-- ----------------------------
DROP TABLE IF EXISTS `termplaninfo`;
CREATE TABLE `termplaninfo` (
  `term_id` int(11) DEFAULT '0',
  `tp_term` int(11) NOT NULL DEFAULT '0' COMMENT '学期',
  `tp_year` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学年',
  `tp_majornum` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业号',
  `tp_grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级',
  `tp_coursenum` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程号',
  PRIMARY KEY (`tp_term`,`tp_coursenum`),
  UNIQUE KEY `term_id` (`term_id`),
  KEY `tp_majornum` (`tp_majornum`,`tp_grade`),
  KEY `tp_coursenum` (`tp_coursenum`),
  KEY `tp_majornum_2` (`tp_majornum`,`tp_grade`,`tp_year`,`tp_coursenum`),
  CONSTRAINT `termplaninfo_ibfk_1` FOREIGN KEY (`tp_majornum`, `tp_grade`) REFERENCES `majorplaninfo` (`majorplan_num`, `majorplan_grade`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `termplaninfo_ibfk_2` FOREIGN KEY (`tp_coursenum`) REFERENCES `courseinfo` (`cou_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of termplaninfo
-- ----------------------------
INSERT INTO `termplaninfo` VALUES ('1', '1', '2017-2018', 'mjp11001', '2017', '1602001');
INSERT INTO `termplaninfo` VALUES ('5', '1', '2017-2018', 'mjp11001', '2017', '1605001');
INSERT INTO `termplaninfo` VALUES ('7', '1', '2017-2018', 'mjp11001', '2017', '1611101');
INSERT INTO `termplaninfo` VALUES ('0', '1', '2017-2018', 'mjp11001', '2017', '1611106');
INSERT INTO `termplaninfo` VALUES ('10', '1', '2017-2018', 'mjp11001', '2017', '1611107');
INSERT INTO `termplaninfo` VALUES ('9', '1', '2017-2018', 'mjp11001', '2017', '1615001');
INSERT INTO `termplaninfo` VALUES ('2', '2', '2017-2018', 'mjp11001', '2017', '1602002');
INSERT INTO `termplaninfo` VALUES ('6', '2', '2017-2018', 'mjp11001', '2017', '1605002');
INSERT INTO `termplaninfo` VALUES ('8', '2', '2017-2018', 'mjp11001', '2017', '1615002');
INSERT INTO `termplaninfo` VALUES ('3', '3', '2017-2018', 'mjp11001', '2017', '1602003');
INSERT INTO `termplaninfo` VALUES ('4', '3', '2017-2018', 'mjp11001', '2017', '1602004');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_num` int(32) DEFAULT NULL,
  `user_pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_role` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '张三', '2017110101', '12345', '学生');
INSERT INTO `userinfo` VALUES ('2', '李四', '2017110102', '12345', '学生');
INSERT INTO `userinfo` VALUES ('3', '王五', '2017110103', '12345', '学生');
INSERT INTO `userinfo` VALUES ('4', '张老师', '16011001', '12345', '教师');
INSERT INTO `userinfo` VALUES ('5', '李老师', '16011002', '12345', '教师');
INSERT INTO `userinfo` VALUES ('6', '王老师', '16011003', '12345', '教师');
INSERT INTO `userinfo` VALUES ('7', '孙老师', '16011004', '12345', '教师');
INSERT INTO `userinfo` VALUES ('8', '张总管', '110110110', 'root', '管理员');
INSERT INTO `userinfo` VALUES ('9', '李总管', '110110111', 'root', '管理员');
INSERT INTO `userinfo` VALUES ('10', '王总管', '110110112', 'root', '管理员');
INSERT INTO `userinfo` VALUES ('11', '赵老师', '16111005', '12345', '教师');
INSERT INTO `userinfo` VALUES ('12', '钱老师', '16110006', '12345', '教师');
INSERT INTO `userinfo` VALUES ('13', '周老师', '16110007', '12345', '教师');
INSERT INTO `userinfo` VALUES ('14', '吴老师', '16110008', '12345', '教师');
INSERT INTO `userinfo` VALUES ('15', '郑老师', '16110009', '12345', '教师');

-- ----------------------------
-- View structure for arrg_cour_view
-- ----------------------------
DROP VIEW IF EXISTS `arrg_cour_view`;
CREATE ALGORITHM=MERGE DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `arrg_cour_view` AS select `termplaninfo`.`tp_year` AS `tp_year`,`termplaninfo`.`tp_majornum` AS `tp_majornum`,`termplaninfo`.`tp_grade` AS `tp_grade`,`termplaninfo`.`tp_coursenum` AS `tp_coursenum`,`courseinfo`.`cou_name` AS `cou_name`,`courseinfo`.`cou_xs` AS `cou_xs`,`courseinfo`.`cou_redit` AS `cou_redit`,`courseinfo`.`cou_type` AS `cou_type`,`courseinfo`.`cou_term` AS `cou_term` from (`termplaninfo` join `courseinfo` on((`termplaninfo`.`tp_coursenum` = `courseinfo`.`cou_num`))) ;

-- ----------------------------
-- Procedure structure for add_studentgrade
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_studentgrade`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_studentgrade`(in grade_counum varchar(32),in grade_c int,out g_credit int)
begin 
  set@credit= (select cou_redit from courseinfo where grade_counum = cou_num);
  if grade_c>60 then
  set g_credit = @credit;
  else 
  set g_credit = 0;
  end if;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for Sum_grade
-- ----------------------------
DROP FUNCTION IF EXISTS `Sum_grade`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `Sum_grade`(stunum varchar(32)) RETURNS int(11)
    READS SQL DATA
BEGIN
declare result int;
set result = (select sumgrade from(select grade_stu_num,sum(grade_c) sumgrade from student_grade group by grade_stu_num) as a where grade_stu_num = stunum);
return result;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_studentinfo`;
DELIMITER ;;
CREATE TRIGGER `trigger_studentinfo` AFTER INSERT ON `studentinfo` FOR EACH ROW begin
 insert into userinfo values(null,new.stu_name,new.stu_num,new.stu_pwd,'学生');
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_delete_studentinfo`;
DELIMITER ;;
CREATE TRIGGER `trigger_delete_studentinfo` AFTER DELETE ON `studentinfo` FOR EACH ROW begin
 delete from userinfo where user_num = old.stu_num;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_studentgrade`;
DELIMITER ;;
CREATE TRIGGER `trigger_studentgrade` BEFORE INSERT ON `student_grade` FOR EACH ROW begin
call add_studentgrade(new.grade_cou_num,new.grade_c,@g_credit);
 set new.grade_credit = @g_credit;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_teacherinfo`;
DELIMITER ;;
CREATE TRIGGER `trigger_teacherinfo` AFTER INSERT ON `teacherinfo` FOR EACH ROW begin
 insert into userinfo values(null,new.tea_name,new.tea_num,new.tea_pwd,'教师');
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_delete_teacherinfo`;
DELIMITER ;;
CREATE TRIGGER `trigger_delete_teacherinfo` AFTER DELETE ON `teacherinfo` FOR EACH ROW begin
 delete from userinfo where user_num = old.tea_num;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_teacouinfo`;
DELIMITER ;;
CREATE TRIGGER `trigger_teacouinfo` AFTER INSERT ON `teacouinfo` FOR EACH ROW begin
 delete from askcourse where askcou_tea_num = new.teacou_teanum and askcou_cou_num = new.teacou_conum;
end
;;
DELIMITER ;
