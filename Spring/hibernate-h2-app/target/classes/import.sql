DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (id bigserial, score int, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (name, score) VALUES ('Bob', 80), ('Jack', 80), ('John', 80);

DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, price int, title VARCHAR(255), PRIMARY KEY (id));
INSERT INTO products (price, title) VALUES (113, 'Milk'), (700, 'Butter'),(10,  'Bread'),(84,  'Coffee'),(407, 'Honey')