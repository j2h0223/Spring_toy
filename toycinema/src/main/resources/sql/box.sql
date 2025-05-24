DROP TABLE IF EXISTS box;

CREATE TABLE box
(
    id           int primary key auto_increment,
    name         varchar(255),
    theater_id   int,
    capacity     int,
    location     varchar(255),
    is_operating boolean  default true,
    price        double,
    created_at   datetime default now()
);

insert into box (name, theater_id, capacity, location)
values ('1관', '1', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '1', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '1', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '1', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '1', '200', '5층');


insert into box (name, theater_id, capacity, location)
values ('1관', '2', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '2', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '2', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '2', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '2', '200', '5층');

insert into box (name, theater_id, capacity, location)
values ('1관', '3', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '3', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '3', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '3', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '3', '200', '5층');
insert into box (name, theater_id, capacity, location)
values ('1관', '4', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '4', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '4', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '4', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '4', '200', '5층');
insert into box (name, theater_id, capacity, location)
values ('1관', '5', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '5', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '5', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '5', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '5', '200', '5층');
insert into box (name, theater_id, capacity, location)
values ('1관', '6', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '6', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '6', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '6', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '6', '200', '5층');
insert into box (name, theater_id, capacity, location)
values ('1관', '7', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '7', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '7', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '7', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '7', '200', '5층');

insert into box (name, theater_id, capacity, location)
values ('1관', '8', '100', '1층');

insert into box (name, theater_id, capacity, location)
values ('2관', '8', '200', '2층');

insert into box (name, theater_id, capacity, location)
values ('3관', '8', '300', '3층');

insert into box (name, theater_id, capacity, location)
values ('4DX', '8', '200', '4층');

insert into box (name, theater_id, capacity, location)
values ('IMAX', '8', '200', '5층');