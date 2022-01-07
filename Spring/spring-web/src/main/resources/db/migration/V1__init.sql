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

(3.13, 'Squid'),
(5.00, 'Salmon'),
(4.1,  'Cheese'),
(8.84, 'Beef'),
(7.07, 'Pork'),
(4.99, 'Chicken'),
(1.10, 'Toothaste'),
(2.31, 'Pasta'),
(0.91, 'Cake'),
(1.34, 'Oil');