-- 系统管理  相关的表

-- sys_area
/*
警告: 字段名可能非法 - type
*/
create table  `sys_area`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `parent_id`       INT not null comment '父级编号',
       `parent_ids`      VARCHAR(2000) not null comment '所有父级编号',
       `name`            VARCHAR(100) not null comment '名称',
       `sort`            INT not null comment '排序',
       `code`            VARCHAR(100) comment '区域编码',
       `type`            VARCHAR(1) comment '区域类型',
       `create_by`       INT not null comment '创建者',
       `create_date`     DATETIME not null comment '创建时间',
       `update_by`       INT not null comment '更新者',
       `update_date`     DATETIME not null comment '更新时间',
       `remarks`         VARCHAR(255) comment '备注信息',
       `del_flag`        VARCHAR(1) default 0 not null comment '删除标记',
primary key (`id`)
);
 
create index `IDX_sys_area_parent_id` on `sys_area`(`parent_id`);
create index `IDX_sys_area_del_flag` on `sys_area`(`del_flag`);
alter table `sys_area` comment= '区域表';

-- sys_dict
/*
警告: 字段名可能非法 - type
*/
create table  `sys_dict`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `value`           VARCHAR(100) not null comment '数据值',
       `label`           VARCHAR(100) not null comment '标签名',
       `type`            VARCHAR(100) not null comment '类型',
       `description`     VARCHAR(100) not null comment '描述',
       `sort`            INT not null comment '排序（升序）',
       `parent_id`       INT default 0 comment '父级编号',
       `create_by`       INT not null comment '创建者',
       `create_date`     DATETIME  not null comment '创建时间',
       `update_by`       INT not null comment '更新者',
       `update_date`     DATETIME  not null comment '更新时间',
       `remarks`         VARCHAR(255) comment '备注信息',
       `del_flag`        VARCHAR(1) default 0 not null comment '删除标记',
primary key (`id`)
);
 
create index `IDX_sys_dict_value` on `sys_dict`(`value`);
create index `IDX_sys_dict_label` on `sys_dict`(`label`);
create index `IDX_sys_dict_del_flag` on `sys_dict`(`del_flag`);
alter table `sys_dict` comment= '字典表';

-- sys_log
/*
警告: 字段名可能非法 - type
*/
create table  `sys_log`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `type`            VARCHAR(1) default 1 comment '日志类型',
       `title`           VARCHAR(255) comment '日志标题',
       `create_by`       VARCHAR(64) comment '创建者',
       `create_date`     DATETIME  comment '创建时间',
       `remote_addr`     VARCHAR(255) comment '操作IP地址',
       `user_agent`      VARCHAR(255) comment '用户代理',
       `request_uri`     VARCHAR(255) comment '请求URI',
       `method`          VARCHAR(5) comment '操作方式',
       `lose_time`       INT comment '用时',
       `params`          LONGBLOB comment '操作提交的数据',
       `exception`       LONGBLOB comment '异常信息'
,primary key (`id`)
);
 
create index `IDX_sys_log_type` on `sys_log`(`type`);
create index `IDX_sys_log_create_by` on `sys_log`(`create_by`);
create index `IDX_sys_log_create_date` on `sys_log`(`create_date`);
create index `IDX_sys_log_request_uri` on `sys_log`(`request_uri`);
alter table `sys_log` comment= '日志表';

-- sys_mdict
create table  `sys_mdict`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `parent_id`       INT not null comment '父级编号',
       `parent_ids`      VARCHAR(2000) not null comment '所有父级编号',
       `name`            VARCHAR(100) not null comment '名称',
       `sort`            INT not null comment '排序',
       `description`     VARCHAR(100) comment '描述',
       `create_by`       INT not null comment '创建者',
       `create_date`     DATETIME  not null comment '创建时间',
       `update_by`       INT not null comment '更新者',
       `update_date`     DATETIME  not null comment '更新时间',
       `remarks`         VARCHAR(255) comment '备注信息',
       `del_flag`        VARCHAR(1) default 0 not null comment '删除标记'
,primary key (`id`)
);
 
