drop table if exists employee;
drop table if exists department;

create table department(
    id int primary key auto_increment,
    name varchar(255) unique
);

create table employee(
    id int primary key auto_increment,
    name varchar(255),
    salary double,
    department_id int,
    foreign key(department_id) references department(id)
);