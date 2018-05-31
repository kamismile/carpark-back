--liquibase formatted sql
--changeset rmorenko:2018-05-28
ALTER TABLE user_info
 ADD COLUMN LOCATION_ID BIGINT;
