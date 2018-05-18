--请假流程

create table  `oa_leave`
(
       `id`              INT auto_increment primary key not null comment '编号',
       `processid`       VARCHAR(64) comment '流程id',
       `title`      VARCHAR(64) comment '标题',
       `user_id`         INT comment '申请人',
       `leave_type`      VARCHAR(64) comment '类型',
       `start_time`      DATETIME comment '开始时间',
       `end_time`        DATETIME comment '结束时间',
       `reason`          VARCHAR(512) comment '原因',
       `time_used`       DOUBLE comment '实际用时',
       `apply_time`      DATETIME comment '申请时间',
       `status`          INT comment '工单状态',
       `deleted`         VARCHAR(1) comment '删除标识'
);
alter table `oa_leave` comment= '请假表';