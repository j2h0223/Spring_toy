drop table if exists operator;

create table operator
(
    id           int primary key auto_increment,
    account_name varchar(30),
    password     varchar(160),
    name         varchar(10),
    created_at   datetime default now()
);
