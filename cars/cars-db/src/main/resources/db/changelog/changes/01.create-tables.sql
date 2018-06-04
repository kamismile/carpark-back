--liquibase formatted sql
--changeset mirzoevnik:create-table-car
CREATE TABLE car (
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
  reg_number VARCHAR(10) NOT NULL
);

--changeset dbegun:create-table-transition
CREATE TABLE transition (
  id SERIAL PRIMARY KEY,
  from_state VARCHAR(100) NOT NULL,
  to_state VARCHAR(100) NOT NULL,
  event VARCHAR(100) NOT NULL
);




