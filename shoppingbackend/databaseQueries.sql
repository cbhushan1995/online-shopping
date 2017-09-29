CREATE TABLE category(

id IDENTITY,
name varchar(50),
description varchar(255),
image_url varchar(50),
is_active BOOLEAN,

CONSTRAINT pk_category_id PRIMARY KEY (id)

);


INSERT INTO category(name,description,image_url,is_active) values('Laptop','this is description on laptop category','CAT_3.png',true);
INSERT INTO category(name,description,image_url,is_active) values('Television','this is description on Television category','CAT_3.png',true);
INSERT INTO category(name,description,image_url,is_active) values('Mobile','this is description on mobile category','CAT_3.png',true);


create table user_detail(
 id IDENTITY,
 first_name varchar(50),
 last_name varchar(50),
 role varchar(50),
 enabled boolean,
 password varchar(60),
 email varchar(50),
 contact_number varchar(15),
 CONSTRAINT pk_user_id primary key (id)

)


INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number) values('virat','kohli','ADMIN',true,'$2a$06$bRcrxa11geBKcSFLOc33k.AGaIOiqRwTpo4nQ5CyPaO.fYj/yTRJS','vk@gmail.com','8888888888')


INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number) values('Ravindra','Jadeja','SUPPLIER',true,'$2a$06$D8FPx7ybptE3GF/Yz69mWOGdiR41iX/BtSyh51H6nGvTILashpBNm','RJ@gmail.com','9999999999')


INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number) values('Ravichandra','Ashwin','SUPPLIER',true,'$2a$06$4TgTC28FfuYIWU8TLGKTaugbeyYEEroir.IZCekgPs0r1klc1YFL6','ra@gmail.com','7777777777')



create table product()
id IDENTITY,
code VARCHAR(20),
name VARCHAR(50),
brand VARCHAR(50),
description VARCHAR(255),
unit_price DECIMAL(10,2),
quantity INT,
is_active BOOLEAN,
category_id INT,
supplier_id INT,
purchases INT DEFAULT 0,
views INT DEFAULT 0,
CONSTRAINT pk_product_id PRIMARY KEY (id),
CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail (id),
);



INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 3, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 32000, 2, true, 3, 3 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 57000, 5, true, 3, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 1, 3 );



--------the cart line table to store the cart details

CREATE TABLE cart_line(
id IDENTITY,
cart_id int,
total DECIMAL(10,2),
product_id int,
product_count int,
buying_	price DECIMAL(10,2);
is_available boolean,
CONSTRAINT fk_cartline_cart_id FOREIGN KEY(cart_id) REFERENCES cart(id),
CONSTRAINT fk_cartline_product_id FOREIGN KEY(product_id) REFERENCES product(id),
CONSTRAINT pk_cartline_id	PRIMARY KEY(id)
); 