CREATE TABLE A (
 id character varying(36),
 login character varying(255),
 password character varying(255),
 first_name character varying(255),
 last_name character varying(255),
 gender character varying(255),
 birthdate character varying(255),
 phonenumber character varying(255)
);

insert into A (id, login, password, first_name, last_name, gender, birthdate, phonenumber) values ('1', 'Paul', '777', 'Павел', 'Готовцев', 'm', '07.07.1992', '89237008776');
insert into A (id, login, password, first_name, last_name) values ('2', 'Dima', '111', 'Дмитрий', 'Рафальский');
insert into A (id, login, password, first_name, last_name) values ('3', 'Olcha', '222', 'Олча', 'Артна');
insert into A (id, login, password, first_name, last_name) values ('4', 'Dimas', '333', 'Дмитрий', 'Карелин');