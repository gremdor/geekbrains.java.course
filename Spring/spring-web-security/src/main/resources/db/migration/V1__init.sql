create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

create table authorities (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

create TABLE roles_authorities (
  role_id               int not null,
  authority_id          int not null,
  primary key (role_id, authority_id),
  foreign key (role_id) references roles (id),
  foreign key (authority_id) references authorities (id)
);

create TABLE users_roles (
  user_id               bigint not null,
  role_id               int,
  authority_id          int,
  unique key (user_id, role_id, authority_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id),
  foreign key (authority_id) references authorities (id)
--  ,check (not(role_id is null and authority_id is null))
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_SUPERADMIN');

insert into authorities (name)
values
('READ_PROFILES'),
('WRITE_PROFILES'),
('UPDATE_PRODUCTS'),
('READ_PRODUCTS'),
('CREATE_USERS'),
('DELETE_USERS');


insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com'),
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com'),
('manager', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'mngr@gmail.com'),
('superadmin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'superadmin@gmail.com')
;

insert into roles_authorities (role_id, authority_id)
values
(1, 1), (1, 4), --ROLE_USER = READ_PROFILES+READ_PRODUCTS
(2, 5), -- ROLE_ADMIN = CREATE_USERS
(3, 3),  -- ROLE_MANAGER = UPDATE_PRODUCTS
(4, 5), (4, 6) --ROLE_SUPERADMIN = CREATE_USERS + DELETE_USERS
;

insert into users_roles (user_id, role_id, authority_id)
values
(1, 1, null), --user can READ_PROFILES+READ_PRODUCTS
(2, 1, 3),    -- user2 as user + UPDATE_PRODUCTS
(3, 2, null), --admin can ADD_USERS only
(5, 4, null), --superadmin = admin with superpower to DELETE_USERS
(4, 3, null), -- manager has ROLE_MANAGER
(4, 1, null)  -- manager has ROLE_MANAGER
;

