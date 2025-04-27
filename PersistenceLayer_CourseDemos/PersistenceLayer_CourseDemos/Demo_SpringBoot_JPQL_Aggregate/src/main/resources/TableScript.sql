drop database if exists customer_db;
create database customer_db;
use  customer_db;

create table Account (
	account_number BIGINT not null,
	account_status varchar(10),
	account_type varchar(10),
	balance BIGINT,
	opening_date date,
	primary key (account_number)
);

insert into Account values (5001,'ACTIVE','Savings',12345,'2016-10-25');
insert into Account values (5002,'ACTIVE','Current',899567,'2015-01-21');
insert into Account values (5003,'INACTIVE','Loan',617345,'2016-11-12');
insert into Account values (5004,'ACTIVE','Savings',345324,'2017-12-02');
insert into Account values (5005,'ACTIVE','Demat',45324,'2017-04-08');

commit;

select * from Account;