create index `IDX_sys_mdict_parent_id` on `sys_mdict`(`parent_id`);
create index `IDX_sys_mdict_del_flag` on `sys_mdict`(`del_flag`);
alter table `sys_mdict` comment= '多级字典表';

-- sys_menu
create table  `sys_menu`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `parent_id`       INT not null comment '父级编号',
       `parent_ids`      VARCHAR(2000) not null comment '所有父级编号',
       `name`            VARCHAR(100) not null comment '名称',
       `sort`            INT not null comment '排序',
       `href`            VARCHAR(2000) comment '链接',
       `target`          VARCHAR(20) comment '目标',
       `icon`            VARCHAR(100) comment '图标',
       `is_show`         VARCHAR(1) not null comment '是否在菜单中显示',
       `permission`      VARCHAR(200) comment '权限标识',
       `create_by`       INT not null comment '创建者',
       `create_date`     DATETIME  not null comment '创建时间',
       `update_by`       INT not null comment '更新者',
       `update_date`     DATETIME  not null comment '更新时间',
       `remarks`         VARCHAR(255) comment '备注信息',
       `del_flag`        VARCHAR(1) default 0 not null comment '删除标记',
        `type` 			 VARCHAR(1) comment '菜单类型'
       
,primary key (`id`)
);
 
create index `IDX_sys_menu_parent_id` on `sys_menu`(`parent_id`);
create index `IDX_sys_menu_del_flag` on `sys_menu`(`del_flag`);
alter table `sys_menu` comment= '菜单表';

-- sys_office
/*
警告: 字段名可能非法 - type
*/
create table  `sys_office`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `parent_id`       INT not null comment '父级编号',
       `parent_ids`      VARCHAR(2000) not null comment '所有父级编号',
       `name`            VARCHAR(100) not null comment '名称',
       `sort`            INT not null comment '排序',
       `area_id`         VARCHAR(64) not null comment '归属区域',
       `code`            VARCHAR(100) comment '区域编码',
       `type`            VARCHAR(1) not null comment '机构类型',
       `grade`           VARCHAR(1) not null comment '机构等级',
       `address`         VARCHAR(255) comment '联系地址',
       `zip_code`        VARCHAR(100) comment '邮政编码',
       `master`          VARCHAR(100) comment '负责人',
       `phone`           VARCHAR(200) comment '电话',
       `fax`             VARCHAR(200) comment '传真',
       `email`           VARCHAR(200) comment '邮箱',
       `USEABLE`         VARCHAR(64) comment '是否启用',
       `PRIMARY_PERSON`  VARCHAR(64) comment '主负责人',
       `DEPUTY_PERSON`   VARCHAR(64) comment '副负责人',
       `create_by`       INT not null comment '创建者',
       `create_date`     DATETIME  not null comment '创建时间',
       `update_by`       INT not null comment '更新者',
       `update_date`     DATETIME  not null comment '更新时间',
       `remarks`         VARCHAR(255) comment '备注信息',
       `del_flag`        VARCHAR(1) default 0 not null comment '删除标记'
,primary key (`id`)
);
 
create index `IDX_sys_office_parent_id` on `sys_office`(`parent_id`);
create index `IDX_sys_office_type` on `sys_office`(`type`);
create index `IDX_sys_office_del_flag` on `sys_office`(`del_flag`);
alter table `sys_office` comment= '机构表';

-- sys_role
create table  `sys_role`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `office_id`       VARCHAR(64) comment '归属机构',
       `name`            VARCHAR(100) not null comment '角色名称',
       `enname`          VARCHAR(255) comment '英文名称',
       `role_type`       VARCHAR(255) comment '角色类型',
       `data_scope`      VARCHAR(1) comment '数据范围',
       `is_sys`          VARCHAR(64) comment '是否系统数据',
       `useable`         VARCHAR(64) comment '是否可用',
       `create_by`       INT not null comment '创建者',
       `create_date`     DATETIME  not null comment '创建时间',
       `update_by`       INT not null comment '更新者',
       `update_date`     DATETIME  not null comment '更新时间',
       `remarks`         VARCHAR(255) comment '备注信息',
       `del_flag`        VARCHAR(1) default 0 not null comment '删除标记'
