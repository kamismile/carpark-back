--liquibase formatted sql
--changeset mirzoevnik:create-table-rubric
CREATE TABLE dict_rubric (
  code VARCHAR(100) NOT NULL PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  system BOOLEAN NOT NULL
);

--changeset mirzoevnik:create-table-reference
CREATE TABLE dict_reference (
  code VARCHAR(100) NOT NULL PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  system BOOLEAN NOT NULL,
  rubric_code VARCHAR(100) REFERENCES dict_rubric(code)
);