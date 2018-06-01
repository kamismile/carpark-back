--liquibase formatted sql
--changeset rmorenko:create_users
INSERT INTO user_info(
            login, password_hash, role, location_id)
    VALUES ('management', 'management', 'management', '1');

INSERT INTO user_info(
            login, password_hash, role, location_id)
    VALUES ('rental_manager', 'rental_manager', 'rental_manager', '1');

INSERT INTO user_info(
            login, password_hash, role, location_id)
    VALUES ('service_manager', 'service_manager', 'service_manager', '1');

INSERT INTO user_info(
            login, password_hash, role, location_id)
    VALUES ('administrator', 'administrator', 'administrator', '1');