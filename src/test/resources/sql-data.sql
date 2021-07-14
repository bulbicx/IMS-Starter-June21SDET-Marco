USE exercise;

INSERT INTO `exercise.customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `exercise.items` (`item_name`, `description`, `price`) VALUES ('shoes', 'description', 5.5);
INSERT INTO `exercise.orders` (`customer_id`, `total`) VALUES (1, 5.5);
INSERT INTO `exercise.orders_items` (`order_id`, `item_id`, `qty`, `line_total`) VALUES (1, 1, 1, 5.5);