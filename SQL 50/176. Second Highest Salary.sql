SELECT
    (
      SELECT DISTINCT salary
      FROM Employee e1
      WHERE 1 = (
          SELECT COUNT(DISTINCT salary)
          FROM Employee e2
          WHERE e2.salary > e1.salary
      )
    ) AS SecondHighestSalary;

or 

SELECT 
    (
      SELECT DISTINCT salary
      FROM Employee
      ORDER BY salary DESC
      LIMIT 1 OFFSET 1
    ) AS SecondHighestSalary;
