DROP DATABASE IF EXISTS `Demo_SpringBootJPA_CRUD`;

CREATE DATABASE `Demo_SpringBootJPA_CRUD`;

USE `Demo_SpringBootJPA_CRUD`;

create table customer(
   customer_id int,
   emailid varchar(50),
   name varchar(20),
   date_of_birth date,
   customer_type varchar(10),
   constraint ps_customer_id_pk primary key ( customer_id )
);


insert into customer (customer_id, emailid, name, date_of_birth, customer_type) values (1, 'martin@infy.com', 'martin', sysdate()- interval 9136 day, 'gold');
commit;
select * from customer;