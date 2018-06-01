--liquibase formatted sql
--changeset mirzoevnik:create-table-location
CREATE TABLE location (
  id SERIAL PRIMARY KEY,
  address VARCHAR(300) NOT NULL,
  location_type VARCHAR(100) NOT NULL
);

--changeset mirzoevnik:create-table-employee
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

--changeset rmorenko:2018-06-01
ALTER TABLE employee
ALTER COLUMN user_id  TYPE VARCHAR(100);

--changeset rmorenko:insert-data-location
INSERT INTO  location (address, location_type)
VALUES ('гор Москва', 'head_office')

--changeset rmorenko:insert-data
INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id )
VALUES ('Иванов', 'Иван', 'Иванович','6543', '123456', '12.05.1994', 1, '01.01.2012', 1, 'managment');

INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id)
VALUES ('Административный', 'Кирил', 'Семенович','6343', '423456', '12.05.1984', 2,'01.01.2012', 1, 'administrator');

INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id)
VALUES ('Прокатный', 'Дмитрий', 'Анатольевич','6578', '133456', '12.05.1974', 3,'01.01.2012', 1, 'rental_manager');

INSERT INTO  employee (name, surname, patronymic, passport_series, passport_number, birthday, position, appointment_date, location_id, user_id)
VALUES ('Сервис', 'Аркадий', 'Петрович','7578', '136456', '12.05.1974', 4,'01.01.2012', 1, 'service_managerr');