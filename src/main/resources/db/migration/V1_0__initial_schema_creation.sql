CREATE TABLE Category (
	id integer PRIMARY KEY AUTOINCREMENT,
	priority integer,
	name string,
	archived boolean,
	image_id integer
);

CREATE TABLE Product (
	id integer PRIMARY KEY AUTOINCREMENT,
	name string,
	description string,
	fabricator string,
	composition string,
	price decimal,
	sale_price decimal,
	archived boolean,
	category_id integer,
	productitem_id integer,
	image_id integer
);

CREATE TABLE Image (
	id integer PRIMARY KEY AUTOINCREMENT,
	path string,
	original_name string
);

CREATE TABLE Size (
	id integer PRIMARY KEY AUTOINCREMENT,
	numeric integer,
	symbolic string,
	archived boolean
);

CREATE TABLE ProductItem (
	id integer PRIMARY KEY AUTOINCREMENT,
	name string
);

CREATE TABLE CategorySize (
	id integer PRIMARY KEY AUTOINCREMENT,
	category_id integer,
	size_id integer
);

CREATE TABLE ProductItemSize (
	id integer PRIMARY KEY AUTOINCREMENT,
	productitem_id integer,
	size_id integer
);
