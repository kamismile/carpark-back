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

--changeset dbegun-add:2018-06-08
delete from dict_reference where rubric_code = 'position';

INSERT INTO public.dict_reference(
            code, title, system, rubric_code, active)
    VALUES ('director', 'Директор', true, 'position', true);

INSERT INTO public.dict_reference(
            code, title, system, rubric_code, active)
    VALUES ('senior_administrator', 'Старший администратор', true, 'position', true);

INSERT INTO public.dict_reference(
            code, title, system, rubric_code, active)
    VALUES ('senior_rental_manager', 'Старший менеджер проката', true, 'position', true);

INSERT INTO public.dict_reference(
            code, title, system, rubric_code, active)
    VALUES ('senior_service_manager', 'Старший менеджер обслуживания', true, 'position', true);

delete from car_event;

INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (471, 2013, 'volkswagen', 256493.59, '2018-04-26', '2018-05-16', 'decommissioned', '2018-05-27', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'M659CO99', '2018-06-08 09:36:49.737', 'DELETE', 'administrator1', 29.00, NULL);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (335, 2012, 'kia', 2347562.53, '2018-04-17', '2018-05-07', 'decommissioned', '2018-05-31', 'in_service', '2018-07-07', 'DECOMMISSIONED', 3, 3, 'H597TB99', '2018-06-07 20:37:47.563', 'UPDATE', 'service_manager4', 9.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (337, 2012, 'kia', 2347562.53, '2018-04-17', '2018-05-07', 'decommissioned', '2018-05-31', 'in_service', '2018-07-03', 'DECOMMISSIONED', 3, 3, 'H597TB99', '2018-06-07 19:44:07.365', 'UPDATE', 'administrator1', 9.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (477, 2013, 'volkswagen', 234659.43, '2018-05-25', '2018-06-25', 'in_use', '2018-06-08', 'in_use', '2018-06-15', 'IN_USE', 5, 6, 'C234CA77', '2018-06-08 13:23:39.651', 'UPDATE', 'administrator1', 32.00, NULL);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (478, 2013, 'volkswagen', 234659.43, '2018-05-25', '2018-06-25', 'decommissioned', '2018-06-08', 'in_use', '2018-06-15', 'DECOMMISSIONED', 5, 6, 'C234CA77', '2018-06-08 13:23:57.524', 'UPDATE', 'administrator1', 32.00, NULL);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (339, 2010, 'ford', 3107642.18, '2018-02-26', '2018-04-29', 'DECOMMISSIONED', '2018-04-19', NULL, NULL, 'READY', 3, NULL, 'A675OB77', '2018-06-07 20:06:56.723', 'UPDATE', 'administrator1', 20.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (340, 2017, 'renault', 22156.49, '2018-04-25', '2018-06-09', 'ready', '2018-06-01', NULL, NULL, 'READY', 2, 3, 'O465MA99', '2018-06-07 20:06:59.765', 'ADD', 'administrator1', 21.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (341, 2015, 'mazda', 12447.99, '2018-04-11', '2018-06-20', 'ready', '2018-05-07', 'in_use', '2018-06-08', 'READY', 3, 3, 'T669HM99', '2018-06-07 20:06:59.767', 'ADD', 'administrator1', 22.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (342, 2018, 'lexus', 691.13, '2018-05-26', '2018-06-16', 'in_service', '2018-05-31', 'ready', '2018-06-06', 'IN_SERVICE', 3, 4, 'O765BA99', '2018-06-07 20:06:59.767', 'ADD', 'administrator1', 23.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (343, 2013, 'mazda', 156860.60, '2018-05-05', '2018-07-01', 'in_use', '2018-06-02', 'ready', '2018-06-24', 'IN_USE', 3, 3, 'O467AC77', '2018-06-07 20:06:59.768', 'ADD', 'administrator1', 24.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (344, 2012, 'volkswagen', 127156.49, '2018-05-14', '2018-06-16', 'ready', '2018-06-03', 'in_service', '2018-06-16', 'READY', 3, 3, 'M315MB99', '2018-06-07 20:06:59.769', 'ADD', 'administrator1', 25.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (345, 2012, 'renault', 322456.37, '2018-05-17', '2018-06-27', 'ready', '2018-05-26', 'in_use', '2018-06-20', 'READY', 3, 2, 'C946AO99', '2018-06-07 20:06:59.769', 'ADD', 'administrator1', 26.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (346, 2014, 'peugeot', 229664.19, '2018-05-10', '2018-06-29', 'in_service', '2018-06-03', 'ready', '2018-06-08', 'IN_SERVICE', 3, 4, 'A355CT99', '2018-06-07 20:06:59.77', 'ADD', 'administrator1', 27.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (347, 2015, 'renault', 22659.49, '2018-05-23', '2018-07-21', 'in_use', '2018-06-03', 'ready', '2018-06-20', 'IN_USE', 3, 3, 'A462CM77', '2018-06-07 20:06:59.771', 'ADD', 'administrator1', 28.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (348, 2013, 'volkswagen', 256493.59, '2018-04-26', '2018-05-16', 'decommissioned', '2018-05-27', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'M659CO99', '2018-06-07 20:06:59.771', 'ADD', 'administrator1', 29.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (349, 2010, 'lexus', 3476292.71, '2018-02-25', '2018-03-27', 'decommissioned', '2018-04-06', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'C623OH99', '2018-06-07 20:06:59.772', 'ADD', 'administrator1', 30.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (350, 2014, 'bmw', 53491.29, '2018-05-06', '2018-06-25', 'ready', '2018-05-20', NULL, NULL, 'READY', 5, 5, 'A539OC77', '2018-06-07 20:06:59.772', 'ADD', 'administrator1', 31.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (351, 2013, 'volkswagen', 234659.43, '2018-05-25', '2018-06-25', 'ready', '2018-05-30', 'in_use', '2018-06-15', 'READY', 5, 6, 'C234CA77', '2018-06-07 20:06:59.773', 'ADD', 'administrator1', 32.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (352, 2017, 'mazda', 10594.21, '2018-05-26', '2018-07-05', 'in_service', '2018-06-04', 'ready', '2018-06-11', 'IN_SERVICE', 2, 4, 'C234OA77', '2018-06-07 20:06:59.774', 'ADD', 'administrator1', 33.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (353, 2015, 'bmw', 15468.02, '2018-05-05', '2018-07-05', 'in_use', '2018-05-24', 'ready', '2018-06-30', 'IN_USE', 5, 5, 'C236PO99', '2018-06-07 20:06:59.775', 'ADD', 'administrator1', 34.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (354, 2014, 'ford', 31536.79, '2018-05-12', '2018-07-04', 'ready', '2018-06-01', 'in_service', '2018-07-04', 'READY', 3, 5, 'C112EM99', '2018-06-07 20:06:59.776', 'ADD', 'administrator1', 35.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (355, 2015, 'lexus', 243296.29, '2018-05-15', '2018-07-03', 'ready', '2018-05-26', 'in_use', '2018-06-20', 'READY', 5, 5, 'C234EO99', '2018-06-07 20:06:59.777', 'ADD', 'administrator1', 36.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (356, 2015, 'peugeot', 54624.04, '2018-05-12', '2018-06-27', 'in_service', '2018-06-03', 'ready', '2018-06-07', 'IN_SERVICE', 5, 4, 'T249MH99', '2018-06-07 20:06:59.778', 'ADD', 'administrator1', 37.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (357, 2016, 'kia', 24292.15, '2018-06-01', '2018-07-25', 'in_use', '2018-06-02', 'ready', '2018-07-01', 'IN_USE', 3, 5, 'T329CO77', '2018-06-07 20:06:59.779', 'ADD', 'administrator1', 38.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (358, 2011, 'volkswagen', 232290.46, '2018-01-16', '2018-02-25', 'decommissioned', '2018-01-26', NULL, NULL, 'DECOMMISSIONED', 3, 5, 'A294CM77', '2018-06-07 20:06:59.779', 'ADD', 'administrator1', 39.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (359, 2009, 'kia', 234692.26, '2017-11-27', '2018-01-16', 'decommissioned', '2018-04-19', NULL, NULL, 'DECOMMISSIONED', 7, 5, 'C234MA77', '2018-06-07 20:06:59.783', 'ADD', 'administrator1', 40.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (360, 2017, 'peugeot', 23465.59, '2018-05-11', '2018-07-03', 'ready', '2018-06-03', NULL, NULL, 'READY', 6, 6, 'C346OM99', '2018-06-07 20:06:59.783', 'ADD', 'administrator1', 41.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (361, 2015, 'bmw', 12456.37, '2018-04-06', '2018-06-15', 'ready', '2018-05-06', 'in_use', '2018-06-06', 'READY', 7, 6, '459HM199', '2018-06-07 20:06:59.784', 'ADD', 'administrator1', 42.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (362, 2016, 'renault', 12346.29, '2018-05-31', '2018-06-15', 'in_service', '2018-05-31', 'ready', '2018-06-06', 'IN_SERVICE', 6, 4, 'A128MP99', '2018-06-07 20:06:59.784', 'ADD', 'administrator1', 43.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (363, 2016, 'bmw', 12960.00, '2018-05-07', '2018-06-30', 'in_use', '2018-06-01', 'ready', '2018-06-19', 'IN_USE', 6, 6, 'C462PT99', '2018-06-07 20:06:59.785', 'ADD', 'administrator1', 44.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (364, 2013, 'mercedes', 25156.42, '2018-05-16', '2018-06-22', 'ready', '2018-06-01', 'in_service', '2018-06-22', 'READY', 6, 6, '159MB99', '2018-06-07 20:06:59.786', 'ADD', 'administrator1', 45.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (365, 2013, 'mercedes', 232456.37, '2018-05-20', '2018-08-14', 'ready', '2018-05-26', 'in_use', '2018-06-15', 'READY', 5, 6, 'T198HM199', '2018-06-07 20:06:59.786', 'ADD', 'administrator1', 46.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (366, 2017, 'ford', 29574.19, '2018-05-16', '2018-06-24', 'in_service', '2018-06-04', 'ready', '2018-06-06', 'IN_SERVICE', 6, 4, 'C289CT79', '2018-06-07 20:06:59.787', 'ADD', 'administrator1', 47.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (367, 2016, 'bmw', 26456.59, '2018-05-25', '2018-07-20', 'in_use', '2018-06-01', 'ready', '2018-06-20', 'IN_USE', 6, 6, 'T463AB77', '2018-06-07 20:06:59.787', 'ADD', 'administrator1', 48.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (368, 2013, 'volkswagen', 212562.19, '2018-04-06', '2018-04-26', 'decommissioned', '2018-05-01', NULL, NULL, 'DECOMMISSIONED', 6, 6, 'H236TB99', '2018-06-07 20:06:59.788', 'ADD', 'administrator1', 49.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (369, 2011, 'lexus', 347956.94, '2018-03-17', '2018-04-05', 'decommissioned', '2018-05-07', NULL, NULL, 'DECOMMISSIONED', 6, 6, 'M349KK199', '2018-06-07 20:06:59.789', 'ADD', 'administrator1', 50.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (370, 2014, 'renault', 56556.12, '2018-03-07', '2018-06-06', 'ready', '2018-05-26', NULL, NULL, 'READY', 6, 6, 'E267MK177', '2018-06-07 20:06:59.789', 'ADD', 'administrator1', 51.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (371, 2015, 'peugeot', 26556.76, '2018-05-26', '2018-08-14', 'ready', '2018-05-30', 'in_use', '2018-07-15', 'READY', 7, 6, 'K956MK77', '2018-06-07 20:06:59.79', 'ADD', 'administrator1', 52.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (372, 2014, 'lexus', 326033.19, '2018-04-26', '2018-07-21', 'in_service', '2018-06-04', 'ready', '2018-06-07', 'IN_SERVICE', 6, 4, 'T298KM77', '2018-06-07 20:06:59.79', 'ADD', 'administrator1', 53.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (373, 2015, 'renault', 46860.00, '2018-05-06', '2018-07-15', 'in_use', '2018-05-26', 'ready', '2018-07-10', 'IN_USE', 6, 6, 'M265PT99', '2018-06-07 20:06:59.791', 'ADD', 'administrator1', 54.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (374, 2013, 'mercedes', 31156.29, '2018-05-14', '2018-06-24', 'ready', '2018-06-02', 'in_service', '2018-06-24', 'READY', 7, 6, 'E329EK97', '2018-06-07 20:06:59.792', 'ADD', 'administrator1', 55.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (375, 2013, 'ford', 232457.19, '2018-05-25', '2018-06-23', 'ready', '2018-05-27', 'in_use', '2018-06-20', 'READY', 6, 6, 'T219EH99', '2018-06-07 20:06:59.792', 'ADD', 'administrator1', 56.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (376, 2016, 'bmw', 124584.19, '2018-05-14', '2018-07-01', 'in_service', '2018-06-03', 'ready', '2018-06-07', 'IN_SERVICE', 6, 4, 'T226MH99', '2018-06-07 20:06:59.793', 'ADD', 'administrator1', 57.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (377, 2014, 'mazda', 217562.13, '2018-05-27', '2018-07-12', 'in_use', '2018-06-02', 'ready', '2018-06-18', 'IN_USE', 7, 6, 'T249AB77', '2018-06-07 20:06:59.793', 'ADD', 'administrator1', 58.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (378, 2011, 'kia', 123225.49, '2018-03-07', '2018-03-30', 'decommissioned', '2018-05-30', NULL, NULL, 'DECOMMISSIONED', 7, 6, 'A122OB77', '2018-06-07 20:06:59.794', 'ADD', 'administrator1', 59.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (379, 2011, 'ford', 107642.29, '2018-02-26', '2018-04-29', 'decommissioned', '2018-04-19', NULL, NULL, 'DECOMMISSIONED', 6, 6, 'A699OB77', '2018-06-07 20:06:59.795', 'ADD', 'administrator1', 60.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (380, 2017, 'bmw', 22156.49, '2018-04-25', '2018-06-09', 'ready', '2018-06-01', NULL, NULL, 'READY', 3, 7, 'O234MA99', '2018-06-07 20:06:59.795', 'ADD', 'administrator1', 61.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (381, 2015, 'ford', 12447.19, '2018-04-11', '2018-06-20', 'ready', '2018-05-07', 'in_use', '2018-06-08', 'READY', 7, 7, 'T294HM99', '2018-06-07 20:06:59.796', 'ADD', 'administrator1', 62.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (382, 2018, 'peugeot', 1649.13, '2018-05-24', '2018-06-20', 'in_service', '2018-06-03', 'ready', '2018-06-07', 'IN_SERVICE', 7, 4, 'A319BA99', '2018-06-07 20:06:59.799', 'ADD', 'administrator1', 63.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (383, 2013, 'ford', 26560.14, '2018-05-05', '2018-07-01', 'in_use', '2018-06-02', 'ready', '2018-06-24', 'IN_USE', 7, 7, 'O234MH77', '2018-06-07 20:06:59.799', 'ADD', 'administrator1', 64.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (384, 2012, 'lexus', 127156.49, '2018-05-12', '2018-06-17', 'ready', '2018-06-01', 'in_service', '2018-06-17', 'READY', 7, 7, 'OA659MB99', '2018-06-07 20:06:59.8', 'ADD', 'administrator1', 65.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (385, 2016, 'bmw', 3456.37, '2018-05-15', '2018-07-02', 'ready', '2018-05-26', 'in_use', '2018-06-30', 'READY', 3, 7, 'O329AO99', '2018-06-07 20:06:59.801', 'ADD', 'administrator1', 66.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (386, 2017, 'lada', 2264.69, '2018-05-10', '2018-06-29', 'in_service', '2018-06-03', 'ready', '2018-06-08', 'IN_SERVICE', 7, 4, 'C329CT99', '2018-06-07 20:06:59.802', 'ADD', 'administrator1', 67.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (387, 2015, 'bmw', 22659.49, '2018-05-23', '2018-07-21', 'in_use', '2018-06-03', 'ready', '2018-06-20', 'IN_USE', 7, 7, 'O329CM77', '2018-06-07 20:06:59.802', 'ADD', 'administrator1', 68.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (388, 2013, 'mazda', 232456.37, '2018-05-25', '2018-06-15', 'ready', '2018-05-27', 'in_service', '2018-06-15', 'READY', 2, 2, 'T440EH99', '2018-06-07 20:06:59.803', 'ADD', 'administrator1', 16.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (389, 2015, 'lada', 46860.00, '2018-05-06', '2018-07-08', 'in_use', '2018-05-26', 'in_service', '2018-07-08', 'IN_USE', 2, 2, 'M734PT99', '2018-06-07 20:06:59.803', 'ADD', 'administrator1', 14.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (390, 2015, 'renault', 56860.00, '2018-05-07', '2018-06-15', 'in_use', '2018-06-01', 'in_service', '2018-06-15', 'IN_USE', 2, 2, 'M462PT99', '2018-06-07 20:06:59.804', 'ADD', 'administrator1', 4.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (391, 2016, 'renault', 519574.00, '2018-05-14', '2018-07-01', 'ready', '2018-06-07', 'ready', '2018-06-07', 'READY', 3, 2, 'T226MH99', '2018-06-07 20:06:59.805', 'ADD', 'administrator1', 17.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (392, 2000, 'renault', 17562.13, '2018-05-25', '2018-07-20', 'in_use', '2018-05-30', 'ready', '2018-06-18', 'READY', 123, NULL, 'T463AB05', '2018-06-07 20:06:59.805', 'ADD', 'administrator1', 8.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (393, 2000, 'mazda', 129574.00, '2018-05-06', '2018-06-23', 'in_service', '2018-06-04', 'ready', '2018-06-06', 'READY', 123, NULL, 'E349CT05', '2018-06-07 20:06:59.806', 'ADD', 'administrator1', 7.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (394, 2000, 'mercedes', 232456.37, '2018-05-20', '2018-08-14', 'ready', '2018-05-26', 'in_use', '2018-06-20', 'READY', 123, NULL, 'T478HM105', '2018-06-07 20:06:59.807', 'ADD', 'administrator1', 6.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (395, 2014, 'renault', 52356.22, '2018-03-07', '2018-06-06', 'in_use', '2018-06-07', NULL, NULL, 'IN_USE', 2, 2, 'E442MK177', '2018-06-07 20:06:59.807', 'ADD', 'administrator1', 11.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (396, 2000, 'lexus', 15256.76, '2018-05-26', '2018-08-14', 'ready', '2018-05-30', 'in_use', '2018-07-15', 'READY', 123, NULL, 'K667MK05', '2018-06-07 20:06:59.808', 'ADD', 'administrator1', 12.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (397, 2000, 'volkswagen', 250337.90, '2018-04-26', '2018-07-25', 'in_service', '2018-06-04', 'ready', '2018-06-07', 'READY', 123, NULL, 'T669KM00', '2018-06-07 20:06:59.809', 'ADD', 'administrator1', 13.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (398, 2005, 'bmw', 31156.42, '2018-05-14', '2018-06-24', 'ready', '2018-06-02', 'in_service', '2018-06-24', 'READY', 123, NULL, 'E106EK05', '2018-06-07 20:06:59.809', 'ADD', 'administrator1', 15.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (399, 2000, 'peugeot', 12456.37, '2018-04-06', '2018-06-15', 'ready', '2018-05-06', 'in_use', '2018-06-06', 'READY', 3, 3, 'T478HM105', '2018-06-07 20:06:59.81', 'ADD', 'administrator1', 2.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (400, 2012, 'kia', 2347562.53, '2018-04-16', '2018-05-06', 'decommissioned', '2018-05-30', 'in_service', '2018-07-13', 'DECOMMISSIONED', 3, 3, 'H597TB99', '2018-06-07 20:06:59.81', 'ADD', 'administrator1', 9.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (401, 2014, 'mercedes', 217562.13, '2018-05-27', '2018-07-12', 'ready', '2018-06-07', 'ready', '2018-06-18', 'READY', 3, 2, 'T463AB77', '2018-06-07 20:06:59.811', 'ADD', 'administrator1', 18.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (402, 2010, 'bmw', 25156.42, '2018-05-15', '2018-06-06', 'ready', '2018-06-01', 'in_service', '2018-06-06', 'READY', 2, 3, 'M315MB05', '2018-06-07 20:06:59.812', 'ADD', 'administrator1', 5.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (403, 2000, 'ford', 2322562.57, '2018-03-07', '2018-03-30', 'DECOMMISSIONED', '2018-05-30', NULL, NULL, 'READY', 123, NULL, 'A467OB77', '2018-06-07 20:06:59.814', 'ADD', 'administrator1', 19.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (404, 2000, 'volkswagen', 3347642.91, '2018-03-27', '2018-04-26', 'DECOMMISSIONED', '2018-05-06', NULL, NULL, 'READY', 123, NULL, 'M349KK199', '2018-06-07 20:06:59.815', 'ADD', 'administrator1', 10.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (405, 2010, 'ford', 3107642.18, '2018-02-26', '2018-04-29', 'DECOMMISSIONED', '2018-04-19', NULL, NULL, 'READY', 3, NULL, 'A675OB77', '2018-06-07 20:06:59.816', 'ADD', 'administrator1', 20.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (406, 2013, 'lexus', 256493.59, '2018-04-26', '2018-05-16', 'decommissioned', '2018-05-27', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'M495CO99', '2018-06-07 20:06:59.816', 'ADD', 'administrator1', 69.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (407, 2010, 'peugeot', 3476292.71, '2018-02-25', '2018-03-27', 'decommissioned', '2018-04-06', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'C234OH99', '2018-06-07 20:06:59.817', 'ADD', 'administrator1', 70.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (408, 2014, 'mercedes', 53491.29, '2018-05-06', '2018-06-25', 'ready', '2018-05-20', NULL, NULL, 'READY', 7, 7, 'A539OC77', '2018-06-07 20:06:59.817', 'ADD', 'administrator1', 71.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (409, 2013, 'volkswagen', 234659.43, '2018-05-25', '2018-06-25', 'ready', '2018-05-30', 'in_use', '2018-06-15', 'READY', 6, 7, 'C167CA77', '2018-06-07 20:06:59.818', 'ADD', 'administrator1', 72.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (410, 2017, 'ford', 26594.21, '2018-05-21', '2018-06-25', 'in_service', '2018-06-03', 'ready', '2018-06-11', 'IN_SERVICE', 7, 4, 'C249MH77', '2018-06-07 20:06:59.819', 'ADD', 'administrator1', 73.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (411, 2015, 'mercedes', 15468.02, '2018-05-05', '2018-07-05', 'in_use', '2018-05-24', 'ready', '2018-06-30', 'IN_USE', 7, 7, 'C449PO99', '2018-06-07 20:06:59.819', 'ADD', 'administrator1', 74.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (412, 2014, 'kia', 31536.79, '2018-05-12', '2018-06-14', 'ready', '2018-06-01', 'in_service', '2018-06-14', 'READY', 3, 7, 'C446EM99', '2018-06-07 20:06:59.82', 'ADD', 'administrator1', 75.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (413, 2015, 'peugeot', 243296.29, '2018-05-15', '2018-07-03', 'ready', '2018-05-26', 'in_use', '2018-06-20', 'READY', 7, 7, 'C436EO99', '2018-06-07 20:06:59.82', 'ADD', 'administrator1', 76.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (414, 2015, 'lada', 54494.49, '2018-05-12', '2018-06-27', 'in_service', '2018-06-03', 'ready', '2018-06-07', 'IN_SERVICE', 7, 4, 'T442MH99', '2018-06-07 20:06:59.821', 'ADD', 'administrator1', 77.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (415, 2016, 'volkswagen', 24292.15, '2018-06-01', '2018-07-25', 'in_use', '2018-06-02', 'ready', '2018-07-01', 'IN_USE', 3, 7, 'T442CO77', '2018-06-07 20:06:59.822', 'ADD', 'administrator1', 78.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (416, 2011, 'lexus', 232290.46, '2018-01-16', '2018-02-25', 'decommissioned', '2018-01-26', NULL, NULL, 'DECOMMISSIONED', 2, 7, 'A421CM77', '2018-06-07 20:06:59.822', 'ADD', 'administrator1', 79.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (417, 2009, 'volkswagen', 234692.26, '2017-11-27', '2018-01-16', 'decommissioned', '2018-04-19', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'C845MA77', '2018-06-07 20:06:59.823', 'ADD', 'administrator1', 80.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (418, 2017, 'peugeot', 20158.45, '2018-05-11', '2018-07-03', 'ready', '2018-06-03', NULL, NULL, 'READY', 2, 7, 'X254TA77', '2018-06-07 20:06:59.823', 'ADD', 'administrator1', 81.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (419, 2015, 'lada', 56294.47, '2018-05-06', '2018-07-05', 'ready', '2018-05-16', 'in_use', '2018-06-26', 'READY', 3, 7, 'C245HM99', '2018-06-07 20:06:59.824', 'ADD', 'administrator1', 82.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (420, 2018, 'bmw', 1564.17, '2018-05-17', '2018-06-30', 'in_service', '2018-06-03', 'ready', '2018-06-07', 'IN_SERVICE', 7, 4, 'O435MP97', '2018-06-07 20:06:59.825', 'ADD', 'administrator1', 83.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (421, 2015, 'bmw', 51960.23, '2018-05-12', '2018-06-27', 'in_use', '2018-06-01', 'ready', '2018-06-19', 'IN_USE', 7, 7, 'M226PT99', '2018-06-07 20:06:59.825', 'ADD', 'administrator1', 84.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (422, 2013, 'mercedes', 19563.23, '2018-05-14', '2018-06-24', 'ready', '2018-06-01', 'in_service', '2018-06-24', 'READY', 3, 7, 'M226MB99', '2018-06-07 20:06:59.826', 'ADD', 'administrator1', 85.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (423, 2013, 'mazda', 232456.37, '2018-05-20', '2018-08-14', 'ready', '2018-05-26', 'in_use', '2018-06-20', 'READY', 7, 3, 'T118HM199', '2018-06-07 20:06:59.826', 'ADD', 'administrator1', 86.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (424, 2017, 'ford', 2349.00, '2018-05-04', '2018-07-06', 'in_service', '2018-06-04', 'ready', '2018-06-06', 'IN_SERVICE', 6, 4, 'A245CM77', '2018-06-07 20:06:59.827', 'ADD', 'administrator1', 87.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (425, 2015, 'bmw', 17562.13, '2018-05-25', '2018-07-20', 'in_use', '2018-05-30', 'ready', '2018-06-18', 'IN_USE', 7, 7, 'A349OH77', '2018-06-07 20:06:59.828', 'ADD', 'administrator1', 88.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (426, 2012, 'volkswagen', 2347562.53, '2018-04-14', '2018-05-05', 'decommissioned', '2018-05-30', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'A847TB99', '2018-06-07 20:06:59.828', 'ADD', 'administrator1', 89.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (427, 2011, 'lexus', 23659.92, '2018-03-27', '2018-04-26', 'decommissioned', '2018-05-04', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'M265OA199', '2018-06-07 20:06:59.829', 'ADD', 'administrator1', 90.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (428, 2014, 'bmw', 23465.29, '2018-03-05', '2018-06-08', 'ready', '2018-05-26', NULL, NULL, 'READY', 7, 6, 'O571MK77', '2018-06-07 20:06:59.829', 'ADD', 'administrator1', 91.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (429, 2015, 'peugeot', 15256.76, '2018-05-24', '2018-07-05', 'ready', '2018-05-30', 'in_use', '2018-06-25', 'READY', 6, 3, 'O471MK77', '2018-06-07 20:06:59.83', 'ADD', 'administrator1', 92.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (430, 2014, 'peugeot', 26459.92, '2018-04-24', '2018-07-25', 'in_service', '2018-06-04', 'ready', '2018-06-07', 'IN_SERVICE', 6, 4, 'T235ACM77', '2018-06-07 20:06:59.83', 'ADD', 'administrator1', 93.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (431, 2015, 'renault', 46264.01, '2018-05-06', '2018-07-15', 'in_use', '2018-05-24', 'ready', '2018-07-06', 'IN_USE', 2, 2, 'M234PT99', '2018-06-07 20:06:59.831', 'ADD', 'administrator1', 94.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (432, 2013, 'ford', 232456.37, '2018-05-25', '2018-06-23', 'ready', '2018-05-27', 'in_use', '2018-06-20', 'READY', 2, 2, 'T290EH99', '2018-06-07 20:06:59.832', 'ADD', 'administrator1', 96.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (433, 2016, 'bmw', 512571.19, '2018-05-15', '2018-06-27', 'in_service', '2018-06-02', 'ready', '2018-06-06', 'IN_SERVICE', 7, 4, 'T228MH77', '2018-06-07 20:06:59.832', 'ADD', 'administrator1', 97.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (434, 2014, 'mazda', 217562.29, '2018-05-27', '2018-07-12', 'in_use', '2018-06-02', 'ready', '2018-06-17', 'IN_USE', 3, 2, 'T234AB77', '2018-06-07 20:06:59.833', 'ADD', 'administrator1', 98.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (435, 2011, 'kia', 22262.19, '2018-03-07', '2018-03-30', 'decommissioned', '2018-05-30', NULL, NULL, 'DECOMMISSIONED', 3, 2, 'A225OB77', '2018-06-07 20:06:59.833', 'ADD', 'administrator1', 99.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (436, 2011, 'kia', 31042.12, '2018-03-05', '2018-05-04', 'decommissioned', '2018-04-24', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'A159OB77', '2018-06-07 20:06:59.834', 'ADD', 'administrator1', 100.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (441, 2018, 'lada', 100.00, '2018-05-31', '2018-06-15', 'in_service', '2018-05-31', 'in_use', '2018-04-20', 'IN_SERVICE', 2, 4, 'A777MP777', '2018-06-07 20:06:59.837', 'ADD', 'administrator1', 3.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (448, 2016, 'lexus', 10156.21, '2018-04-21', '2018-07-23', 'ready', '2018-06-04', NULL, NULL, 'READY', 1, NULL, 'X716TA77', '2018-06-07 20:06:59.842', 'ADD', 'administrator1', 1.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (449, 2013, 'mercedes', 31156.43, '2018-05-14', '2018-06-24', 'ready', '2018-06-02', 'in_service', '2018-06-24', 'READY', 3, 2, 'E269EK97', '2018-06-07 20:06:59.842', 'ADD', 'administrator1', 95.00, 3);
INSERT INTO public.car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (336, 2012, 'kia', 2347562.53, '2018-04-17', '2018-05-07', 'decommissioned', '2018-05-31', 'in_service', '2018-07-05', 'DECOMMISSIONED', 3, 3, 'H597TB99', '2018-06-07 19:43:54.345', 'UPDATE', 'rental_manager2', 9.00, 3);

update public.car_event set message_date = current_date -1;

ALTER SEQUENCE car_event_id_seq RESTART WITH 500;
ALTER SEQUENCE employee_id_seq RESTART WITH 500;
ALTER SEQUENCE location_id_seq RESTART WITH 500;
