create table taskmanager_api.tasks(

    id bigint not null auto_increment,
    title varchar(100) not null,
    description varchar(500) not null,
    dueDate datetime not null,

    primary key(id)

);