,primary key (`id`)
);
 
create index `IDX_sys_role_enname` on `sys_role`(`enname`);
create index `IDX_sys_role_del_flag` on `sys_role`(`del_flag`);
alter table `sys_role` comment= '角色表';

-- sys_role_menu
create table  `sys_role_menu`
(
       `role_id`         INT not null comment '角色编号',
       `menu_id`         INT not null comment '菜单编号'
);
alter  table `sys_role_menu`
       add constraint `PK_sys_role_menu_role_id` primary key (`role_id`,`menu_id`);
alter table `sys_role_menu` comment= '角色-菜单';

-- sys_role_office
create table  `sys_role_office`
(
       `role_id`         INT not null comment '角色编号',
       `office_id`       INT not null comment '机构编号'
);
alter  table `sys_role_office`
       add constraint `PK_sys_role_office_role_id` primary key (`role_id`,`office_id`);
alter table `sys_role_office` comment= '角色-机构';

-- sys_user
/*
警告: 字段名可能非法 - password
警告: 字段名可能非法 - no
*/
create table  `sys_user`
(
       `id`              INT not null AUTO_INCREMENT comment '编号',
       `company_id`      INT not null comment '归属公司',
       `office_id`       INT not null comment '归属部门',
       `login_name`      VARCHAR(100) not null comment '登录名',
       `password`        VARCHAR(100) not null comment '密码',
       `no`              VARCHAR(100) comment '工号',
       `name`            VARCHAR(100) not null comment '姓名',
       `email`           VARCHAR(200) comment '邮箱',
       `phone`           VARCHAR(200) comment '电话',
       `mobile`          VARCHAR(200) comment '手机',
       `user_type`       VARCHAR(1) comment '用户类型',
       `photo`           VARCHAR(1000) comment '用户头像',
       `login_ip`        VARCHAR(100) comment '最后登陆IP',
       `login_date`      DATETIME  comment '最后登陆时间',
       `login_flag`      VARCHAR(64) comment '是否可登录',
       `create_by`       INT not null comment '创建者',
       `create_date`     DATETIME  not null comment '创建时间',
       `update_by`       INT not null comment '更新者',
       `update_date`     DATETIME  not null comment '更新时间',
       `remarks`         VARCHAR(255) comment '备注信息',
       `del_flag`        VARCHAR(1) default 0 not null comment '删除标记'
,primary key (`id`)
);
 
create index `IDX_sys_user_company_id` on `sys_user`(`company_id`);
create index `IDX_sys_user_office_id` on `sys_user`(`office_id`);
create index `IDX_sys_user_login_name` on `sys_user`(`login_name`);
create index `IDX_sys_user_update_date` on `sys_user`(`update_date`);
create index `IDX_sys_user_del_flag` on `sys_user`(`del_flag`);
alter table `sys_user` comment= '用户表';

-- sys_user_role
create table  `sys_user_role`
(
       `user_id`         INT not null comment '用户编号',
       `role_id`         INT not null comment '角色编号'
);
alter  table `sys_user_role`
       add constraint `PK_sys_user_role_user_id` primary key (`user_id`,`role_id`);
alter table `sys_user_role` comment= '用户-角色';


-- sys_file
/*
 系统文件表
*/

create table  `sys_file`
(
       `id`              INT auto_increment primary key not null comment '编号',
       `file_name`       VARCHAR(64) comment '文件名',
       `file_size`       INT comment '大小',
       `file_type`       VARCHAR(64) comment '类型',
       `file_url`        VARCHAR(512) comment '存储位置',
       `create_time`     DATETIME,
       `create_by`       NUMERIC(64),
       `update_time`     DATETIME,
       `update_by`       NUMERIC(64),
       `busi_type`       VARCHAR(64) comment '业务类型',
       `busi_no`         VARCHAR(128) comment '业务单号',
       `memo`            VARCHAR(512) comment '注释',
       `state`           INT comment '状态',
       `deleted`         INT comment '删除标识'
);
alter table `sys_file` comment= '系统文件管理 系统文件管理';


