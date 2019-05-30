#配置oauth2
Drop table  if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  autoapprove VARCHAR (255) default 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists oauth_code;
create table oauth_code (
  code VARCHAR(255),
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Add indexes
create index token_id_index on oauth_access_token (token_id);
create index authentication_id_index on oauth_access_token (authentication_id);
create index user_name_index on oauth_access_token (user_name);
create index client_id_index on oauth_access_token (client_id);
create index refresh_token_index on oauth_access_token (refresh_token);
create index token_id_index on oauth_refresh_token (token_id);
create index code_index on oauth_code (code);

-- INSERT DEFAULT DATA
INSERT INTO `oauth_client_details` VALUES ('dev', '', 'dev', 'app', 'authorization_code', 'http://localhost:7777/', '', '3600', '3600', '{"country":"CN","country_code":"086"}', 'TAIJI');


-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
                           `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
                           `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
                           `gender` int(2) DEFAULT NULL COMMENT '性别（1男 2女）',
                           `email` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
                           `create_time` datetime DEFAULT NULL COMMENT '用户创建时间',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           `removed` int(2) DEFAULT NULL COMMENT '是否删除（1删除0未删除）',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'admin', '$2a$10$vWyL7fMGQRvVNn.i2bK40e3z30Nem4k.ElwuxdLBNzKFxRCcXCoqm', 1, NULL, '2019-05-30 15:53:45', '2019-05-30 15:53:51', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
                                `id` bigint(11) NOT NULL COMMENT '主键ID',
                                `user_id` bigint(11) DEFAULT NULL COMMENT '用户主键',
                                `role_id` bigint(11) DEFAULT NULL COMMENT '角色ID',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` VALUES (1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
                           `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `parent_id` bigint(20) DEFAULT NULL COMMENT '父类ID',
                           `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名字',
                           `ename` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名字',
                           `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` VALUES (1, 0, '超级管理员', 'ADMIN', NULL, '2019-05-30 16:09:53', '2019-05-30 16:09:57');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
                                      `id` bigint(11) NOT NULL COMMENT '主键ID',
                                      `role_id` bigint(11) DEFAULT NULL COMMENT '角色ID',
                                      `permission_id` bigint(11) DEFAULT NULL COMMENT '权限ID',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_permission` VALUES (1, 1, 1);
INSERT INTO `tb_role_permission` VALUES (2, 1, 2);
INSERT INTO `tb_role_permission` VALUES (3, 1, 3);
INSERT INTO `tb_role_permission` VALUES (4, 1, 4);
INSERT INTO `tb_role_permission` VALUES (5, 1, 5);
INSERT INTO `tb_role_permission` VALUES (6, 1, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
                                 `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                 `parent_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
                                 `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限名字',
                                 `ename` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限名字',
                                 `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求路径',
                                 `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_permission` VALUES (1, 0, '系统管理', 'System', '/', NULL, '2019-05-30 16:22:20', '2019-05-30 16:22:24');
INSERT INTO `tb_permission` VALUES (2, 0, '用户管理', 'SystemUser', '/users', NULL, '2019-05-30 16:23:28', '2019-05-30 16:23:32');
INSERT INTO `tb_permission` VALUES (3, 0, '查看用户', 'SystemUserView', NULL, NULL, '2019-05-30 16:24:29', '2019-05-30 16:24:33');
INSERT INTO `tb_permission` VALUES (4, 0, '新增用户', 'SystemUserInsert', NULL, NULL, '2019-05-30 16:25:09', '2019-05-30 16:25:13');
INSERT INTO `tb_permission` VALUES (5, 0, '编辑用户', 'SystemUserUpdate', NULL, NULL, '2019-05-30 16:25:53', '2019-05-30 16:25:57');
INSERT INTO `tb_permission` VALUES (6, 0, '删除用户', 'SystemUserDelete', NULL, NULL, '2019-05-30 16:26:49', '2019-05-30 16:26:54');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

