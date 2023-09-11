alter table tasks add completed tinyint;
update tasks set completed = 1;
