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
  MESSAGE_DATE timestamp,
  MESSAGE_TYPE VARCHAR(10) NOT NULL,
  USER_NAME VARCHAR(100) NOT NULL,
  CAR_ID INTEGER,
  employee_id INTEGER
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


--changeset rmorenko:2018-06-02
ALTER TABLE  dict_reference
ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

UPDATE dict_reference set active = true;



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



