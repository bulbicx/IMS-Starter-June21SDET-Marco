USE exercise;

DROP TABLE IF EXISTS `orders_items`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `items` (
    `item_id` INT NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
	`price` DOUBLE NOT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT NOT NULL AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `total` DOUBLE,
    PRIMARY KEY (`order_id`),
	CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS `orders_items` (
	`order_item_id` INT NOT NULL AUTO_INCREMENT,
    `order_id` INT NOT NULL,
    `item_id` INT NOT NULL,
    `qty` INT NOT NULL,
	`line_total` DOUBLE NOT NULL,
    PRIMARY KEY (`order_item_id`),
	CONSTRAINT fk_orders_item_orders FOREIGN KEY (order_id) REFERENCES orders(order_id),
	CONSTRAINT fk_orders_item_items FOREIGN KEY (item_id) REFERENCES items(item_id)
);

INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');