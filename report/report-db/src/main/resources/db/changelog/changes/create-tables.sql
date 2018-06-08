--liquibase formatted sql
--changeset rmorenko:create-tables
CREATE TABLE car_event (
  ID SERIAL PRIMARY KEY,
  year INTEGER NOT NULL,
  mark VARCHAR(100) NOT NULL,
  mileage NUMERIC(10, 2) NOT NULL,
  prev_maintenance_date DATE NULL,
  next_maintenance_date DATE NULL,
  current_status VARCHAR(100) NOT NULL,
  current_status_date DATE NOT NULL,
  next_status VARCHAR(100) NULL,
  next_status_date DATE NULL,
  state VARCHAR(100) NOT NULL,
  location_id INTEGER NOT NULL,
  current_location_id INTEGER NULL,
  reg_number VARCHAR(10) NOT NULL,
  MESSAGE_DATE DATE NOT NULL,
  MESSAGE_TYPE VARCHAR(10) NOT NULL,
  USER_NAME VARCHAR(100) NOT NULL,
  CAR_ID NUMERIC(10, 2)
 );

 CREATE TABLE location (
  id SERIAL PRIMARY KEY,
  address VARCHAR(300) NOT NULL,
  location_type VARCHAR(100) NOT NULL
);

CREATE TABLE dict_rubric (
  code VARCHAR(100) NOT NULL PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  system BOOLEAN NOT NULL
);

CREATE TABLE dict_reference (
  code VARCHAR(100) NOT NULL PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  system BOOLEAN NOT NULL,
  rubric_code VARCHAR(100) REFERENCES dict_rubric(code)
);

