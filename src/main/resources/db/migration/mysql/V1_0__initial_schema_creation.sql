CREATE TABLE `Category` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`priority` INT,
	`name` VARCHAR(255) NOT NULL,
	`archived` BOOLEAN NOT NULL,
	`image_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Product` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255),
	`fabricator` VARCHAR(255),
	`composition` VARCHAR(255),
	`price` DECIMAL NOT NULL,
	`sale_price` DECIMAL,
	`archived` BOOLEAN NOT NULL,
	`category_id` INT NOT NULL,
	`productitem_id` INT NOT NULL,
	`image_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Image` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`path` VARCHAR(255) NOT NULL,
	`original_name` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `Size` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`numeric` INT NOT NULL,
	`symbolic` VARCHAR(255),
	`archived` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `ProductItem` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `CategorySize` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`category_id` INT NOT NULL,
	`size_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `ProductItemSize` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`productitem_id` INT NOT NULL,
	`size_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Category` ADD CONSTRAINT `Category_fk0` FOREIGN KEY (`image_id`) REFERENCES `Image`(`id`);

ALTER TABLE `Product` ADD CONSTRAINT `Product_fk0` FOREIGN KEY (`category_id`) REFERENCES `Category`(`id`);

ALTER TABLE `Product` ADD CONSTRAINT `Product_fk1` FOREIGN KEY (`productitem_id`) REFERENCES `ProductItem`(`id`);

ALTER TABLE `Product` ADD CONSTRAINT `Product_fk2` FOREIGN KEY (`image_id`) REFERENCES `Image`(`id`);

ALTER TABLE `CategorySize` ADD CONSTRAINT `CategorySize_fk0` FOREIGN KEY (`category_id`) REFERENCES `Category`(`id`);

ALTER TABLE `CategorySize` ADD CONSTRAINT `CategorySize_fk1` FOREIGN KEY (`size_id`) REFERENCES `Size`(`id`);

ALTER TABLE `ProductItemSize` ADD CONSTRAINT `ProductItemSize_fk0` FOREIGN KEY (`productitem_id`) REFERENCES `ProductItem`(`id`);

ALTER TABLE `ProductItemSize` ADD CONSTRAINT `ProductItemSize_fk1` FOREIGN KEY (`size_id`) REFERENCES `Size`(`id`);
