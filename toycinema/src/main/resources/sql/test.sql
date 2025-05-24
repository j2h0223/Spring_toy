select g1.*
from people p1
         join film_actor_people fap1 on p1.id = fap1.people_id
         join film_genre fg1 on fap1.film_id = fg1.film_id
         join genre g1 on fg1.genre_id = g1.id
where p1.name = '키아누 리브스';


select p2.id, count(*) as count
from people p2
         join film_actor_people fap2 on p2.id = fap2.people_id
         join film_genre fg2 on fap2.film_id = fg2.film_id
where fg2.genre_id in (select fg1.genre_id
                       from people p1
                                join film_actor_people fap1 on p1.id = fap1.people_id
                                join film_genre fg1 on fap1.film_id = fg1.film_id
                       where p1.name = '키아누 리브스')
group by p2.id;


select p3.name,
       (select count(*)
        from people p2
                 join film_actor_people fap2 on p2.id = fap2.people_id
                 join film_genre fg2 on fap2.film_id = fg2.film_id
        where fg2.genre_id in (select fg1.genre_id
                               from people p1
                                        join film_actor_people fap1 on p1.id = fap1.people_id
                                        join film_genre fg1 on fap1.film_id = fg1.film_id
                               where p1.name = '틸다 스윈튼')
          and p2.id = p3.id
        group by p2.id) as forCount
from people p3
where p3.name != '틸다 스윈튼'
order by forCount desc
limit 5;

select p3.name,
       (select count(*)
        from people p2
                 join film_actor_people fap2 on p2.id = fap2.people_id
                 join film_genre fg2 on fap2.film_id = fg2.film_id
        where fg2.genre_id in (select fg1.genre_id
                               from people p1
                                        join film_actor_people fap1 on p1.id = fap1.people_id
                                        join film_genre fg1 on fap1.film_id = fg1.film_id
                               where p1.name = '틸다 스윈튼')
          and p2.id = p3.id) as forCount
from people p3
where p3.name != '틸다 스윈튼'
order by forCount desc
limit 5;



select *
from film
where film_name like concat('%', 'i', '%')
   or original_name like concat('%', 'i', '%');

select *
from people
where name like concat('%', '키', '%')
   or original_name like concat('%', 's', '%');



select *
from film
where original_name like concat('%', 'i', '%');

# SET time_zone = 'Asia/Seoul';
SET time_zone = DEFAULT;

select now();

-- 쿼리 4: 날짜와 시간을 각각 비교
SELECT *
FROM film_playing_table
WHERE (date > CURDATE()) -- 현재 날짜보다 미래인 경우
   OR (date = CURDATE() AND time > CURTIME());
-- 현재 날짜와 같고, 현재 시간보다 미래인 경우



select distinct date
from film_playing_table
where film_id = 1
ORDER BY date;



