create table users
(
    id                              bigserial       not null,
    name                            varchar(255)    null,
    constraint pk_users_id primary key (id)
);

insert into users (id, name) values
        (1, 'Иван'),
        (2, 'Пётр'),
        (3, 'Антон'),
        (4, 'Сергей');

create table products
(
    id                              bigserial       not null,
    account_number                   varchar(255)    null,
    balance                         numeric         null,
    product_type                    varchar(255)    null,
    user_id                          bigserial       not null,
    constraint pk_products_id primary key (id),
    CONSTRAINT pk_products_users_id FOREIGN KEY (user_id) REFERENCES users (id)
);

insert into products (id, account_number, balance, product_type, user_id) values
        (1, '123 2321 321', 101.0, 'карта', 1),
        (2, '123 2321 322', 102.0, 'счёт', 1),
        (3, '123 2321 322', 103.0, 'карта', 2),
        (4, '123 2321 322', 104.0, 'счёт', 2),
        (5, '123 2321 324', 105.0, 'счёт', 3),
        (6, '123 2321 325', 106.0, 'карта', 3),
        (7, '123 2321 325', 107.0, 'карта', 4);