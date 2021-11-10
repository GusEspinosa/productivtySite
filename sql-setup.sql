drop table if exists task;

--------------------------
-- creating task table  
--------------------------

create table task (
	task_id serial primary key,
	task_name varchar(50),
	is_completed boolean,
	description varchar(100),
	due_date date
);

--------------------------
-- inserting data after adding pk 
--------------------------

insert into task (task_name, is_completed, due_date) values ('take out trash', false, '2021-09-10');

insert into task (task_name, is_completed, due_date) values ('study for qc', false, '2021-09-12');

insert into task (task_name, is_completed, due_date) values ('add jdbc to task demo', false, '2021-09-10');

insert into task (task_name, is_completed, due_date) values ('finish full stack task demo', false, '2021-09-14');

insert into task (task_name, is_completed, due_date) values ('create task console app', true, '2021-09-8');

insert into task (task_name, is_completed, due_date) values ('super cool new task', true, '2021-09-8');