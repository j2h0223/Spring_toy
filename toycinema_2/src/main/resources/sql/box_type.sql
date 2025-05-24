DROP TABLE IF EXISTS box_type;

CREATE TABLE box_type
(
    id         int primary key auto_increment,
    box_id     int,
    type_id    int,
    price      double,
    created_at datetime default now()
);

insert into box_type (box_id, type_id)
values (1, 1);

insert into box_type (box_id, type_id)
values (2, 1);

insert into box_type (box_id, type_id)
values (3, 1);

insert into box_type (box_id, type_id)
values (4, 1);

insert into box_type (box_id, type_id)
values (5, 1);

insert into box_type (box_id, type_id)
values (6, 1);

insert into box_type (box_id, type_id)
values (7, 1);

insert into box_type (box_id, type_id)
values (8, 1);

insert into box_type (box_id, type_id)
values (9, 1);

insert into box_type (box_id, type_id)
values (10, 1);