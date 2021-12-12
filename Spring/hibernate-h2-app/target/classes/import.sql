
DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, price int, title VARCHAR(255), PRIMARY KEY (id));
INSERT INTO products (price, title) VALUES (113, 'Milk'), (700, 'Butter'),(10,  'Bread'),(84,  'Coffee'),(407, 'Honey');

DROP TABLE clients IF EXISTS;
CREATE TABLE IF NOT EXISTS clients (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO clients (name) VALUES ('Bob Vaskin'), ('Masha Elka'), ('Peter Smirnov'), ('Semen');

DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (id bigserial,product_id bigint references products(id),client_id bigint references clients(id),product_count int,created timestamp, price int, PRIMARY KEY (id));

INSERT INTO orders (client_id, product_id, product_count, price, created) VALUES(1, 1, 2, 100, date'2020-08-10'),(1, 2, 1, 690, null),(2, 3, 1, 10, null),(4, 4, 2, 84, null),(4, 1, 5, 90, null),(4, 2, 1, 710, null),(3, 5, 1, 499, null);
