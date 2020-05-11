CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;
CREATE TABLE `internet_shop`.`products` (
  `product_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  `price` decimal(15,0) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);