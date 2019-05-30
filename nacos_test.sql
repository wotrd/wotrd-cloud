#配置订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                         `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                         `name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单名字',
                         `price` decimal(9,2) DEFAULT NULL COMMENT '订单价格',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;