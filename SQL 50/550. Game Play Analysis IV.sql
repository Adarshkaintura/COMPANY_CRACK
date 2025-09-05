SELECT 
    ROUND(
        COUNT(DISTINCT a.player_id) / COUNT(DISTINCT b.player_id),
        2
    ) AS fraction
FROM Activity b
LEFT JOIN Activity a
    ON a.player_id = b.player_id
    AND a.event_date = DATE_ADD(b.event_date, INTERVAL 1 DAY)
WHERE b.event_date = (
    SELECT MIN(event_date) 
    FROM Activity 
    WHERE player_id = b.player_id
);
or
SELECT 
    ROUND(
        COUNT(DISTINCT player_id) / 
        (SELECT COUNT(DISTINCT player_id) FROM Activity),
        2
    ) AS fraction
FROM Activity 
WHERE (player_id,date_sub(event_date,interval 1 day)) in(
    select player_id,min(event_date) as first_logic from Activity group by player_id
)

