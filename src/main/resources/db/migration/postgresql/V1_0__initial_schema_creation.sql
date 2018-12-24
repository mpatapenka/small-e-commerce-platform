CREATE TABLE "Category" (
	"id" serial NOT NULL,
	"priority" integer,
	"name" VARCHAR(255) NOT NULL,
	"archived" BOOLEAN NOT NULL,
	"image_id" integer NOT NULL,
	CONSTRAINT Category_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Product" (
	"id" serial NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"description" VARCHAR(255),
	"fabricator" VARCHAR(255),
	"composition" VARCHAR(255),
	"price" DECIMAL NOT NULL,
	"sale_price" DECIMAL,
	"archived" BOOLEAN NOT NULL,
	"category_id" integer NOT NULL,
	"productitem_id" integer NOT NULL,
	"image_id" integer NOT NULL,
	CONSTRAINT Product_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Image" (
	"id" serial NOT NULL,
	"path" VARCHAR(255) NOT NULL,
	"original_name" VARCHAR(255),
	CONSTRAINT Image_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Size" (
	"id" serial NOT NULL,
	"numeric" integer NOT NULL,
	"symbolic" VARCHAR(255),
	"archived" BOOLEAN NOT NULL,
	CONSTRAINT Size_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "ProductItem" (
	"id" serial NOT NULL,
	"name" VARCHAR(255),
	CONSTRAINT ProductItem_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "CategorySize" (
	"id" serial NOT NULL,
	"category_id" integer NOT NULL,
	"size_id" integer NOT NULL,
	CONSTRAINT CategorySize_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "ProductItemSize" (
	"id" serial NOT NULL,
	"productitem_id" integer NOT NULL,
	"size_id" integer NOT NULL,
	CONSTRAINT ProductItemSize_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "Category" ADD CONSTRAINT "Category_fk0" FOREIGN KEY ("image_id") REFERENCES "Image"("id");

ALTER TABLE "Product" ADD CONSTRAINT "Product_fk0" FOREIGN KEY ("category_id") REFERENCES "Category"("id");
ALTER TABLE "Product" ADD CONSTRAINT "Product_fk1" FOREIGN KEY ("productitem_id") REFERENCES "ProductItem"("id");
ALTER TABLE "Product" ADD CONSTRAINT "Product_fk2" FOREIGN KEY ("image_id") REFERENCES "Image"("id");




ALTER TABLE "CategorySize" ADD CONSTRAINT "CategorySize_fk0" FOREIGN KEY ("category_id") REFERENCES "Category"("id");
ALTER TABLE "CategorySize" ADD CONSTRAINT "CategorySize_fk1" FOREIGN KEY ("size_id") REFERENCES "Size"("id");

ALTER TABLE "ProductItemSize" ADD CONSTRAINT "ProductItemSize_fk0" FOREIGN KEY ("productitem_id") REFERENCES "ProductItem"("id");
ALTER TABLE "ProductItemSize" ADD CONSTRAINT "ProductItemSize_fk1" FOREIGN KEY ("size_id") REFERENCES "Size"("id");