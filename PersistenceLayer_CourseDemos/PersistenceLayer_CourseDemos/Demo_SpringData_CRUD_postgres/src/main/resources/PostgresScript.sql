drop schema if exists customer_db cascade;
create schema customer_db;
set search_path to customer_db;


create table customer(
   customer_id int,
   email_id varchar(50),
   name varchar(20),
   date_of_birth date,
   constraint ps_customer_id_pk primary key (customer_id)
);


insert into customer (customer_id, email_id, name, date_of_birth) values (1, 'martin@infy.com', 'Martin', current_date-9000);
insert into customer (customer_id, email_id, name, date_of_birth) values (2, 'tim@infy.com', 'Tim', current_date-5000);
insert into customer (customer_id, email_id, name, date_of_birth) values (3, 'jack@infy.com', 'Jack', current_date-6000);

commit;
select * from customer;