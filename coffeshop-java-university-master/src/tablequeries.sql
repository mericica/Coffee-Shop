CREATE TABLE `products` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(20) NOT NULL,
                            `price` float NOT NULL,
                            `size` int NOT NULL,
                            `unit` varchar(10) NOT NULL,
                            PRIMARY KEY (`id`)
);



CREATE TABLE coffeeshop.location (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     name VARCHAR(64) NOT NULL,
                                     address VARCHAR(128) NOT NULL,
                                     active BIT NOT NULL,
                                     PRIMARY KEY (`id`)
);

Drop TABLE coffeeshop.Location;

CREATE TABLE coffeeshop.Employee (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     first_name VARCHAR(64) NOT NULL,
                                     last_name VARCHAR(64) NOT NULL,
                                     address VARCHAR(128),

                                     salary FLOAT,
                                     title VARCHAR(64),
                                     location_id INT,
                                     FOREIGN KEY (location_id) REFERENCES coffeeshop.location(id),
                                     PRIMARY KEY (`id`)
);

DROP TABLE coffeeshop.Employee;

CREATE TABLE coffeeshop.Manager (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    first_name VARCHAR(64) NOT NULL,
                                    last_name VARCHAR(64) NOT NULL,
                                    address VARCHAR(128),
                                    salary FLOAT,
                                    location_id INT,
                                    FOREIGN KEY (location_id) REFERENCES coffeeshop.location(id),
                                    PRIMARY KEY (`id`)
);

CREATE TABLE coffeeshop.Event (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  name VARCHAR(64),
                                  host VARCHAR(64) NOT NULL,
                                  profit FLOAT,
                                  location_id INT,
                                  FOREIGN KEY (location_id) REFERENCES coffeeshop.location(id),
                                  PRIMARY KEY (`id`)
);

CREATE TABLE coffeeshop.orders(
                                  id INT auto_increment PRIMARY KEY,
                                  date_time VARCHAR(20),
                                  location int not null ,
                                  customer int,
                                  order_type varchar(30),
                                  delivery_address varchar(20),
                                  delivery_man varchar(20),
                                  foreign key (location) references coffeeshop.location(id),
                                  foreign key (customer) references coffeeshop.customer(id)
);

Create Table coffeeshop.order_products(
                                          id INT auto_increment primary key,
                                          order_id int NOT NULL,
                                          product_id int NOT NULL,
                                          quantity int NOT NULL,
                                          foreign key (order_id) references coffeeshop.orders(id),
                                          foreign key (product_id) references coffeeshop.products(id)
);

--this is the many to many vaordersriant
CREATE TABLE order_products (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT, -- Additional columns related to the relationship
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE `location_products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
);


CREATE TABLE `customer` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `first_name` varchar(20) NOT NULL,
                            `last_name` varchar(20) NOT NULL,
                            `address` varchar(20) NOT NULL,
                            PRIMARY KEY (`id`)
);
