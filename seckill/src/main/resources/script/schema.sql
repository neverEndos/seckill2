create database seckill;

use seckill;

create table seckill(
    `seckill_id` bigint not null AUTO_INCREMENT,
    `name` varchar(120) not null,
    `number` int not null,
    `start_time` timestamp not null,
    `end_time` timestamp not null,
    `create_time` timestamp not null default CURRENT_TIMESTAMP,
    primary key (seckill_id),
    key idx_start_time(start_time),
    key idx_end_time(end_time),
    key ixd_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT '秒杀库存表';

insert into seckill(name,number,start_time,end_time)
values
('1000元秒杀iphone6',100,'2017-03-22 00:00:00','2017-03-24 00:00:00'),
('500元秒杀ipad2',200,'2017-03-22 00:00:00','2017-03-24 00:00:00'),
('300元秒杀小米4',300,'2017-03-22 00:00:00','2017-03-24 00:00:00'),
('200元秒杀红米4',400,'2017-03-22 00:00:00','2017-03-24 00:00:00');

create table success_killed(
    `seckill_id` bigint not null,
    `user_phone` bigint not null,
    `state` tinyint not null DEFAULT 0 COMMENT '状态标识:-1:无效；0:成功；1:已付款；2:已发货',
    `create_time` timestamp not null,
    primary key (seckill_id,user_phone),
    key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '秒杀成功明细表';
