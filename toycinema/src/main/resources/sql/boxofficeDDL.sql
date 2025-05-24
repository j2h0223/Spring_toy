DROP TABLE IF EXISTS film_daily_viewings;
DROP TABLE IF EXISTS film_genre;
DROP TABLE IF EXISTS film_type;
DROP TABLE IF EXISTS film_actor_people;
DROP TABLE IF EXISTS film_director_people;
DROP TABLE IF EXISTS film_youtube;
DROP TABLE IF EXISTS film_image;
DROP TABLE IF EXISTS film_poster;
DROP TABLE IF EXISTS company_distributor_film;
DROP TABLE IF EXISTS company_production_film;
DROP TABLE IF EXISTS member_like_people;
DROP TABLE IF EXISTS member_like_comments;
DROP TABLE IF EXISTS member_reply_comments;
DROP TABLE IF EXISTS member_like_reply;
DROP TABLE IF EXISTS member_comments_film;
DROP TABLE IF EXISTS member_coupon;
DROP TABLE IF EXISTS box_type;
DROP TABLE IF EXISTS box;
DROP TABLE IF EXISTS theater_snack_bar;
DROP TABLE IF EXISTS snack_bar;
DROP TABLE IF EXISTS film_playing_table;
DROP TABLE IF EXISTS film_reservation;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS member_like_film;



DROP TABLE IF EXISTS film;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS coupon;
DROP TABLE IF EXISTS theater;
DROP TABLE IF EXISTS snack;


CREATE TABLE film
(
    id             int primary key auto_increment,
    film_name      varchar(255),
    original_name  varchar(255),
    story          varchar(1000),
    opening_day    date,
    end_day        date,
    running_time   int,
    country        varchar(10),
    film_rating    int,
    is_now_playing boolean  default true,
    url_main       varchar(1000),
    created_at     datetime default now()
);

CREATE TABLE genre
(
    id         int primary key auto_increment,
    genre_name varchar(10),
    created_at datetime default now()
);

CREATE TABLE people
(
    id            int primary key auto_increment,
    name          varchar(255),
    original_name varchar(255),
    birth         date,
    country       varchar(10),
    created_at    datetime default now()
);

CREATE TABLE member
(
    id           int primary key auto_increment,
    account_name varchar(255),
    password     varchar(255),
    nickname     varchar(255),
    email        varchar(255),
    gender       varchar(255),
    birth        date,
    phone        varchar(255),
    is_critic    boolean  default false,
    is_activated boolean  default true,
    created_at   datetime default now()
);

CREATE TABLE type
(
    id         int primary key auto_increment,
    price      double,
    type_name  varchar(255),
    created_at datetime default now()
);

CREATE TABLE company
(
    id           int primary key auto_increment,
    name         varchar(255),
    is_operating boolean  default true,
    created_at   datetime default now()
);

CREATE TABLE snack
(
    id         int primary key auto_increment,
    name       varchar(255),
    price      int,
    created_at datetime default now()
);

CREATE TABLE coupon
(
    id          int primary key auto_increment,
    name        varchar(255),
    expire_date datetime,
    created_at  datetime default now()
);

CREATE TABLE theater
(
    id           int primary key auto_increment,
    name         varchar(255),
    location     varchar(255),
    open_time    time,
    close_time   time,
    is_operating boolean  default true,
    created_at   datetime default now()
);

-- -----------------------------------------------
CREATE TABLE film_poster
(
    id            int primary key auto_increment,
    film_id       int,
    url_sub       varchar(1000),
    original_name varchar(1000),
    created_at    datetime default now()
);

CREATE TABLE film_youtube
(
    id          int primary key auto_increment,
    film_id     int,
    url_youtube varchar(1000),
    created_at  datetime default now()
);


CREATE TABLE film_image
(
    id                 int primary key auto_increment,
    film_id            int,
    original_file_name varchar(1000),
    url_sub            varchar(100),
    created_at         datetime default now()
);



CREATE TABLE film_genre
(
    id         int primary key auto_increment,
    film_id    int,
    genre_id   int,
    created_at datetime default now()
);

CREATE TABLE film_type
(
    id         int primary key auto_increment,
    type_id    int,
    film_id    int,
    created_at datetime default now()
);

CREATE TABLE film_actor_people
(
    id         int primary key auto_increment,
    people_id  int,
    film_id    int,
    created_at datetime default now()
);

CREATE TABLE film_director_people
(
    id         int primary key auto_increment,
    people_id  int,
    film_id    int,
    created_at datetime default now()
);

CREATE TABLE film_daily_viewings
(
    id         int primary key auto_increment,
    film_id    int,
    viewings   int,
    created_at datetime default now()
);

CREATE TABLE company_distributor_film
(
    id         int primary key auto_increment,
    film_id    int,
    company_id int,
    created_at datetime default now()
);

CREATE TABLE company_production_film
(
    id         int primary key auto_increment,
    film_id    int,
    company_id int,
    created_at datetime default now()
);


CREATE TABLE member_like_film
(
    id         int primary key auto_increment,
    member_id  int,
    people_id  int,
    is_like    boolean  default false,
    created_at datetime default now()
);

CREATE TABLE member_like_people
(
    id         int primary key auto_increment,
    member_id  int,
    people_id  int,
    is_like    boolean  default false,
    created_at datetime default now()
);

CREATE TABLE member_comments_film
(
    id                 int primary key auto_increment,
    text               varchar(500),
    point              int,
    member_id          int,
    film_id            int,
    parent_comments_id int,
    is_deleted         boolean  default false,
    created_at         datetime default now()
);

CREATE TABLE member_like_comments
(
    id          int primary key auto_increment,
    member_id   int,
    comments_id int,
    is_like     boolean  default false,
    created_at  datetime default now()
);

CREATE TABLE member_reply_comments
(
    id              int primary key auto_increment,
    text            varchar(255),
    comments_id     int,
    member_id       int,
    parent_reply_id int      default null,
    is_deleted      boolean,
    created_at      datetime default now()
);

CREATE TABLE member_like_reply
(
    id          int primary key auto_increment,
    member_id   int,
    comments_id int,
    is_like     boolean  default false,
    created_at  datetime default now()
);


CREATE TABLE member_coupon
(
    id         int primary key auto_increment,
    member_id  int,
    coupon_id  int,
    is_used    boolean  default false,
    used_at    datetime,
    created_at datetime default now()
);

CREATE TABLE box_type
(
    id         int primary key auto_increment,
    box_id     int,
    type_id    int,
    price      double,
    created_at datetime default now()
);

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

CREATE TABLE theater_snack_bar
(
    id           int primary key auto_increment,
    name         varchar(255),
    theater_id   int,
    floor        varchar(255),
    is_operating boolean  default true,
    created_at   datetime default now()
);

CREATE TABLE snack_bar
(
    id                   int primary key auto_increment,
    theater_snack_bar_id int,
    snack_type_id        int,
    price                double,
    created_at           datetime default now()
);

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

CREATE TABLE film_reservation
(
    id                    int primary key auto_increment,
    price                 double,
    seat                  varchar(255),
    film_playing_table_id int,
    member_id             int,
    state                 varchar(255),
    created_at            datetime default now()

);


CREATE TABLE shopping_cart
(
    id          int primary key auto_increment,
    price       double,
    count       int,
    member_id   int,
    snackbar_id int,
    state       varchar(255),
    created_at  datetime default now()
);



CREATE TABLE reservation
(
    id        int primary key auto_increment,
    count     int,
    createdAt datetime default now()

);

