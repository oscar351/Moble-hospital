drop database if exists Project;
create database Project;
use Project;
create table Patient(
	name varchar(20),
    address varchar(20),
    ResidentNum varchar(20),
    gender varchar(20),
    age int,
    no int auto_increment primary key
);
select*from Patient;

create table doctor(
	speciality varchar(20),
    DoctorName varchar(20),
    Time time,
    Reserv varchar(20)
);
select*from doctor;

create table Wait(
	name varchar(20),
    address varchar(20),
    ResidentNum varchar(20),
    gender varchar(20),
    age int,
    no int,
	speciality varchar(20),
    DoctorName varchar(20),
    Time time,
    price int
);
select*from Wait
order by DoctorName ASC, Time ASC;

commit;


