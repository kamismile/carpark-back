--liquibase formatted sql
--changeset rmorenko:create-table-report2
CREATE TABLE car_event (
  id SERIAL PRIMARY KEY,
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
  message_date DATE NULL,
  fio VARCHAR(300) NULL,
  address VARCHAR(300) NULL,
  mark_desc VARCHAR(300) NULL,
  curent_status_desc VARCHAR(300) NULL,
  next_status_desc VARCHAR(300) NULL,
  USER_NAME VARCHAR(300) NULL

 );
 --changeset rmorenko:create-table-report3
 CREATE SEQUENCE hibernate_sequence INCREMENT BY 1;


