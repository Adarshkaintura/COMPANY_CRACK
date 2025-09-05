# Write your MySQL query statement below
 select person_name from (
SELECT person_name,turn,
sum(weight) over (order by turn) AS weight FROM queue
) p
where weight <=1000 order by turn desc limit 1
