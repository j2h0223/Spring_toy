DROP TABLE IF EXISTS film_playing_table;

CREATE TABLE film_playing_table
(
    id          int primary key auto_increment,
    date        date,
    time        time,
    price       int,
    film_id     int,
    box_type_id int,
    created_at  datetime default now()

);

insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '10:00', 10000, 1, 1);
insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '11:00', 10000, 1, 2);
insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '12:00', 10000, 1, 3);
insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '13:00', 10000, 1, 4);
insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '14:00', 10000, 1, 5);
insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '15:00', 10000, 1, 6);
insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '16:00', 10000, 1, 7);
insert into film_playing_table (date, time, price, film_id, box_type_id)
values ('2025-05-23', '17:00', 10000, 1, 8);