-- data
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1', '1', 'admin', '$2a$10$iyy/9WyGecm9MqeSyGaxauYXcz79foBFA047EtQlrPJbau5OPBpMm', '1', '管理员', NULL, NULL, NULL, NULL, 0, NULL, NULL, '0', '1', '2017-10-21 17:12:38', '1', '2017-10-21 17:13:12', NULL, '0');

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '管理员', 'admin', '1', null, '1', '1', '1', '2017-10-23 15:54:40', '1', '2017-11-28 16:52:57', '', '0');
INSERT INTO `sys_role` VALUES ('2', null, '普通用户', 'normal', '1', null, '1', '1', '1', '2017-11-28 16:52:44', '1', '2017-11-28 16:52:44', '', '0');


-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');




-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '0,', '系统管理', '30', '', '', 'fa fa-cog', '1', '', '1', '2017-11-29 11:02:04', '1', '2017-11-29 11:02:04', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '0,1,', '用户管理', '30', '/sys/user/list', '', '', '1', '', '1', '2017-11-29 11:03:21', '1', '2017-11-29 11:03:21', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '0,1,', '角色管理', '30', '/sys/role/list', '', '', '1', '', '1', '2017-11-29 11:03:37', '1', '2017-11-29 11:03:37', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('4', '1', '0,1,', '菜单管理', '30', '/sys/menu/list', '', '', '1', '', '1', '2017-11-29 11:03:49', '1', '2017-11-29 11:03:49', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('5', '1', '0,1,', '机构管理', '30', '/sys/office/list', '', '', '1', '', '1', '2017-11-29 11:04:03', '1', '2017-11-29 11:04:03', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('6', '1', '0,1,', '区域管理', '30', '/sys/area/list', '', '', '1', '', '1', '2017-11-29 11:04:16', '1', '2017-11-29 11:04:16', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('7', '1', '0,1,', '字典管理', '30', '/sys/dict/list', '', '', '1', '', '1', '2017-11-29 11:04:29', '1', '2017-11-29 11:04:29', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('9', '1', '0,1,', '日志管理', '30', '/sys/log/list', '', '', '1', '', '1', '2017-12-01 13:53:02', '1', '2017-12-01 13:53:02', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('10', '4', '0,1,4', '编辑权限', '30', '', '', '', '1', 'menu:edit', '1', '0000-00-00 00:00:00', '1', '2017-12-05 14:52:34', '', '0', '2');
INSERT INTO `sys_menu` VALUES ('11', '3', '0,1,3,', '编辑', '30', '', '', '', '1', 'role:edit', '1', '2017-12-05 14:54:57', '1', '2017-12-05 16:45:00', '', '0', '2');
INSERT INTO `sys_menu` VALUES ('12', '2', '0,1,2,', '用户编辑', '30', '', '', '', '1', 'user:edit', '1', '2017-12-05 14:55:22', '1', '2017-12-05 14:55:22', '', '0', '2');
INSERT INTO `sys_menu` VALUES ('13', '3', '0,1,3,', '查看', '30', '', '', '', '1', 'role:view', '1', '2017-12-05 15:03:15', '1', '2017-12-05 15:03:15', '', '0', '2');
INSERT INTO `sys_menu` VALUES ('14', '4', '0,1,4,', '查看', '30', '', '', '', '1', 'menu:view', '1', '2017-12-05 15:03:35', '1', '2017-12-05 15:03:35', '', '0', '2');
INSERT INTO `sys_menu` VALUES ('15', '3', '0,1,3,', '角色授权', '30', null, null, null, '1', 'role:auth', '1', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00', null, '0', '2');






-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '15');




-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '0', '0,', '总公司', '30', '1', '001', '', '', null, null, null, null, null, null, '0', null, null, '1', '2017-10-31 17:03:58', '1', '2017-10-31 17:04:29', '1', '0');
INSERT INTO `sys_office` VALUES ('2', '1', '0,1,', '开发部', '30', '1', '', '', '', null, null, null, null, null, null, '0', null, null, '1', '2017-10-31 17:04:18', '1', '2017-10-31 17:04:25', '1', '0');
INSERT INTO `sys_office` VALUES ('3', '2', '0,1,2,', '开发一部', '30', '1', '', '', '', null, null, null, null, null, null, '0', null, null, '1', '2017-10-31 17:04:47', '1', '2017-11-01 14:17:48', '', '0');
INSERT INTO `sys_office` VALUES ('4', '0', '0,', '海外公司', '30', '1', '', '', '', null, null, null, null, null, null, '0', null, null, '1', '2017-10-31 17:05:45', '1', '2017-11-01 15:51:45', '', '0');


-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('1', '0', '0,', '安徽', '30', '34', '', '1', '2017-10-27 12:08:26', '1', '2017-10-27 12:08:26', '啊啊啊', '0');
INSERT INTO `sys_area` VALUES ('2', '1', '0,1,', '合肥', '30', '3423', '', '1', '2017-10-27 12:57:32', '1', '2017-10-27 12:57:32', '', '0');
INSERT INTO `sys_area` VALUES ('3', '0', '0,', '江苏', '30', '25', '', '1', '2017-10-27 13:04:23', '1', '2017-10-27 13:04:23', '', '0');
INSERT INTO `sys_area` VALUES ('4', '2', '0,1,2,', '蜀山', '30', '342301', '', '1', '2017-10-27 14:37:09', '1', '2017-10-27 14:37:09', '', '0');
INSERT INTO `sys_area` VALUES ('5', '1', '0,1,', '芜湖', '30', '3426', '', '1', '2017-10-27 14:38:46', '1', '2017-10-27 14:38:46', '', '0');
INSERT INTO `sys_area` VALUES ('6', '3', '0,3,', '南京', '30', '2523', '', '1', '2017-10-27 14:40:21', '1', '2017-10-27 14:40:21', '', '0');


-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '', '', 'orgType', '机构类型', '50', '0', '1', '2017-12-15 21:35:33', '1', '2017-12-15 21:35:33', '', '0');
INSERT INTO `sys_dict` VALUES ('2', '1', '公司', '', '', '50', '1', '1', '2017-12-15 21:36:05', '1', '2017-12-15 21:36:05', '', '0');
INSERT INTO `sys_dict` VALUES ('3', '2', '部门', '', '', '50', '1', '1', '2017-12-15 21:36:17', '1', '2017-12-15 21:36:17', '', '0');
INSERT INTO `sys_dict` VALUES ('4', '', '', 'orgLevel', '公司级别', '50', '0', '1', '2017-12-15 22:30:54', '1', '2017-12-15 22:30:54', '', '0');
INSERT INTO `sys_dict` VALUES ('5', '1', '一级', '', '', '50', '4', '1', '2017-12-15 22:31:08', '1', '2017-12-15 22:31:08', '', '0');
INSERT INTO `sys_dict` VALUES ('6', '2', '二级', '', '', '50', '4', '1', '2017-12-15 22:31:20', '1', '2017-12-15 22:31:20', '', '0');
INSERT INTO `sys_dict` VALUES ('7', '3', '三级', '', '', '50', '4', '1', '2017-12-15 22:31:29', '1', '2017-12-15 22:31:29', '', '0');
INSERT INTO `sys_dict` VALUES ('8', '', '', 'areaType', '区域类型', '50', '0', '1', '2017-12-19 20:19:24', '1', '2017-12-19 20:19:24', '', '0');
INSERT INTO `sys_dict` VALUES ('9', '1', '国家', '', '', '50', '8', '1', '2017-12-19 20:19:37', '1', '2017-12-19 20:19:37', '', '0');
INSERT INTO `sys_dict` VALUES ('10', '2', '省份、直辖市', '', '', '60', '8', '1', '2017-12-19 20:20:03', '1', '2017-12-19 20:20:03', '', '0');
INSERT INTO `sys_dict` VALUES ('11', '3', '地市', '', '', '70', '8', '1', '2017-12-19 20:20:31', '1', '2017-12-19 20:20:31', '', '0');
INSERT INTO `sys_dict` VALUES ('12', '4', '区县', '', '', '80', '8', '1', '2017-12-19 20:20:46', '1', '2017-12-19 20:20:46', '', '0');



