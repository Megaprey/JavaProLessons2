create table products
(
    id                              bigserial       not null,
    accountNumber                   varchar(255)    null,
    balance                         numeric         null,
    product_type                    varchar(255)    null,
    user_id                          bigserial       not null,
    constraint pk_transactions_id primary key (id)
);

insert into products (id, accountNumber, balance, product_type, user_id) values
        (1, '123 2321 321', 101.0, 'карта', 1),
        (2, '123 2321 322', 102.0, 'карта', 1),
        (3, '123 2321 322', 103.0, 'карта', 1),
        (4, '123 2321 322', 104.0, 'счёт', 1),
        (5, '123 2321 324', 105.0, 'счёт', 3),
        (6, '123 2321 325', 106.0, 'карта', 3),
        (7, '123 2321 325', 107.0, 'карта', 2);
