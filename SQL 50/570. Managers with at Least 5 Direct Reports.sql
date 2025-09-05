SELECT e.name
FROM Employee e
JOIN (
    SELECT managerId, COUNT(*) AS mana
    FROM Employee
    WHERE managerId IS NOT NULL
    GROUP BY managerId
) m ON e.id = m.managerId
WHERE m.mana >= 5;

or
SELECT e.name
FROM Employee e
JOIN Employee b ON e.id = b.managerId
GROUP BY e.id, e.name
HAVING COUNT(*) >= 5;
