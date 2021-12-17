create table if not exists products (id bigserial primary key, name varchar(255), cost numeric);

insert into products(cost, name)
values
(1.13, 'Milk'),
(7.00, 'Butter'),
(0.1, 'Bread'),
(0.84, 'Coffee'),
(4.07,'Honey'),
(1.99, 'Potato'),
(6.10, 'Onion'),
(0.31, 'Maffin'),
(12.0, 'Cream'),
(1.17,'Lemon'),

(1.13, 'Squid'),
(7.00, 'Salmon'),
(0.1, 'Cheese'),
(0.84, 'Beef'),
(4.07,'Pork'),
(1.99, 'Chicken'),
(6.10, 'Toothaste'),
(4.31, 'Pasta'),
(1.01, 'Cake'),
(1.17,'Oil');