create table limits
(
    id bigserial not null,
    limmit numeric null,
    user_id bigserial not null,
    constraint pk_limits_id primary key (id)
);

insert into limits (limmit, user_id) values
        (10000.0, 1),
        (10000.0, 2),
        (10000.0, 3),
        (10000.0, 4);
