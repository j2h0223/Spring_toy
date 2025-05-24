DROP TABLE IF EXISTS film_reservation;

CREATE TABLE film_reservation
(
    id                    int primary key auto_increment,
    count                 int,
    seat                  varchar(255),
    film_playing_table_id int,
    member_id             int,
    created_at            datetime default now()
);


insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 1, 1);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 1, 22);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 1, 3);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 1, 4);

insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 1, 13);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 1, 18);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 1, 19);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 1, 20);


insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 4, 1);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 2, 22);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 3, 33);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 2, 34);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 3, 25);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 4, 6);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 2, 27);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 3, 38);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 4, 9);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 7, 10);



insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 5, 11);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 6, 12);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 8, 23);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 8, 18);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 6, 19);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 5, 20);



insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 9, 5);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 10, 36);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 10, 37);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 9, 8);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 9, 29);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 10, 10);



insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 10, 11);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 10, 32);

insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 12, 11);
insert into film_reservation (count, film_playing_table_id, member_id)
values (2, 12, 22);
insert into film_reservation (count, film_playing_table_id, member_id)
values (3, 12, 15);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 12, 31);
insert into film_reservation (count, film_playing_table_id, member_id)
values (4, 12, 17);
insert into film_reservation (count, film_playing_table_id, member_id)
values (1, 12, 27);

select *
from film_reservation;