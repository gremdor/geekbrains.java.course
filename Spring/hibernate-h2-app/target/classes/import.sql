DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, price int, title VARCHAR(255), PRIMARY KEY (id));
INSERT INTO products (price, title) VALUES (113, 'Milk'), (700, 'Butter'),(10,  'Bread'),(84,  'Coffee'),(407, 'Honey')