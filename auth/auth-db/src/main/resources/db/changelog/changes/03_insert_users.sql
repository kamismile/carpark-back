--liquibase formatted sql
--changeset rmorenko:create_users
DELETE FROM user_info;


-- администратор
INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator1', 'administrator1', 'administrator', '1', '1');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('management1', 'management1', 'management', '1', '2');

----
----- Пользователи для точки проката 2

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator2', 'administrator2', 'administrator', '2', '3');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager2', 'rental_manager2', 'rental_manager', '2', '4');

---
---Пользователи для точки проката 3

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator3', 'administrator3', 'administrator', '3', '5');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager3', 'rental_manager3', 'rental_manager', '3', '6');
---
---Пользователи для точки обслуживания 4

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator4', 'administrator4', 'administrator', '4', '7');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('service_manager4', 'service_manager4', 'service_manager', '4', '8');
---
---Пользователи для точки проката 5

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator5', 'administrator5', 'administrator', '5', '9');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager5', 'rental_manager5', 'rental_manager', '5', '10');
---
---Пользователи для точки проката 6

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator6', 'administrator6', 'administrator', '6', '11');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager6', 'rental_manager6', 'rental_manager', '6', '12');
---
---Пользователи для точки проката 7

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('administrator7', 'administrator7', 'administrator', '7', '13');

INSERT INTO user_info(
  login, password_hash, role, location_id, id)
VALUES ('rental_manager7', 'rental_manager7', 'rental_manager', '7', '14');

ALTER SEQUENCE user_info_id_seq RESTART WITH 500;


--changeset rmorenko:2018-06-05
ALTER TABLE user_info
ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

