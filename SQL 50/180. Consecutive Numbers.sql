# Write your MySQL query statement below
select distinct L1.num as ConsecutiveNums
from Logs L1,Logs l2,Logs l3
where L1.num=l2.num and l2.num=l3.num
and L1.id=l2.id-1 and l2.id=l3.id-1;
