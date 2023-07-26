create table tb_users(
    id       serial constraint tb_users_pkey primary key,
    login    varchar(150),
    password varchar(150),
    status   char,
    role     varchar(20)
);