INSERT INTO dict_rubric(code, title, system)
  VALUES ('car_status', 'Статус автомобиля', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('employee_role', 'Роль сотрудника', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('location_type', 'Тип локации', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('position', 'Должность', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('car_mark', 'Марка автомобиля', TRUE);

INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('rental_station', 'Пункт проката', TRUE, 'location_type');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('service_station', 'Пункт обслуживания', TRUE, 'location_type');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('head_office', 'Головной офис', TRUE, 'location_type');

INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('ready', 'В наличии', TRUE, 'car_status');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('in_use', 'В прокате', TRUE, 'car_status');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('in_service', 'На обслуживании', TRUE, 'car_status');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('decommissioned', 'Выбыл из автопарка', TRUE, 'car_status');

INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('management', 'Руководство', TRUE, 'employee_role');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('rental_manager', 'Менеджер по прокату', TRUE, 'employee_role');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('service_manager', 'Менеджер по обслуживанию', TRUE, 'employee_role');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('administrator', 'Администратор', TRUE, 'employee_role');

INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('lexus', 'Lexus', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('bmw', 'BMW', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('audi', 'Audi', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('mercedes', 'Mercedes-Benz', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('ford', 'Ford', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('kia', 'KIA', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('mazda', 'Mazda', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('volkswagen', 'Volkswagen', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('lada', 'LADA', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('renault', 'Renault', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('peugeot', 'Peugeot', TRUE, 'car_mark');

--changeset rmorenko:2018-06-02
ALTER TABLE  dict_reference
ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

UPDATE dict_reference set active = true;

--changeset rmorenko:2018-06-06-11
DELETE FROM public.location;


----- ЦО
INSERT INTO public.location(
  id, address, location_type)
VALUES ('1', 'Лубянский пр-д, 9 строение 1, Москва, 101000', 'head_office');


-- прокат 1
INSERT INTO public.location(
  id, address, location_type)
VALUES ('2', '1-я Брестская ул., 2, Москва, 125047', 'rental_station');

--прокат
INSERT INTO public.location(
  id, address, location_type)
VALUES ('3', 'Кожевническая ул., 2, Москва, 115114', 'rental_station');

-- пункт обслуживания
INSERT INTO public.location(
  id, address, location_type)
VALUES ('4', 'Лубянский пр-д, 9 строение 1, Москва, 101000', 'service_station');

-- прокат
INSERT INTO public.location(
  id, address, location_type)
VALUES ('5', 'Рогожский Вал ул., 9/2, Москва, 109544', 'rental_station');

-- прокат
INSERT INTO public.location(
  id, address, location_type)
VALUES ('6', 'Авиамоторная ул., 30А, Москва, 111024', 'rental_station');

-- прокат
INSERT INTO public.location(
  id, address, location_type)
VALUES ('7', 'Крюковская ул., 23, Москва, 111020', 'rental_station');

ALTER SEQUENCE location_id_seq RESTART WITH 500;

--changeset rmorenko:2018-06-06-12
ALTER TABLE  location
ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

UPDATE location set active = true;

--changeset rmorenko:2018-06-06-13
CREATE TABLE user_info (
  id SERIAL PRIMARY KEY,
  login VARCHAR(100) NOT NULL,
  password_hash VARCHAR(300) NOT NULL,
  role VARCHAR(100) NOT NULL
);

ALTER TABLE user_info
 ADD COLUMN LOCATION_ID BIGINT;

-- администратор
INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator1', 'administrator1', 'administrator', '1', '1');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('management1', 'management1', 'management', '1', '2');

----
----- Пользователи для точки проката 2

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator2', 'administrator2', 'administrator', '2', '3');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager2', 'rental_manager2', 'rental_manager', '2', '4');

---
---Пользователи для точки проката 3

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator3', 'administrator3', 'administrator', '3', '5');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager3', 'rental_manager3', 'rental_manager', '3', '6');
---
---Пользователи для точки обслуживания 4

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator4', 'administrator4', 'administrator', '4', '7');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('service_manager4', 'service_manager4', 'service_manager', '4', '8');
---
---Пользователи для точки проката 5

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator5', 'administrator5', 'administrator', '5', '9');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager5', 'rental_manager5', 'rental_manager', '5', '10');
---
---Пользователи для точки проката 6

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator6', 'administrator6', 'administrator', '6', '11');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager6', 'rental_manager6', 'rental_manager', '6', '12');
---
---Пользователи для точки проката 7

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator7', 'administrator7', 'administrator', '7', '13');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager7', 'rental_manager7', 'rental_manager', '7', '14');

ALTER SEQUENCE user_info_id_seq RESTART WITH 500;

--changeset rmorenko:2018-06-06-15
ALTER TABLE user_info
ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

--changeset rmorenko:2018-06-06-16
CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  patronymic VARCHAR(100) NULL,
  passport_series VARCHAR(4) NOT NULL,
  passport_number VARCHAR(6) NOT NULL,
  birthday DATE NOT NULL,
  position VARCHAR(100) NOT NULL,
  appointment_date DATE NOT NULL,
  location_id INTEGER REFERENCES location(id) NOT NULL,
  user_id INTEGER NOT NULL
);
--changeset rmorenko:2018-06-06-17
ALTER TABLE  employee
ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

UPDATE employee set active = true;

INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('1', 'Николай', 'Филиппов', 'Евгеньевич', '1624','326497' ,
             '1986.10.12', 'administrator', current_date - 950, '1', '1');
---

INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('2', 'Станислав', 'Брагин', 'Гавриилович', '2945','206534' ,
             '1979.05.04', 'management', current_date - 250, '1', '2');
---

---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('3', 'Василий', 'Филиппов', 'Николаевич', '6426','244653' ,
             '1995.05.02', 'administrator', current_date - 170, '2', '3');
---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('4', 'Вячеслав', 'Самойлов', 'Андрейевич', '4653','234875' ,
             '1979.03.01', 'rental_manager', current_date - 300, '2', '4');
---

---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('5', 'Тимофей', 'Лобанов', 'Владимирович', '5976','234751' ,
             '1979.12.13', 'administrator', current_date - 290, '3', '5');
---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('6', 'Светлана', 'Меркушева', 'Олеговна', '2349','234759' ,
             '1979.02.23', 'rental_manager', current_date - 280, '3', '6');
---

---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('7', 'Игорь', 'Кононов', 'Борисович', '4628','125470' ,
             '1988.12.13', 'administrator', current_date - 190, '4', '7');
---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('8', 'Павел', 'Стрелков', 'Вячеславович', '2580','120579' ,
             '1982.03.03', 'rental_manager', current_date - 220, '4', '8');
---

---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('9', 'Марина', 'Стрелкова', 'Станиславовна', '2580','349520' ,
             '1998.12.12', 'administrator', current_date - 30, '5', '9');
---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('10', 'Людмила', 'Ларионова', 'Ярославовна', '2584','659120' ,
              '1979.11.11', 'rental_manager', current_date - 220, '5', '10');
---

---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('11', 'Артём', 'Лаврентьев', 'Эдуардович', '3490','259459' ,
              '1988.03.02', 'administrator', current_date - 160, '6', '11');
---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('12', 'Святослав', 'Дроздов', 'Владиславович', '5640','594387' ,
              '1984.12.02', 'rental_manager', current_date - 210, '6', '12');
---

---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('13', 'Гавриил', 'Соболев', 'Станиславович', '9564','234590' ,
              '1991.03.01', 'administrator', current_date - 170, '7', '13');
---
INSERT INTO public.employee(
  id, name, surname, patronymic, passport_series, passport_number,
  birthday, "position", appointment_date, location_id, user_id)
VALUES ('14', 'Аркадий', 'Уваров', 'Валентинович', '4851','349501' ,
              '1990.01.04', 'rental_manager', current_date - 110, '7', '14');

ALTER SEQUENCE employee_id_seq RESTART WITH 500;

--changeset rmorenko:alter-tables
ALTER TABLE  car_event
ADD COLUMN employee_id BIGINT;

--changeset rmorenko:alter-tables-2
ALTER TABLE  car_event
ALTER COLUMN next_maintenance_date TYPE TIMESTAMP;

--changeset rmorenko:alter-tables-3
ALTER TABLE  car_event
ALTER COLUMN next_maintenance_date TYPE DATE;

--changeset rmorenko:alter-tables-4
ALTER TABLE  car_event
ALTER COLUMN MESSAGE_DATE TYPE TIMESTAMP;

