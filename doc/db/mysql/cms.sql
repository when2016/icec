-- sys_global  系统参数表 

create table  `sys_global`
(
       `id`              INT   primary key not null comment '编号',
       `name`            VARCHAR(200) comment '名称',
       `fullname`        VARCHAR(200) comment '全称',
       `logo`            INT comment 'logo id',
       `poweredby`       VARCHAR(200) comment '版权',
       `version`         VARCHAR(4000) comment '版本',
       `create_time`     DATETIME comment '创建日期'
);
alter table `sys_global` comment= '全局表';
-- sys_global_custom  系统参数扩展表
create table  `sys_global_custom`
(
       `id`              INT auto_increment primary key  not null comment '全局自定义id',
       `f_global_id`     INT comment '全局id',
       `f_key`           VARCHAR(50) comment '键',
       `f_value`         VARCHAR(2000) comment '值'
);
 
alter  table `sys_global_custom`
       add constraint `FK_sys_glotom_f_global_id9DA3` foreign key (`f_global_id`)
       references `sys_global`(`id`);
alter table `sys_global_custom` comment= '全局自定义表';









-- ----------------------------
-- Records of sys_global
-- ----------------------------
INSERT INTO `sys_global` VALUES ('1', 'icec', 'icec admin', '0', '©2018 icec', '1.0', '2018-01-16 16:25:23');







