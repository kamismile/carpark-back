--liquibase formatted sql
--changeset rmorenko:insert_tables

delete from car_event;
delete from employee;
delete from user_info;
delete from location;
delete from dict_reference;
delete from dict_rubric;

--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: report
--

----- ЦО
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('1', '(ЦО) Лубянский пр-д, 9 строение 1, Москва, 101000', 'head_office');
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
    VALUES ('4', '(Станция обсуживнаия) Лубянский пр-д, 9 строение 1, Москва, 101000', 'service_station');
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

--
-- Data for Name: dict_reference; Type: TABLE DATA; Schema: public; Owner: report
--

--
-- Data for Name: dict_rubric; Type: TABLE DATA; Schema: public; Owner: report
--

INSERT INTO public.dict_rubric (code, title, system) VALUES ('car_status', 'Статус автомобиля', true);
INSERT INTO public.dict_rubric (code, title, system) VALUES ('employee_role', 'Роль сотрудника', true);
INSERT INTO public.dict_rubric (code, title, system) VALUES ('location_type', 'Тип локации', true);
INSERT INTO public.dict_rubric (code, title, system) VALUES ('position', 'Должность', true);
INSERT INTO public.dict_rubric (code, title, system) VALUES ('car_mark', 'Марка автомобиля', true);


INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('rental_station', 'Пункт проката', true, 'location_type', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('service_station', 'Пункт обслуживания', true, 'location_type', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('head_office', 'Головной офис', true, 'location_type', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('ready', 'В наличии', true, 'car_status', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('in_use', 'В прокате', true, 'car_status', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('in_service', 'На обслуживании', true, 'car_status', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('decommissioned', 'Выбыл из автопарка', true, 'car_status', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('management', 'Руководство', true, 'employee_role', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('rental_manager', 'Менеджер по прокату', true, 'employee_role', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('service_manager', 'Менеджер по обслуживанию', true, 'employee_role', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('administrator', 'Администратор', true, 'employee_role', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('lexus', 'Lexus', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('bmw', 'BMW', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('audi', 'Audi', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('mercedes', 'Mercedes-Benz', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('ford', 'Ford', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('kia', 'KIA', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('mazda', 'Mazda', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('volkswagen', 'Volkswagen', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('lada', 'LADA', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('renault', 'Renault', true, 'car_mark', true);
INSERT INTO public.dict_reference (code, title, system, rubric_code, active) VALUES ('peugeot', 'Peugeot', true, 'car_mark', true);

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
--
-- Data for Name: car_event; Type: TABLE DATA; Schema: public; Owner: report
--
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (1, 2017, 'lexus', 10156.22, '2018-04-25', '2018-07-27', 'ready', '2018-06-08', NULL, NULL, 'READY', 2, 2, 'X716TA77', '2018-06-09', 'ADD', 'administrator', 1, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (2, 2015, 'peugeot', 12456.37, '2018-04-10', '2018-06-19', 'ready', '2018-05-10', 'in_use', '2018-06-10', 'READY', 3, 2, 'T478HM199', '2018-06-09', 'ADD', 'administrator', 2, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (3, 2018, 'lada', 100.00, '2018-06-04', '2018-06-19', 'in_service', '2018-06-04', 'ready', '2018-06-10', 'IN_SERVICE', 2, 4, 'A777MP777', '2018-06-09', 'ADD', 'administrator', 3, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (4, 2015, 'renault', 56860.00, '2018-05-11', '2018-07-04', 'in_use', '2018-06-05', 'ready', '2018-06-23', 'IN_USE', 2, 2, 'M462PT99', '2018-06-09', 'ADD', 'administrator', 4, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (5, 2013, 'bmw', 25156.42, '2018-05-20', '2018-06-10', 'ready', '2018-06-05', 'in_service', '2018-06-10', 'READY', 3, 3, 'M315MB99', '2018-06-09', 'ADD', 'administrator', 5, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (6, 2013, 'mercedes', 232456.37, '2018-05-24', '2018-08-18', 'ready', '2018-05-30', 'in_use', '2018-06-24', 'READY', 2, 3, 'T478HM199', '2018-06-09', 'ADD', 'administrator', 6, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (7, 2015, 'mazda', 129574.00, '2018-05-10', '2018-06-27', 'in_service', '2018-06-08', 'ready', '2018-06-10', 'IN_SERVICE', 3, 4, 'E349CT79', '2018-06-09', 'ADD', 'administrator', 7, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (8, 2015, 'renault', 17562.13, '2018-05-29', '2018-07-24', 'in_use', '2018-06-03', 'ready', '2018-06-22', 'IN_USE', 3, 3, 'T463AB77', '2018-06-09', 'ADD', 'administrator', 8, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (9, 2012, 'kia', 2347562.53, '2018-04-20', '2018-05-10', 'decommissioned', '2018-06-03', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'H597TB99', '2018-06-09', 'ADD', 'administrator', 9, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (10, 2011, 'volkswagen', 3347642.91, '2018-03-31', '2018-04-30', 'decommissioned', '2018-05-10', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'M349KK199', '2018-06-09', 'ADD', 'administrator', 10, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (11, 2014, 'renault', 52356.22, '2018-03-11', '2018-06-10', 'ready', '2018-05-30', NULL, NULL, 'READY', 2, 2, 'E442MK177', '2018-06-09', 'ADD', 'administrator', 11, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (12, 2015, 'lexus', 15256.76, '2018-05-30', '2018-08-18', 'ready', '2018-06-03', 'in_use', '2018-07-19', 'READY', 3, 2, 'K667MK77', '2018-06-09', 'ADD', 'administrator', 12, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (13, 2014, 'volkswagen', 250337.90, '2018-04-30', '2018-07-29', 'in_service', '2018-06-08', 'ready', '2018-06-11', 'IN_SERVICE', 2, 4, 'T669KM77', '2018-06-09', 'ADD', 'administrator', 13, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (14, 2015, 'lada', 46860.00, '2018-05-10', '2018-07-19', 'in_use', '2018-05-30', 'ready', '2018-07-14', 'IN_USE', 2, 2, 'M734PT99', '2018-06-09', 'ADD', 'administrator', 14, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (15, 2013, 'bmw', 31156.42, '2018-05-18', '2018-06-28', 'ready', '2018-06-06', 'in_service', '2018-06-28', 'READY', 3, 2, 'E106EK97', '2018-06-09', 'ADD', 'administrator', 15, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (16, 2013, 'mazda', 232456.37, '2018-05-29', '2018-06-27', 'ready', '2018-05-31', 'in_use', '2018-06-24', 'READY', 2, 2, 'T440EH99', '2018-06-09', 'ADD', 'administrator', 16, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (17, 2016, 'renault', 519574.00, '2018-05-18', '2018-07-05', 'in_service', '2018-06-07', 'ready', '2018-06-11', 'IN_SERVICE', 3, 2, 'T226MH99', '2018-06-09', 'ADD', 'administrator', 17, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (18, 2014, 'mercedes', 217562.13, '2018-05-31', '2018-07-16', 'in_use', '2018-06-06', 'ready', '2018-06-22', 'IN_USE', 3, 2, 'T463AB77', '2018-06-09', 'ADD', 'administrator', 18, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (19, 2011, 'ford', 2322562.57, '2018-03-11', '2018-04-03', 'decommissioned', '2018-06-03', NULL, NULL, 'DECOMMISSIONED', 3, 2, 'A467OB77', '2018-06-09', 'ADD', 'administrator', 19, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (20, 2011, 'ford', 3107642.18, '2018-03-02', '2018-05-03', 'decommissioned', '2018-04-23', NULL, NULL, 'DECOMMISSIONED', 3, 2, 'A675OB77', '2018-06-09', 'ADD', 'administrator', 20, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (21, 2017, 'renault', 22156.49, '2018-04-29', '2018-06-13', 'ready', '2018-06-05', NULL, NULL, 'READY', 2, 3, 'O465MA99', '2018-06-09', 'ADD', 'administrator', 21, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (22, 2015, 'mazda', 12447.99, '2018-04-15', '2018-06-24', 'ready', '2018-05-11', 'in_use', '2018-06-12', 'READY', 3, 3, 'T669HM99', '2018-06-09', 'ADD', 'administrator', 22, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (23, 2018, 'lexus', 691.13, '2018-05-30', '2018-06-20', 'in_service', '2018-06-04', 'ready', '2018-06-10', 'IN_SERVICE', 3, 4, 'O765BA99', '2018-06-09', 'ADD', 'administrator', 23, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (24, 2013, 'mazda', 156860.60, '2018-05-09', '2018-07-05', 'in_use', '2018-06-06', 'ready', '2018-06-28', 'IN_USE', 3, 3, 'O467AC77', '2018-06-09', 'ADD', 'administrator', 24, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (25, 2012, 'volkswagen', 127156.49, '2018-05-18', '2018-06-20', 'ready', '2018-06-07', 'in_service', '2018-06-20', 'READY', 3, 3, 'M315MB99', '2018-06-09', 'ADD', 'administrator', 25, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (26, 2012, 'renault', 322456.37, '2018-05-21', '2018-07-01', 'ready', '2018-05-30', 'in_use', '2018-06-24', 'READY', 3, 2, 'C946AO99', '2018-06-09', 'ADD', 'administrator', 26, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (27, 2014, 'peugeot', 229664.19, '2018-05-14', '2018-07-03', 'in_service', '2018-06-07', 'ready', '2018-06-12', 'IN_SERVICE', 3, 4, 'A355CT99', '2018-06-09', 'ADD', 'administrator', 27, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (28, 2015, 'renault', 22659.49, '2018-05-27', '2018-07-25', 'in_use', '2018-06-07', 'ready', '2018-06-24', 'IN_USE', 3, 3, 'A462CM77', '2018-06-09', 'ADD', 'administrator', 28, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (29, 2013, 'volkswagen', 256493.59, '2018-04-30', '2018-05-20', 'decommissioned', '2018-05-31', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'M659CO99', '2018-06-09', 'ADD', 'administrator', 29, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (30, 2010, 'lexus', 3476292.71, '2018-03-01', '2018-03-31', 'decommissioned', '2018-04-10', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'C623OH99', '2018-06-09', 'ADD', 'administrator', 30, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (31, 2014, 'bmw', 53491.29, '2018-05-10', '2018-06-29', 'ready', '2018-05-24', NULL, NULL, 'READY', 5, 5, 'A539OC77', '2018-06-09', 'ADD', 'administrator', 31, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (32, 2013, 'volkswagen', 234659.43, '2018-05-29', '2018-06-29', 'ready', '2018-06-03', 'in_use', '2018-06-19', 'READY', 5, 6, 'C234CA77', '2018-06-09', 'ADD', 'administrator', 32, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (33, 2017, 'mazda', 10594.21, '2018-05-30', '2018-07-09', 'in_service', '2018-06-08', 'ready', '2018-06-15', 'IN_SERVICE', 2, 4, 'C234OA77', '2018-06-09', 'ADD', 'administrator', 33, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (34, 2015, 'bmw', 15468.02, '2018-05-09', '2018-07-09', 'in_use', '2018-05-28', 'ready', '2018-07-04', 'IN_USE', 5, 5, 'C236PO99', '2018-06-09', 'ADD', 'administrator', 34, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (35, 2014, 'ford', 31536.79, '2018-05-16', '2018-07-08', 'ready', '2018-06-05', 'in_service', '2018-07-08', 'READY', 3, 5, 'C112EM99', '2018-06-09', 'ADD', 'administrator', 35, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (36, 2015, 'lexus', 243296.29, '2018-05-19', '2018-07-07', 'ready', '2018-05-30', 'in_use', '2018-06-24', 'READY', 5, 5, 'C234EO99', '2018-06-09', 'ADD', 'administrator', 36, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (37, 2015, 'peugeot', 54624.04, '2018-05-16', '2018-07-01', 'in_service', '2018-06-07', 'ready', '2018-06-11', 'IN_SERVICE', 5, 4, 'T249MH99', '2018-06-09', 'ADD', 'administrator', 37, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (38, 2016, 'kia', 24292.15, '2018-06-05', '2018-07-29', 'in_use', '2018-06-06', 'ready', '2018-07-05', 'IN_USE', 3, 5, 'T329CO77', '2018-06-09', 'ADD', 'administrator', 38, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (39, 2011, 'volkswagen', 232290.46, '2018-01-20', '2018-03-01', 'decommissioned', '2018-01-30', NULL, NULL, 'DECOMMISSIONED', 3, 5, 'A294CM77', '2018-06-09', 'ADD', 'administrator', 39, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (40, 2009, 'kia', 234692.26, '2017-12-01', '2018-01-20', 'decommissioned', '2018-04-23', NULL, NULL, 'DECOMMISSIONED', 7, 5, 'C234MA77', '2018-06-09', 'ADD', 'administrator', 40, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (41, 2017, 'peugeot', 23465.59, '2018-05-15', '2018-07-07', 'ready', '2018-06-07', NULL, NULL, 'READY', 6, 6, 'C346OM99', '2018-06-09', 'ADD', 'administrator', 41, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (42, 2015, 'bmw', 12456.37, '2018-04-10', '2018-06-19', 'ready', '2018-05-10', 'in_use', '2018-06-10', 'READY', 7, 6, '459HM199', '2018-06-09', 'ADD', 'administrator', 42, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (43, 2016, 'renault', 12346.29, '2018-06-04', '2018-06-19', 'in_service', '2018-06-04', 'ready', '2018-06-10', 'IN_SERVICE', 6, 4, 'A128MP99', '2018-06-09', 'ADD', 'administrator', 43, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (44, 2016, 'bmw', 12960.00, '2018-05-11', '2018-07-04', 'in_use', '2018-06-05', 'ready', '2018-06-23', 'IN_USE', 6, 6, 'C462PT99', '2018-06-09', 'ADD', 'administrator', 44, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (45, 2013, 'mercedes', 25156.42, '2018-05-20', '2018-06-26', 'ready', '2018-06-05', 'in_service', '2018-06-26', 'READY', 6, 6, '159MB99', '2018-06-09', 'ADD', 'administrator', 45, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (46, 2013, 'mercedes', 232456.37, '2018-05-24', '2018-08-18', 'ready', '2018-05-30', 'in_use', '2018-06-19', 'READY', 5, 6, 'T198HM199', '2018-06-09', 'ADD', 'administrator', 46, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (47, 2017, 'ford', 29574.19, '2018-05-20', '2018-06-28', 'in_service', '2018-06-08', 'ready', '2018-06-10', 'IN_SERVICE', 6, 4, 'C289CT79', '2018-06-09', 'ADD', 'administrator', 47, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (48, 2016, 'bmw', 26456.59, '2018-05-29', '2018-07-24', 'in_use', '2018-06-05', 'ready', '2018-06-24', 'IN_USE', 6, 6, 'T463AB77', '2018-06-09', 'ADD', 'administrator', 48, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (49, 2013, 'volkswagen', 212562.19, '2018-04-10', '2018-04-30', 'decommissioned', '2018-05-05', NULL, NULL, 'DECOMMISSIONED', 6, 6, 'H236TB99', '2018-06-09', 'ADD', 'administrator', 49, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (50, 2011, 'lexus', 347956.94, '2018-03-21', '2018-04-09', 'decommissioned', '2018-05-11', NULL, NULL, 'DECOMMISSIONED', 6, 6, 'M349KK199', '2018-06-09', 'ADD', 'administrator', 50, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (51, 2014, 'renault', 56556.12, '2018-03-11', '2018-06-10', 'ready', '2018-05-30', NULL, NULL, 'READY', 6, 6, 'E267MK177', '2018-06-09', 'ADD', 'administrator', 51, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (52, 2015, 'peugeot', 26556.76, '2018-05-30', '2018-08-18', 'ready', '2018-06-03', 'in_use', '2018-07-19', 'READY', 7, 6, 'K956MK77', '2018-06-09', 'ADD', 'administrator', 52, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (53, 2014, 'lexus', 326033.19, '2018-04-30', '2018-07-25', 'in_service', '2018-06-08', 'ready', '2018-06-11', 'IN_SERVICE', 6, 4, 'T298KM77', '2018-06-09', 'ADD', 'administrator', 53, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (54, 2015, 'renault', 46860.00, '2018-05-10', '2018-07-19', 'in_use', '2018-05-30', 'ready', '2018-07-14', 'IN_USE', 6, 6, 'M265PT99', '2018-06-09', 'ADD', 'administrator', 54, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (55, 2013, 'mercedes', 31156.29, '2018-05-18', '2018-06-28', 'ready', '2018-06-06', 'in_service', '2018-06-28', 'READY', 7, 6, 'E329EK97', '2018-06-09', 'ADD', 'administrator', 55, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (56, 2013, 'ford', 232457.19, '2018-05-29', '2018-06-27', 'ready', '2018-05-31', 'in_use', '2018-06-24', 'READY', 6, 6, 'T219EH99', '2018-06-09', 'ADD', 'administrator', 56, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (57, 2016, 'bmw', 124584.19, '2018-05-18', '2018-07-05', 'in_service', '2018-06-07', 'ready', '2018-06-11', 'IN_SERVICE', 6, 4, 'T226MH99', '2018-06-09', 'ADD', 'administrator', 57, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (58, 2014, 'mazda', 217562.13, '2018-05-31', '2018-07-16', 'in_use', '2018-06-06', 'ready', '2018-06-22', 'IN_USE', 7, 6, 'T249AB77', '2018-06-09', 'ADD', 'administrator', 58, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (59, 2011, 'kia', 123225.49, '2018-03-11', '2018-04-03', 'decommissioned', '2018-06-03', NULL, NULL, 'DECOMMISSIONED', 7, 6, 'A122OB77', '2018-06-09', 'ADD', 'administrator', 59, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (60, 2011, 'ford', 107642.29, '2018-03-02', '2018-05-03', 'decommissioned', '2018-04-23', NULL, NULL, 'DECOMMISSIONED', 6, 6, 'A699OB77', '2018-06-09', 'ADD', 'administrator', 60, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (61, 2017, 'bmw', 22156.49, '2018-04-29', '2018-06-13', 'ready', '2018-06-05', NULL, NULL, 'READY', 3, 7, 'O234MA99', '2018-06-09', 'ADD', 'administrator', 61, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (62, 2015, 'ford', 12447.19, '2018-04-15', '2018-06-24', 'ready', '2018-05-11', 'in_use', '2018-06-12', 'READY', 7, 7, 'T294HM99', '2018-06-09', 'ADD', 'administrator', 62, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (63, 2018, 'peugeot', 1649.13, '2018-05-28', '2018-06-24', 'in_service', '2018-06-07', 'ready', '2018-06-11', 'IN_SERVICE', 7, 4, 'A319BA99', '2018-06-09', 'ADD', 'administrator', 63, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (64, 2013, 'ford', 26560.14, '2018-05-09', '2018-07-05', 'in_use', '2018-06-06', 'ready', '2018-06-28', 'IN_USE', 7, 7, 'O234MH77', '2018-06-09', 'ADD', 'administrator', 64, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (65, 2012, 'lexus', 127156.49, '2018-05-16', '2018-06-21', 'ready', '2018-06-05', 'in_service', '2018-06-21', 'READY', 7, 7, 'OA659MB99', '2018-06-09', 'ADD', 'administrator', 65, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (66, 2016, 'bmw', 3456.37, '2018-05-19', '2018-07-06', 'ready', '2018-05-30', 'in_use', '2018-07-04', 'READY', 3, 7, 'O329AO99', '2018-06-09', 'ADD', 'administrator', 66, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (67, 2017, 'lada', 2264.69, '2018-05-14', '2018-07-03', 'in_service', '2018-06-07', 'ready', '2018-06-12', 'IN_SERVICE', 7, 4, 'C329CT99', '2018-06-09', 'ADD', 'administrator', 67, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (68, 2015, 'bmw', 22659.49, '2018-05-27', '2018-07-25', 'in_use', '2018-06-07', 'ready', '2018-06-24', 'IN_USE', 7, 7, 'O329CM77', '2018-06-09', 'ADD', 'administrator', 68, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (69, 2013, 'lexus', 256493.59, '2018-04-30', '2018-05-20', 'decommissioned', '2018-05-31', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'M495CO99', '2018-06-09', 'ADD', 'administrator', 69, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (70, 2010, 'peugeot', 3476292.71, '2018-03-01', '2018-03-31', 'decommissioned', '2018-04-10', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'C234OH99', '2018-06-09', 'ADD', 'administrator', 70, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (71, 2014, 'mercedes', 53491.29, '2018-05-10', '2018-06-29', 'ready', '2018-05-24', NULL, NULL, 'READY', 7, 7, 'A539OC77', '2018-06-09', 'ADD', 'administrator', 71, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (72, 2013, 'volkswagen', 234659.43, '2018-05-29', '2018-06-29', 'ready', '2018-06-03', 'in_use', '2018-06-19', 'READY', 6, 7, 'C167CA77', '2018-06-09', 'ADD', 'administrator', 72, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (73, 2017, 'ford', 26594.21, '2018-05-25', '2018-06-29', 'in_service', '2018-06-07', 'ready', '2018-06-15', 'IN_SERVICE', 7, 4, 'C249MH77', '2018-06-09', 'ADD', 'administrator', 73, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (74, 2015, 'mercedes', 15468.02, '2018-05-09', '2018-07-09', 'in_use', '2018-05-28', 'ready', '2018-07-04', 'IN_USE', 7, 7, 'C449PO99', '2018-06-09', 'ADD', 'administrator', 74, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (75, 2014, 'kia', 31536.79, '2018-05-16', '2018-06-18', 'ready', '2018-06-05', 'in_service', '2018-06-18', 'READY', 3, 7, 'C446EM99', '2018-06-09', 'ADD', 'administrator', 75, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (76, 2015, 'peugeot', 243296.29, '2018-05-19', '2018-07-07', 'ready', '2018-05-30', 'in_use', '2018-06-24', 'READY', 7, 7, 'C436EO99', '2018-06-09', 'ADD', 'administrator', 76, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (77, 2015, 'lada', 54494.49, '2018-05-16', '2018-07-01', 'in_service', '2018-06-07', 'ready', '2018-06-11', 'IN_SERVICE', 7, 4, 'T442MH99', '2018-06-09', 'ADD', 'administrator', 77, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (78, 2016, 'volkswagen', 24292.15, '2018-06-05', '2018-07-29', 'in_use', '2018-06-06', 'ready', '2018-07-05', 'IN_USE', 3, 7, 'T442CO77', '2018-06-09', 'ADD', 'administrator', 78, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (79, 2011, 'lexus', 232290.46, '2018-01-20', '2018-03-01', 'decommissioned', '2018-01-30', NULL, NULL, 'DECOMMISSIONED', 2, 7, 'A421CM77', '2018-06-09', 'ADD', 'administrator', 79, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (80, 2009, 'volkswagen', 234692.26, '2017-12-01', '2018-01-20', 'decommissioned', '2018-04-23', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'C845MA77', '2018-06-09', 'ADD', 'administrator', 80, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (81, 2017, 'peugeot', 20158.45, '2018-05-15', '2018-07-07', 'ready', '2018-06-07', NULL, NULL, 'READY', 2, 7, 'X254TA77', '2018-06-09', 'ADD', 'administrator', 81, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (82, 2015, 'lada', 56294.47, '2018-05-10', '2018-07-09', 'ready', '2018-05-20', 'in_use', '2018-06-30', 'READY', 3, 7, 'C245HM99', '2018-06-09', 'ADD', 'administrator', 82, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (83, 2018, 'bmw', 1564.17, '2018-05-21', '2018-07-04', 'in_service', '2018-06-07', 'ready', '2018-06-11', 'IN_SERVICE', 7, 4, 'O435MP97', '2018-06-09', 'ADD', 'administrator', 83, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (84, 2015, 'bmw', 51960.23, '2018-05-16', '2018-07-01', 'in_use', '2018-06-05', 'ready', '2018-06-23', 'IN_USE', 7, 7, 'M226PT99', '2018-06-09', 'ADD', 'administrator', 84, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (85, 2013, 'mercedes', 19563.23, '2018-05-18', '2018-06-28', 'ready', '2018-06-05', 'in_service', '2018-06-28', 'READY', 3, 7, 'M226MB99', '2018-06-09', 'ADD', 'administrator', 85, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (86, 2013, 'mazda', 232456.37, '2018-05-24', '2018-08-18', 'ready', '2018-05-30', 'in_use', '2018-06-24', 'READY', 7, 3, 'T118HM199', '2018-06-09', 'ADD', 'administrator', 86, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (87, 2017, 'ford', 2349.00, '2018-05-08', '2018-07-10', 'in_service', '2018-06-08', 'ready', '2018-06-10', 'IN_SERVICE', 6, 4, 'A245CM77', '2018-06-09', 'ADD', 'administrator', 87, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (88, 2015, 'bmw', 17562.13, '2018-05-29', '2018-07-24', 'in_use', '2018-06-03', 'ready', '2018-06-22', 'IN_USE', 7, 7, 'A349OH77', '2018-06-09', 'ADD', 'administrator', 88, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (89, 2012, 'volkswagen', 2347562.53, '2018-04-18', '2018-05-09', 'decommissioned', '2018-06-03', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'A847TB99', '2018-06-09', 'ADD', 'administrator', 89, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (90, 2011, 'lexus', 23659.92, '2018-03-31', '2018-04-30', 'decommissioned', '2018-05-08', NULL, NULL, 'DECOMMISSIONED', 3, 3, 'M265OA199', '2018-06-09', 'ADD', 'administrator', 90, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (91, 2014, 'bmw', 23465.29, '2018-03-09', '2018-06-12', 'ready', '2018-05-30', NULL, NULL, 'READY', 7, 6, 'O571MK77', '2018-06-09', 'ADD', 'administrator', 91, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (92, 2015, 'peugeot', 15256.76, '2018-05-28', '2018-07-09', 'ready', '2018-06-03', 'in_use', '2018-06-29', 'READY', 6, 3, 'O471MK77', '2018-06-09', 'ADD', 'administrator', 92, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (93, 2014, 'peugeot', 26459.92, '2018-04-28', '2018-07-29', 'in_service', '2018-06-08', 'ready', '2018-06-11', 'IN_SERVICE', 6, 4, 'T235ACM77', '2018-06-09', 'ADD', 'administrator', 93, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (94, 2015, 'renault', 46264.01, '2018-05-10', '2018-07-19', 'in_use', '2018-05-28', 'ready', '2018-07-10', 'IN_USE', 2, 2, 'M234PT99', '2018-06-09', 'ADD', 'administrator', 94, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (95, 2013, 'mercedes', 31156.43, '2018-05-18', '2018-06-28', 'ready', '2018-06-06', 'in_service', '2018-06-28', 'READY', 3, 2, 'E269EK97', '2018-06-09', 'ADD', 'administrator', 95, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (96, 2013, 'ford', 232456.37, '2018-05-29', '2018-06-27', 'ready', '2018-05-31', 'in_use', '2018-06-24', 'READY', 2, 2, 'T290EH99', '2018-06-09', 'ADD', 'administrator', 96, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (97, 2016, 'bmw', 512571.19, '2018-05-19', '2018-07-01', 'in_service', '2018-06-06', 'ready', '2018-06-10', 'IN_SERVICE', 7, 4, 'T228MH77', '2018-06-09', 'ADD', 'administrator', 97, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (98, 2014, 'mazda', 217562.29, '2018-05-31', '2018-07-16', 'in_use', '2018-06-06', 'ready', '2018-06-21', 'IN_USE', 3, 2, 'T234AB77', '2018-06-09', 'ADD', 'administrator', 98, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (99, 2011, 'kia', 22262.19, '2018-03-11', '2018-04-03', 'decommissioned', '2018-06-03', NULL, NULL, 'DECOMMISSIONED', 3, 2, 'A225OB77', '2018-06-09', 'ADD', 'administrator', 99, NULL);
INSERT INTO car_event (id, year, mark, mileage, prev_maintenance_date, next_maintenance_date, current_status, current_status_date, next_status, next_status_date, state, location_id, current_location_id, reg_number, message_date, message_type, user_name, car_id, employee_id) VALUES (100, 2011, 'kia', 31042.12, '2018-03-09', '2018-05-08', 'decommissioned', '2018-04-28', NULL, NULL, 'DECOMMISSIONED', 7, 7, 'A159OB77', '2018-06-09', 'ADD', 'administrator', 100, NULL);



ALTER SEQUENCE user_info_id_seq RESTART WITH 500;
ALTER SEQUENCE employee_id_seq RESTART WITH 500;
ALTER SEQUENCE car_event_id_seq RESTART WITH 500;
ALTER SEQUENCE location_id_seq RESTART WITH 500;

UPDATE employee set active = true;
update public.car_event set message_date = current_date -1;

update public.car_event set employee_id = 1;

--changeset rmorenko:insert_tables2
update public.car_event set user_name = 'administrator1';