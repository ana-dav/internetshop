CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;
CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DECIMAL(15) NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `product_id_UNIQUE` (`product_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `orders` (
  `order_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_users_fk_idx` (`user_id`),
  CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
)

CREATE TABLE `orders_products` (
  `product_id` bigint(11) DEFAULT NULL,
  `order_id` bigint(11) DEFAULT NULL,
  KEY `orders_products_fk_idx` (`product_id`),
  KEY `fk_orders_products_1_idx` (`order_id`),
  CONSTRAINT `order_relation_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `products_in_order_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
)

CREATE TABLE `products` (
  `product_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  `price` decimal(15,2) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `product_id_UNIQUE` (`product_id`)
)

CREATE TABLE `roles` (
  `role_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(256) NOT NULL,
  PRIMARY KEY (`role_id`)
)

CREATE TABLE `shopping_cart_products` (
  `cart_id` bigint(11) DEFAULT NULL,
  `product_id` bigint(11) DEFAULT NULL,
  KEY `shopping_cart_fk_idx` (`cart_id`),
  KEY `product_for_shopping_cart_fk_idx` (`product_id`),
  CONSTRAINT `product_for_shopping_cart_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `shopping_cart_fk` FOREIGN KEY (`cart_id`) REFERENCES `shopping_carts` (`cart_id`)
)

CREATE TABLE `shopping_carts` (
  `cart_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `shopping_cart_user_fk_idx` (`user_id`),
  CONSTRAINT `shopping_cart_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
)

CREATE TABLE `users` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(225) DEFAULT NULL,
  `login` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `salt` VARBINARY(500) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
)

CREATE TABLE `users_roles` (
  `user_id` bigint(11) DEFAULT NULL,
  `role_id` bigint(11) DEFAULT NULL,
  KEY `user_fk_idx` (`user_id`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
)
