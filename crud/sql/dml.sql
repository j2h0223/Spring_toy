
DROP TABLE student;
CREATE TABLE student (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
     age INT,
     score INT
);

insert into student(name, age, score)
values ('hana-song', 17, 100);

