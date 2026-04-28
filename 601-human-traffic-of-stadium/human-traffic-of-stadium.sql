WITH filtered_stadium AS (
    SELECT *, 
           id - ROW_NUMBER() OVER (ORDER BY id) AS id_group
    FROM Stadium
    WHERE people >= 100
),
group_counts AS (
    SELECT *, 
           COUNT(*) OVER (PARTITION BY id_group) AS cnt
    FROM filtered_stadium
)
SELECT id, visit_date, people
FROM group_counts
WHERE cnt >= 3
ORDER BY visit_date;
