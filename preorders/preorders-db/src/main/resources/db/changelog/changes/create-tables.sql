--liquibase formatted sql
--changeset mirzoevnik:create-table-preorder
CREATE TABLE preorder (
  id SERIAL PRIMARY KEY,
  client_name VARCHAR(100) NOT NULL,
  client_surname VARCHAR(100) NOT NULL,
  client_patronymic VARCHAR(100) NULL,
  phone VARCHAR(20) NULL,
  email VARCHAR(100) NULL,
  lease_start_date DATE NOT NULL,
  lease_end_date DATE NOT NULL,
  start_location_id INTEGER NOT NULL,
  end_location_id INTEGER NOT NULL
);