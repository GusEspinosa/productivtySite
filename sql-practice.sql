create table task (
	task_id serial primary key,
	task_name varchar(50),
	is_completed boolean,
	description varchar(100),
	due_date date
)

drop table task;

insert into task values ('take out trash', false, null, '2021-09-10');

insert into task (task_name, due_date) values ('study for qc', '2021-09-12');

delete from task
where task_name = 'study for qc' and description = '2021-09-12';

delete from task
where task_name = 'study for qc' and due_date = '2021-09-12';

update task
set due_date = '2021-09-09';

update task
set due_date = '2021-09-12'
where task_name = 'study for qc';

--------------------------
-- inserting data after adding pk 
--------------------------

insert into task (task_name, is_completed, due_date) values ('take out trash', false, '2021-09-10');

insert into task (task_name, is_completed, due_date) values ('study for qc', false, '2021-09-12');

insert into task (task_name, is_completed, due_date) values ('add jdbc to task demo', false, '2021-09-10');

insert into task (task_name, is_completed, due_date) values ('finish full stack task demo', false, '2021-09-14');

insert into task (task_name, is_completed, due_date) values ('create task console app', true, '2021-09-8');

insert into task (task_name, is_completed, due_date) values ('super cool new uncompleted task', false, '2021-09-8');

-------------------------------
-- querying data from task table
-------------------------------

-- select [columns] or *
-- from [table]
-- where [condition]
-- order by [column] asc/desc

select *
from task;

select *
from task 
order by task_name desc;

select task_name, due_date 
from task;

select task_name, due_date 
from task
where is_completed = false;

select task_name, due_date 
from task
where is_completed = true;

select task_name, due_date 
from task
where is_completed = false
order by due_date;




