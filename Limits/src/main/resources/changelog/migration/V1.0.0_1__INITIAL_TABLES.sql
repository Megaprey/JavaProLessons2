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

create table cards
(
    id                              bigserial       not null,
    account_number                  varchar(255)    null,
    balance                         numeric         null,
    user_id                         bigserial       not null,
    constraint pk_products_id primary key (id),
    constraint pk_products_users_id foreign key (user_id) references users (id)
);

insert into cards (id, account_number, balance, user_id) values
        (1, '123 2321 321', 101.0, 1),
        (2, '123 2321 322', 102.0, 1),
        (3, '123 2321 322', 103.0, 2),
        (4, '123 2321 322', 104.0, 2);

create table limits
(
    id                              bigserial       not null,
    limit                           numeric         null,
    user_id                         bigserial       null,
    constraint pk_users_id primary key (id),
    constraint pk_products_users_id foreign key (user_id) references users (id)
);

insert into limits (id, limit, user_id) values
        (1, 10000.0, 1),
        (2, 10000.0, 2),
        (3, 10000.0, 3),
        (4, 10000.0, 4);
