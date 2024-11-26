CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS role_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS product_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS category_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS cart_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE roles (
    role_id BIGINT PRIMARY KEY DEFAULT NEXTVAL('role_id_seq'),
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE app_users (
    user_id BIGINT PRIMARY KEY DEFAULT NEXTVAL('user_id_seq'),
    user_name VARCHAR(200),
    user_email VARCHAR(200) UNIQUE,
    user_password VARCHAR(200),
    user_phone_number VARCHAR(50),
    user_address VARCHAR(200),
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES roles(role_id) ON DELETE SET NULL
);

CREATE TABLE category (
    category_id BIGINT PRIMARY KEY DEFAULT NEXTVAL('category_id_seq'),	
	category_name VARCHAR(255)
);

CREATE TABLE products (
    product_id BIGINT PRIMARY KEY DEFAULT NEXTVAL('product_id_seq'),
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT,
	product_price DECIMAL(10,2) NOT NULL,
	product_image VARCHAR(255),
	product_stock_quantity BIGINT NOT NULL,
	category_id BIGINT NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES category(category_id) ON DELETE SET NULL
);

CREATE TABLE cart (
    cart_id BIGINT PRIMARY KEY DEFAULT NEXTVAL('cart_id_seq'),	
	user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT DEFAULT 1 CHECK (quantity > 0),
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES app_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);


INSERT INTO category (category_name)
VALUES 
('Beauty'),
('Groceries'),
('Electronics'),
('Clothing'),
('Home & Kitchen');


-- Insert product data
INSERT INTO Products (product_name, product_description, product_price, product_stock_quantity, category_id, product_image)
VALUES 
    (
        'Essence Mascara Lash Princess', 
        'The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.', 
        9.99, 
        5, 
		(SELECT category_id FROM category WHERE category_name = 'Beauty'),
        'https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png'
    );

INSERT INTO Products (product_name, product_description, product_price, product_stock_quantity, category_id, product_image)
VALUES
('Chanel Coco Noir Eau De', 
 'Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood. Perfect for evening occasions.', 
 129.99, 
 41, 
 (SELECT category_id FROM category WHERE category_name = 'Beauty'), 
 'https://cdn.dummyjson.com/products/images/fragrances/Chanel%20Coco%20Noir%20Eau%20De/thumbnail.png');


INSERT INTO Products (product_name, product_description, product_price, product_stock_quantity, category_id, product_image)
VALUES
('Dolce Shine Eau de', 
 'Dolce Shine by Dolce & Gabbana is a vibrant and fruity fragrance, featuring notes of mango, jasmine, and blonde woods. Its a joyful and youthful scent.', 
 69.99, 
 3, 
 (SELECT category_id FROM category WHERE category_name = 'Beauty'),  
 'https://cdn.dummyjson.com/products/images/fragrances/Dolce%20Shine%20Eau%20de/thumbnail.png');

INSERT INTO Products (product_name, product_description, product_price, product_stock_quantity, category_id, product_image)
VALUES
('Honey Jar', 
 'Pure and natural honey in a convenient jar, perfect for sweetening beverages or drizzling over food.', 
 6.99, 
 25, 
 (SELECT category_id FROM category WHERE category_name = 'Groceries'),  
 'https://cdn.dummyjson.com/products/images/groceries/Honey%20Jar/thumbnail.png');

INSERT INTO Products (product_name, product_description, product_price, product_stock_quantity, category_id, product_image)
VALUES
('Ice Cream', 
 'Creamy and delicious ice cream, available in various flavors for a delightful treat.', 
 5.49, 
 76, 
 (SELECT category_id FROM category WHERE category_name = 'Groceries'),   
 'https://cdn.dummyjson.com/products/images/groceries/Ice%20Cream/thumbnail.png');

INSERT INTO Products (product_name, product_description, product_price, product_stock_quantity, category_id, product_image)
VALUES
('Juice', 
 'Refreshing fruit juice, packed with vitamins and great for staying hydrated.', 
 3.99, 
 99, 
 (SELECT category_id FROM category WHERE category_name = 'Groceries'),   
 'https://cdn.dummyjson.com/products/images/groceries/Juice/thumbnail.png');

INSERT INTO Products (product_name, product_description, product_price, product_stock_quantity, category_id, product_image)
VALUES
('Kiwi', 
 'Nutrient-rich kiwi, perfect for snacking or adding a tropical twist to your dishes.', 
 2.49, 
 1, 
 (SELECT category_id FROM category WHERE category_name = 'Groceries'),   
 'https://cdn.dummyjson.com/products/images/groceries/Kiwi/thumbnail.png');



ALTER TABLE app_users ADD creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE category ADD category_image BYTEA;

ALTER TABLE app_users ALTER COLUMN user_id SET DEFAULT nextval('user_id_seq');

INSERT INTO roles (role_id, role_name) VALUES (1, 'customer');
INSERT INTO roles (role_id, role_name) VALUES (2, 'admin');
INSERT INTO roles (role_id, role_name) VALUES (3, 'moderator');

INSERT INTO app_users (user_name, user_email, user_password, user_phone_number, user_address, role_id)
VALUES ('admin', 'admin@example.com', '$2a$09$n2qHSa.r.zj50HQ6J7U9KOoST3r299j3fsfs/npCjv1wzfnJHXEmi', '1234567890', '123 Admin Street', 2);

INSERT INTO app_users (user_name, user_email, user_password, user_phone_number, user_address, role_id)
VALUES ('ZeinaOdeh', 'zeinaodeh1234@email.com', '$2a$12$PGK5zIK4KCToBC0ftVAuau9fXQfBv11YWYCbp.C0qWt1UunRB.qbu', '1234567111', 'Salfeet', 2);

SELECT * FROM app_users
SELECT * FROM products
SELECT * FROM roles
SELECT * FROM category
SELECT * FROM cart
DROP TABLE IF EXISTS products;
DELETE FROM app_users WHERE user_id = 68
DELETE FROM products WHERE product_id = 5;
DELETE FROM category WHERE category_id = 15;
DELETE FROM cart WHERE cart_id = 1;
ALTER TABLE category DROP COLUMN category_image;
