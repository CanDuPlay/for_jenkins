/*
Navicat MySQL Data Transfer

Source Server         : 230
Source Server Version : 50610
Source Host           : 192.168.100.230:3306
Source Database       : ganzhoubaobiao

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2019-04-10 18:21:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_data_collect
-- ----------------------------
CREATE TABLE `t_data_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `instruction` varchar(500) DEFAULT NULL COMMENT '说明',
  `file_url` varchar(500) DEFAULT NULL COMMENT '文件路径',
  `remind_way` varchar(20) DEFAULT NULL COMMENT '提醒方式',
  `remind_content` varchar(500) DEFAULT NULL COMMENT '提醒内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_collect
-- ----------------------------

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
CREATE TABLE `t_department` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级组织机构ID',
  `school_id` varchar(32) DEFAULT NULL COMMENT '学校标识号',
  `district_id` varchar(32) DEFAULT NULL COMMENT '校区标识号',
  `faculty_id` varchar(32) DEFAULT NULL COMMENT '学部标识号',
  `department_name` varchar(60) DEFAULT NULL COMMENT '组织机构名称',
  `order_no` int(11) DEFAULT NULL COMMENT '排序标识（最小值排最前）',
  `del_flag` int(11) DEFAULT NULL COMMENT '作废标记(1-启用 0-作废)',
  `input_time` datetime DEFAULT NULL COMMENT '录入时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of t_department
-- ----------------------------

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
CREATE TABLE `t_dictionary` (
  `code` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `pcode` varchar(10) DEFAULT NULL,
  `town_type` varchar(10) DEFAULT NULL COMMENT '区县类别',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
INSERT INTO `t_dictionary` VALUES ('100', '区县', '1', null);
INSERT INTO `t_dictionary` VALUES ('100001', '章贡区', '100', '103001');
INSERT INTO `t_dictionary` VALUES ('100002', '赣州经开区', '100', '103001');
INSERT INTO `t_dictionary` VALUES ('100003', '南康区', '100', '103001');
INSERT INTO `t_dictionary` VALUES ('100004', '赣县区', '100', '103001');
INSERT INTO `t_dictionary` VALUES ('100005', '龙南县', '100', '103001');
INSERT INTO `t_dictionary` VALUES ('100006', '信丰县', '100', '103002');
INSERT INTO `t_dictionary` VALUES ('100007', '宁都县', '100', '103002');
INSERT INTO `t_dictionary` VALUES ('100008', '于都县', '100', '103002');
INSERT INTO `t_dictionary` VALUES ('100009', '兴国县', '100', '103002');
INSERT INTO `t_dictionary` VALUES ('100010', '会昌县', '100', '103002');
INSERT INTO `t_dictionary` VALUES ('100011', '瑞金市', '100', '103002');
INSERT INTO `t_dictionary` VALUES ('100012', '蓉江新区', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100013', '大余县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100014', '上犹县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100015', '崇义县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100016', '安远县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100017', '定南县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100018', '全南县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100019', '寻乌县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('100020', '石城县', '100', '103003');
INSERT INTO `t_dictionary` VALUES ('101', '学校类型', '1', null);
INSERT INTO `t_dictionary` VALUES ('101001', '幼儿园', '101', null);
INSERT INTO `t_dictionary` VALUES ('101002', '小学', '101', null);
INSERT INTO `t_dictionary` VALUES ('101003', '九年一贯制', '101', null);
INSERT INTO `t_dictionary` VALUES ('101004', '初中', '101', null);
INSERT INTO `t_dictionary` VALUES ('101005', '普通高中', '101', null);
INSERT INTO `t_dictionary` VALUES ('101006', '职业学校', '101', null);
INSERT INTO `t_dictionary` VALUES ('102', '项目阶段', '1', null);
INSERT INTO `t_dictionary` VALUES ('102001', '未开工', '102', null);
INSERT INTO `t_dictionary` VALUES ('10200101', '征地', '102001', null);
INSERT INTO `t_dictionary` VALUES ('10200102', '设计', '102001', null);
INSERT INTO `t_dictionary` VALUES ('10200103', '图审', '102001', null);
INSERT INTO `t_dictionary` VALUES ('10200104', '预算审核', '102001', null);
INSERT INTO `t_dictionary` VALUES ('10200105', '开标', '102001', null);
INSERT INTO `t_dictionary` VALUES ('10200106', '施工许可', '102001', null);
INSERT INTO `t_dictionary` VALUES ('102002', '开工进行中', '102', null);
INSERT INTO `t_dictionary` VALUES ('10200201', '主体开工', '102002', null);
INSERT INTO `t_dictionary` VALUES ('10200202', '装修', '102002', null);
INSERT INTO `t_dictionary` VALUES ('10200203', '完工', '102002', null);
INSERT INTO `t_dictionary` VALUES ('10200204', '竣工验收', '102002', null);
INSERT INTO `t_dictionary` VALUES ('102003', '可投入使用', '102', null);
INSERT INTO `t_dictionary` VALUES ('10200301', '投入使用', '102003', null);
INSERT INTO `t_dictionary` VALUES ('103', '区县类别', '1', null);
INSERT INTO `t_dictionary` VALUES ('103001', '一类县', '103', null);
INSERT INTO `t_dictionary` VALUES ('103002', '二类县', '103', null);
INSERT INTO `t_dictionary` VALUES ('103003', '三类县', '103', null);

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '操作人姓名',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作人id',
  `content` varchar(200) DEFAULT NULL COMMENT '操作内容',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `type` int(11) DEFAULT NULL COMMENT '操作类型',
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_procedure
-- ----------------------------
CREATE TABLE `t_procedure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '办理程序名称',
  `alias_name` varchar(200) DEFAULT NULL COMMENT '流程别名',
  `department` varchar(200) DEFAULT NULL COMMENT '办理部门',
  `material` varchar(200) DEFAULT NULL COMMENT '需要的材料',
  `time_limit` int(11) DEFAULT NULL COMMENT '办理工作时限',
  `cost` float DEFAULT NULL COMMENT '报建费用',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `t_procedure_type_id` int(11) DEFAULT NULL COMMENT '程序流程类型id',
  `pid` int(11) DEFAULT NULL COMMENT '父流程id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='程序流程表';

-- ----------------------------
-- Records of t_procedure
-- ----------------------------

-- ----------------------------
-- Table structure for t_procedure_type
-- ----------------------------
CREATE TABLE `t_procedure_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='程序流程类型表';

-- ----------------------------
-- Records of t_procedure_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL COMMENT '项目名称',
  `start_time` datetime DEFAULT NULL COMMENT '开工时间',
  `end_time` datetime DEFAULT NULL COMMENT '竣工时间',
  `contacts` varchar(20) DEFAULT NULL COMMENT '总负责人',
  `invest` float DEFAULT NULL COMMENT '规划总投资',
  `finish_time` datetime DEFAULT NULL COMMENT '截至申报时间',
  `procedure_type` int(11) DEFAULT NULL COMMENT '流程类型',
  `status` int(1) DEFAULT NULL COMMENT '项目状态1未开始2进行中3已结束4已封存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目表';

-- ----------------------------
-- Records of t_project
-- ----------------------------

-- ----------------------------
-- Table structure for t_project_schedule
-- ----------------------------
CREATE TABLE `t_project_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_project_stage` int(11) DEFAULT NULL COMMENT '项目阶段id',
  `name` varchar(200) DEFAULT NULL COMMENT '进度名称',
  `t_procedure_id` int(11) DEFAULT NULL COMMENT '流程id',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8 COMMENT='项目进度表';

-- ----------------------------
-- Records of t_project_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for t_project_school
-- ----------------------------
CREATE TABLE `t_project_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `school_name` varchar(100) DEFAULT NULL COMMENT '学校名称',
  `school_type` varchar(10) DEFAULT NULL COMMENT '学校类型（字典）',
  `area` float DEFAULT NULL COMMENT '建筑面积',
  `new_build` int(11) DEFAULT NULL COMMENT '新建学位',
  `invest` float DEFAULT NULL COMMENT '总投资',
  `start_year` varchar(7) DEFAULT NULL COMMENT '开工年份',
  `end_year` varchar(7) DEFAULT NULL COMMENT '竣工年份',
  `now_schedule` int(11) DEFAULT NULL COMMENT '当前项目进度',
  `reward` varchar(200) DEFAULT NULL COMMENT '重点说明情况',
  `is_start` int(11) DEFAULT NULL COMMENT '是否开工',
  `town_id` varchar(20) DEFAULT NULL COMMENT '区县id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 COMMENT='项目学校表';

-- ----------------------------
-- Records of t_project_school
-- ----------------------------

-- ----------------------------
-- Table structure for t_project_stage
-- ----------------------------
CREATE TABLE `t_project_stage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `name` varchar(500) DEFAULT NULL COMMENT '阶段名称',
  `is_daopai` int(11) DEFAULT NULL COMMENT '是否需要设置倒排时间',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='项目阶段表';

-- ----------------------------
-- Records of t_project_stage
-- ----------------------------

-- ----------------------------
-- Table structure for t_project_town
-- ----------------------------
CREATE TABLE `t_project_town` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `town_id` varchar(20) DEFAULT NULL COMMENT '区县id',
  `accountability_unit` varchar(100) DEFAULT NULL COMMENT '责任单位',
  `contacts` varchar(20) DEFAULT NULL COMMENT '联系领导',
  `start_time` datetime DEFAULT NULL COMMENT '项目开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '项目结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目区县表';

-- ----------------------------
-- Records of t_project_town
-- ----------------------------

-- ----------------------------
-- Table structure for t_project_town_schedule
-- ----------------------------
CREATE TABLE `t_project_town_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `town_id` varchar(10) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '计划开始时间',
  `finish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_town_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `if_check` int(11) DEFAULT NULL COMMENT '是否需要审核',
  `status` int(11) DEFAULT NULL COMMENT '审批通过状态标识',
  `type` int(11) DEFAULT NULL COMMENT '用户类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110010 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('110001', '区县校建办', '1', '1', '1');
INSERT INTO `t_role` VALUES ('110002', '区县校建办主任', null, '2', '1');
INSERT INTO `t_role` VALUES ('110003', '区县分管局长', null, '3', '1');
INSERT INTO `t_role` VALUES ('110004', '区县局长', null, '4', '1');
INSERT INTO `t_role` VALUES ('110005', '市局科室', null, '5', '2');
INSERT INTO `t_role` VALUES ('110006', '市局科室负责人', null, '6', '2');
INSERT INTO `t_role` VALUES ('110007', '市局分管领导', null, '7', '2');
INSERT INTO `t_role` VALUES ('110008', '市局局长', null, '8', '2');
INSERT INTO `t_role` VALUES ('110009', '市分管领导', null, '9', '3');

-- ----------------------------
-- Table structure for t_school_file
-- ----------------------------
CREATE TABLE `t_school_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_project_school_id` int(11) DEFAULT NULL COMMENT '项目学校表id',
  `file_url` varchar(100) DEFAULT NULL COMMENT '文件路径',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `type` int(11) DEFAULT NULL COMMENT '1学校图片2进度图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8 COMMENT='学校资质表';

-- ----------------------------
-- Records of t_school_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_school_inverted
-- ----------------------------
CREATE TABLE `t_school_inverted` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_project_school_id` int(11) DEFAULT NULL COMMENT '项目学校id',
  `name` varchar(200) DEFAULT NULL COMMENT '倒排工作名称',
  `time` datetime DEFAULT NULL COMMENT '计划时间',
  `is_finish` int(11) DEFAULT NULL COMMENT '是否完成',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校倒排表';

-- ----------------------------
-- Records of t_school_inverted
-- ----------------------------

-- ----------------------------
-- Table structure for t_school_schedule
-- ----------------------------
CREATE TABLE `t_school_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_project_school_id` int(11) DEFAULT NULL COMMENT '项目学校表id',
  `t_project_schedule_id` int(11) DEFAULT NULL COMMENT '项目进度id',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `status` int(11) DEFAULT NULL COMMENT '审批流程状态',
  `invest` float DEFAULT NULL COMMENT '投资',
  `reward` varchar(200) DEFAULT NULL COMMENT '重点说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='学校项目进度表';

-- ----------------------------
-- Records of t_school_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for t_sync
-- ----------------------------
CREATE TABLE `t_sync` (
  `syncStatus` int(1) DEFAULT '0' COMMENT '全量同步状态（0：未同步，1：已同步）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全量同步状态表';

-- ----------------------------
-- Records of t_sync
-- ----------------------------
INSERT INTO `t_sync` VALUES ('0');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
CREATE TABLE `t_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `teacher_no` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `global_id` varchar(60) DEFAULT NULL,
  `pinyin` varchar(50) DEFAULT NULL,
  `phone` varchar(60) DEFAULT NULL,
  `rowid` varchar(60) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL COMMENT '作废标识(1-启用 0-废除)',
  PRIMARY KEY (`id`),
  KEY `t_tea_index` (`global_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for t_teacher_department_ref
-- ----------------------------
CREATE TABLE `t_teacher_department_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '教职工标识号',
  `leader_flag` int(11) DEFAULT NULL COMMENT '领导标识(1-是 0-否)',
  `department_id` varchar(32) DEFAULT NULL COMMENT '组织机构标识号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教职工组织机构关系表';

-- ----------------------------
-- Records of t_teacher_department_ref
-- ----------------------------

-- ----------------------------
-- Table structure for t_town_submit
-- ----------------------------
CREATE TABLE `t_town_submit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `town_id` varchar(10) DEFAULT NULL COMMENT '区县id',
  `is_submit` int(11) DEFAULT NULL COMMENT '是否提交',
  `file_url` varchar(50) DEFAULT NULL COMMENT '文件路径',
  `submit_time` datetime DEFAULT NULL COMMENT '提交时间',
  `collect_id` int(11) DEFAULT NULL,
  `remind_count` int(11) DEFAULT NULL COMMENT '提醒次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_town_submit
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `town_id` varchar(10) DEFAULT NULL COMMENT '区县id（字典）',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_year_sort
-- ----------------------------
CREATE TABLE `t_year_sort` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `town_id` varchar(10) DEFAULT NULL,
  `town_name` varchar(20) DEFAULT NULL,
  `town_type` varchar(10) DEFAULT NULL,
  `town_type_name` varchar(20) DEFAULT NULL,
  `area` float DEFAULT NULL COMMENT '建筑面积',
  `invest` float DEFAULT NULL COMMENT '投资',
  `score` float DEFAULT NULL COMMENT '评分',
  `sort1` int(11) DEFAULT NULL,
  `all_invest` float DEFAULT NULL COMMENT '累计投资',
  `sort2` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL COMMENT '项目总数',
  `kaigong` int(11) DEFAULT NULL COMMENT '开工数量',
  `kaigonglv` float DEFAULT NULL COMMENT '开工率',
  `sort3` int(11) DEFAULT NULL,
  `weikaigong` int(11) DEFAULT NULL COMMENT '未开工数量',
  `year` varchar(4) DEFAULT NULL COMMENT '年度',
  `project_id` int(11) DEFAULT NULL,
  `types` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_year_sort
-- ----------------------------
