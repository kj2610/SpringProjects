drop database if exists customer_db;
create database customer_db;
use  customer_db;

create table hibernate_sequences (
sequence_name varchar(10) not null,
next_val int,
primary key (sequence_name)
);


create table customer (
	customer_id int ,
	email_id varchar(20),
	name varchar(10),
	date_of_birth date,
	constraint ps_customer_id_pk primary key ( customer_id )
);


commit;
select * from customer;



