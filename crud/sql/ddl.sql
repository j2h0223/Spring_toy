create database practice;
create user beginner@'%' identified by 'qwer1234';
grant all privileges on practice.* to beginner@'%';
flush privileges;