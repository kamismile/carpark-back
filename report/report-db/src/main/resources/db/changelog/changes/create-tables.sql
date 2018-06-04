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
  user_id VARCHAR(100) NOT NULL
);

INSERT INTO  location (address, location_type)
VALUES ('гор Москва', 'head_office');

INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id )
VALUES ('Иванов', 'Иван', 'Иванович','6543', '123456', '12.05.1994', 1, '01.01.2012', 1, 'managment');

INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id)
VALUES ('Административный', 'Кирил', 'Семенович','6343', '423456', '12.05.1984', 2,'01.01.2012', 1, 'administrator');

INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id)
VALUES ('Прокатный', 'Дмитрий', 'Анатольевич','6578', '133456', '12.05.1974', 3,'01.01.2012', 1, 'rental_manager');

INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id)
VALUES ('Сервис', 'Аркадий', 'Петрович','7578', '136456', '12.05.1974', 4,'01.01.2012', 1, 'service_managerr');

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
