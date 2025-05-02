drop database if exists trainee_classroom_db;

create database trainee_classroom_db;

use trainee_classroom_db;

create table classroom(
classroom_id varchar(12)  primary key,
building_name varchar(12)  not null,
capacity int  not null,
available_capacity int  not null);

create table trainee(
trainee_id int  primary key auto_increment,
trainee_name varchar(20)  not null,
classroomid varchar(12)  references classroom(classroom_id));

insert into classroom values('SL-6','GEC1',100,98);
insert into classroom values('G-63','GEC2',100,0);
insert into classroom values('L1-72','GEC2',100,100);
insert into classroom values('B1-12','GEC2',200,100);


insert into trainee values(800001,'Scott','SL-6');
insert into trainee values(800002,'Jack','SL-6');
insert into trainee values(800003,'Jill',null);

commit;


select * from classroom;
select * from trainee;
