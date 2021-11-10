-- query 1
select * from task
where is_completed = true;

-- query 2
select * from task
where category = 1;

-- union with our two queries
select * from task
where is_completed = true
union
select * from task
where category = 1;

-- union all with our two queries
select * from task
where is_completed = true
union all
select * from task
where category = 1;

-- comparing a view and a materialized view 

-- saving the query in memory, each time we call a view, it executes the query again
create view all_tasks_completed_and_cat_1 as
select * from task
where is_completed = true
union all
select * from task
where category = 1;

select * from all_tasks_completed_and_cat_1;

-- results of a materialized view are stored in memory
create materialized view all_tasks_completed_and_cat_1_materialized as
select * from task
where is_completed = true
union all
select * from task
where category = 1;

refresh materialized view all_tasks_completed_and_cat_1_materialized;

select * from all_tasks_completed_and_cat_1_materialized;

insert into task (task_name, is_completed) values ('complete first qc', true);
-- inserting a new record, we see the changes in our view because the query is executed each time
-- we don't immediately see any changes in our materialized view, we need to refresh to re-execute the query

select * from task
where is_completed = true
except
select * from task
where category = 1;

select * from task
where is_completed = true
intersect 
select * from task
where category = 1;

select * from task 
where category = 1 and is_completed = true;


---- using functions
select count(task_id)
from task;

select count(task_id)
from task 
where is_completed = false;

select count(task_id) "Task Count", category "Category ID"
from task
where is_completed = false -- filter data before aggregation
group by category;
-- having [condition] filters data after aggregation