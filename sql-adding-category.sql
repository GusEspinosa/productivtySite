--------------------------------
-- create a new category table
--------------------------------
create table category(
	id serial primary key,
	name varchar(50) unique not null
)

-- alter task to accommodate for the category
alter table task 
add column category integer references category;

-- adding some categories to the category table
insert into category (name) values ('training');
insert into category (name) values ('errands');
insert into category (name) values ('around the house');

-- add some category references to the task table
update task
set category = 1
where task_id = 12;

update task
set category = 2
where task_id = 17;


select * 
from category;

select * 
from task;


---------------------------------------
-- using joins to query both tables 
---------------------------------------

-- implicitly an inner join
select *
from task
join category
on task.category = category.id;

select *
from task
inner join category
on task.category = category.id;

select *
from task
full join category
on task.category = category.id;

select * 
from task 
left join category 
on task.category = category.id;

select * 
from task 
right join category 
on task.category = category.id;



