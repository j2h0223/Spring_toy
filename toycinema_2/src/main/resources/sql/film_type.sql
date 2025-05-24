DROP TABLE IF EXISTS film_type;

CREATE TABLE film_type
(
    id         int primary key auto_increment,
    type_id    int,
    film_id    int,
    created_at datetime default now()
);

insert into film_type (type_id, film_id)
values (1, 1);

insert into film_type (type_id, film_id)
values (1, 2);

insert into film_type (type_id, film_id)
values (1, 3);
insert into film_type (type_id, film_id)
values (2, 3);
insert into film_type (type_id, film_id)
values (3, 3);
insert into film_type (type_id, film_id)
values (4, 3);
insert into film_type (type_id, film_id)
values (5, 3);
insert into film_type (type_id, film_id)
values (6, 3);

insert into film_type (type_id, film_id)
values (1, 4);

insert into film_type (type_id, film_id)
values (1, 5);
insert into film_type (type_id, film_id)
values (2, 5);
insert into film_type (type_id, film_id)
values (7, 5);


insert into film_type (type_id, film_id)
values (1, 6);
insert into film_type (type_id, film_id)
values (2, 6);
insert into film_type (type_id, film_id)
values (3, 6);

insert into film_type (type_id, film_id)
values (1, 7);

insert into film_type (type_id, film_id)
values (1, 8);
insert into film_type (type_id, film_id)
values (2, 8);
insert into film_type (type_id, film_id)
values (3, 8);
insert into film_type (type_id, film_id)
values (4, 8);
insert into film_type (type_id, film_id)
values (6, 8);
insert into film_type (type_id, film_id)
values (8, 8);

insert into film_type (type_id, film_id)
values (1, 9);

insert into film_type (type_id, film_id)
values (1, 10);

insert into film_type (type_id, film_id)
values (1, 11);
insert into film_type (type_id, film_id)
values (2, 11);
insert into film_type (type_id, film_id)
values (3, 11);
insert into film_type (type_id, film_id)
values (5, 11);

insert into film_type (type_id, film_id)
values (1, 12);

insert into film_type (type_id, film_id)
values (1, 13);
insert into film_type (type_id, film_id)
values (2, 13);
insert into film_type (type_id, film_id)
values (3, 13);

insert into film_type (type_id, film_id)
values (1, 14);
insert into film_type (type_id, film_id)
values (2, 14);
insert into film_type (type_id, film_id)
values (4, 14);
insert into film_type (type_id, film_id)
values (5, 14);
insert into film_type (type_id, film_id)
values (9, 14);

insert into film_type (type_id, film_id)
values (1, 15);
insert into film_type (type_id, film_id)
values (1, 16);
insert into film_type (type_id, film_id)
values (1, 17);
