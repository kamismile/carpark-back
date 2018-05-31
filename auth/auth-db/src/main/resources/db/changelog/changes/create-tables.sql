--liquibase formatted sql
--changeset mirzoevnik:create-table-user-info
CREATE TABLE user_info (
  id SERIAL PRIMARY KEY,
  login VARCHAR(100) NOT NULL,
  password_hash VARCHAR(300) NOT NULL,
  role VARCHAR(100) NOT NULL
);
