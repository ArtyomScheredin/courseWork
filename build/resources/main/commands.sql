SELECT p.last_name, AVG(m.value)
FROM people p
         JOIN marks m ON m.student_id = p.person_id
GROUP BY p.person_id
ORDER BY AVG(m.value);