drop table test2.patient;

create table test2.patient(
	id int auto_increment primary key,
    name varchar(200),
    email varchar(200),
    address varchar(300),
    contactno varchar(30)
);


insert into test2.patient(name, email, address, contactno ) values( "Kirushan S", "kirushan24@gmail.com", "Chankanai 000, Jaffna 00","+94 77 585 2302");
insert into test2.patient(name, email, address, contactno ) values( "Kirushan 1", "kirushan01@gmail.com", "Chankanai 001, Jaffna 01","+94 77 585 0001");
insert into test2.patient(name, email, address, contactno ) values( "Kirushan 2", "kirushan02@gmail.com", "Chankanai 002, Jaffna 02","+94 77 585 0002");
insert into test2.patient(name, email, address, contactno ) values( "Kirushan 3", "kirushan03@gmail.com", "Chankanai 003, Jaffna 03","+94 77 585 0003");
insert into test2.patient(name, email, address, contactno ) values( "Kirushan 4", "kirushan04@gmail.com", "Chankanai 004, Jaffna 04","+94 77 585 0004");
insert into test2.patient(name, email, address, contactno ) values( "Kirushan 5", "kirushan05@gmail.com", "Chankanai 005, Jaffna 05","+94 77 585 0005");