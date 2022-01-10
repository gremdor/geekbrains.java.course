create table if not exists products (id bigserial primary key, title varchar(255), price numeric);

insert into products(price, title)
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


create table users (
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles (
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);
