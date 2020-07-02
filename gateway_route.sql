-- auto-generated definition
drop table if exists route;
create table route
(
    id          varchar(64)      not null comment '主键'
        primary key,
    app_id      int              not null comment '应用ID',
    app_name    varchar(64)      not null comment '应用名字',
    target_url  varchar(128)     null comment '目标地址',
    `order`     int    default 0 not null comment '执行顺序',
    create_user varchar(64)      not null comment '创建时间',
    update_user varchar(64)      null,
    create_time datetime         null,
    update_time datetime         null,
    deleted     int(1) default 0 not null comment '是否删除（1是0否）'
)
    comment '网关主表';

create index idx_app_id
    on route (app_id);
-- auto-generated definition
drop table if exists route_filter;
create table route_filter
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    route_id    varchar(64) not null,
    filter_name varchar(64) not null comment '过滤器名字',
    deleted     int(1)      null comment '是否删除'
)
    comment '网关过滤器';

create index idx_route_id
    on route_filter (route_id);

-- auto-generated definition
drop table if exists route_filter_args;
create table route_filter_args
(
    id        bigint auto_increment
        primary key,
    filter_id bigint      not null,
    `key`     varchar(64) not null,
    value     varchar(64) null,
    deleted   int(1)      null
);

create index idx_filter_id
    on route_filter_args (filter_id);
    
-- auto-generated definition
drop table if exists route_metadata;
create table route_metadata
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    route_id    varchar(64)      not null,
    `key`       varchar(64)      not null comment '元数据key',
    value       varchar(128)     null comment '元数据值',
    create_user varchar(64)      null,
    update_user varchar(64)      null,
    create_time datetime         null,
    update_time datetime         null,
    deleted     int(1) default 0 not null comment '是否删除（1是0否）'
)
    comment '网关元数据表';

create index idx_route_id
    on route_metadata (route_id);

-- auto-generated definition
drop table if exists route_predicate;
create table route_predicate
(
    id             bigint auto_increment
        primary key,
    route_id       varchar(64)      not null,
    predicate_name varchar(64)      not null comment '断言名字',
    update_time    datetime         null,
    create_user    varchar(64)      null,
    update_user    varchar(64)      null,
    create_time    datetime         null,
    deleted        int(1) default 0 not null
)
    comment '网关断言';

create index idx_route_id
    on route_predicate (route_id);

-- auto-generated definition
drop table if exists route_predicate_args;
create table route_predicate_args
(
    id           bigint auto_increment
        primary key,
    predicate_id bigint           not null,
    `key`        varchar(64)      null,
    value        varchar(128)     null,
    update_user  varchar(64)      null,
    create_user  varchar(64)      null,
    update_time  datetime         null,
    create_time  datetime         null,
    deleted      int(1) default 0 not null
);

create index idx_predicate_id
    on route_predicate_args (predicate_id);


INSERT INTO route (id, app_id, app_name, target_url, `order`, create_user, update_user, create_time, update_time, deleted) VALUES ('before_route', 1, 'app1', 'https://www.ailijie.top', 0, 'wotrd', 'wotrd', '2020-07-02 13:11:31', '2020-07-02 13:11:33', 0);
INSERT INTO route (id, app_id, app_name, target_url, `order`, create_user, update_user, create_time, update_time, deleted) VALUES ('myRoute', 1, 'app1', 'https://www.baidu.com', 0, 'wotrd', 'wotrd', '2020-07-02 13:13:23', '2020-07-02 13:13:24', 0);

INSERT INTO route_filter (id, route_id, filter_name, deleted) VALUES (1, 'before_route', 'AddRequestParameter', 0);
INSERT INTO route_filter (id, route_id, filter_name, deleted) VALUES (2, 'before_route', 'AddRequestHeader', 0);

INSERT INTO route_filter_args (id, filter_id, `key`, value, deleted) VALUES (1, 1, '_genkey_0', 'header', 0);
INSERT INTO route_filter_args (id, filter_id, `key`, value, deleted) VALUES (2, 1, '_genkey_1', 'addHeader', 0);
INSERT INTO route_filter_args (id, filter_id, `key`, value, deleted) VALUES (3, 2, '_genkey_0', 'param', 0);
INSERT INTO route_filter_args (id, filter_id, `key`, value, deleted) VALUES (4, 2, '_genkey_1', 'addParam', 0);

INSERT INTO route_predicate (id, route_id, predicate_name, update_time, create_user, update_user, create_time, deleted) VALUES (1, 'before_route', 'Path', '2020-07-02 13:14:11', 'wotrd', 'wotrd', '2020-07-02 13:14:22', 0);
INSERT INTO route_predicate (id, route_id, predicate_name, update_time, create_user, update_user, create_time, deleted) VALUES (2, 'myRoute', 'Path', '2020-07-02 13:14:11', 'wotrd', 'wotrd', '2020-07-02 13:14:22', 0);

INSERT INTO route_predicate_args (id, predicate_id, `key`, value, update_user, create_user, update_time, create_time, deleted) VALUES (1, 1, 'pattern', '/jd', 'wotrd', 'wotrd', '2020-07-02 13:20:56', '2020-07-02 13:20:57', 0);
INSERT INTO route_predicate_args (id, predicate_id, `key`, value, update_user, create_user, update_time, create_time, deleted) VALUES (2, 2, 'pattern', '/home', 'wotrd', 'wotrd', '2020-07-02 15:06:58', '2020-07-02 15:06:59', 0);