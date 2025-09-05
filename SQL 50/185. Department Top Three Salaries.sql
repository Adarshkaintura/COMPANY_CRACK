SELECT 
    d.name AS Department,
    e.name AS Employee,
    e.salary AS Salary
    from Department d join Employee e
    on d.id=e.departmentId
    and 3>(select count(distinct e2.salary) from
    Employee e2 
    where d.id=e2.departmentId
    and e.salary<e2.salary
    )
