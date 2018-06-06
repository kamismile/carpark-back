--liquibase formatted sql

--changeset dbegun:insert-table-preorder
delete from preorder;

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('1', 'Филипп', 'Горбачёв', 'Антонович', '89450462844', 'phgant@mail.ru',
             current_date + 1, current_date + 9, '2', '2',
             'rental_manager2', '2', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('2', 'Тимофей', 'Лобанов', 'Владимирович', '89480421866', 'administrator3@mail.ru',
             current_date + 1, current_date + 16, '3', '3',
             'administrator3', '5', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('3', 'Татьяна', 'Ситникова', 'Кирилловна', '89590421866', 'tckkill@mail.ru',
             current_date + 15, current_date + 20, '3', '3',
             'rental_manager3', '6', 'BOOKING');
---

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('4', 'Святослав', 'Шубин', 'Владимирович', '89020751955', 'csshvl55@gmail.com',
             current_date + 40, current_date + 69, '2', '2',
             'rental_manager2', '12', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('5', 'Василий', 'Филиппов', 'Николаевич', '89480421866', 'administrator2@mail.ru',
             current_date + 19, current_date + 23, '2', '2',
             'administrator2', '15', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('6', 'Егор', 'Антонов', 'Тимурович', '89050467211', 'easports@game.ru',
             current_date + 15, current_date + 17, '2', '2',
             'rental_manager2', '16', 'BOOKING');
---

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('7', 'Людмила', 'Мишина', 'Эдуардовна', '89590427866', 'lmedu66@mail.ru',
             current_date + 3, current_date + 14, '3', '3',
             'rental_manager3', '22', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('8', 'Тимофей', 'Лобанов', 'Владимирович', '89480421866', 'administrator2@mail.ru',
             current_date + 19, current_date + 23, '3', '3',
             'administrator3', '25', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('9', 'Яков', 'Фомин', 'Александрович', '89465947753', 'jafoall@mail.ru',
             current_date + 15, current_date + 17, '2', '2',
             'rental_manager2', '26', 'BOOKING');
---
INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('10', 'Фёдор', 'Некрасов', 'Гавриилович', '89495769822', 'fngavgav@mail.ru',
              current_date + 10, current_date + 18, '6', '6',
              'rental_manager6', '32', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('11', 'Марина', 'Стрелкова', 'Станиславовна', '84950487753', 'administrator5@mail.ru',
              current_date + 29, current_date + 32, '5', '5',
              'administrator5', '35', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('12', 'Ольга', 'Юдина', 'Валентиновна', '89564584522', 'oljuval@mail.ru',
              current_date + 15, current_date + 17, '5', '5',
              'rental_manager5', '36', 'BOOKING');
---
INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('13', 'Кристина', 'Федотова', 'Ивановна', '89594581522', 'krrfiva522@mail.ru',
              current_date + 1, current_date + 9, '6', '6',
              'rental_manager6', '42', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('14', 'Артём', 'Лаврентьев', 'Эдуардович', '89504584522', 'administrator6@mail.ru',
              current_date + 29, current_date + 32, '6', '6',
              'administrator6', '45', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('15', 'Борис', 'Мясников', 'Васильевич', '89564851144', 'boryameeeeeet@mail.ru',
              current_date + 10, current_date + 29, '6', '6',
              'rental_manager6', '46', 'BOOKING');
---
INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('16', 'Фёдор', 'Сысоев', 'Васильевич', '89594581133', 'vasiafedia@mail.ru',
              current_date + 40, current_date + 69, '6', '6',
              'rental_manager6', '52', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('17', 'Артём', 'Лаврентьев', 'Эдуардович', '89504584522', 'administrator6@mail.ru',
              current_date + 19, current_date + 32, '6', '6',
              'administrator6', '55', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('18', 'Антон', 'Яковлев', 'Богданович', '89564564844', 'anjzbog@gmail.com',
              current_date + 15, current_date + 17, '6', '6',
              'rental_manager6', '56', 'BOOKING');
---
INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('19', 'Алиса', 'Овчинникова', 'Денисовна', '89594556420', 'aliceinachanse@mail.ru',
              current_date + 3, current_date + 14, '7', '7',
              'rental_manager7', '62', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('20', 'Гавриил', 'Соболев', 'Станиславович', '89505301592', 'administrator7@mail.ru',
              current_date + 12, current_date + 15, '7', '7',
              'administrator7', '65', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('21', 'Татьяна', 'Суханова', 'Тимофеевна', '89564560488', 'tati60488@gmail.com',
              current_date + 25, current_date + 26, '7', '7',
              'rental_manager7', '66', 'BOOKING');
---
INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('22', 'Эмма', 'Большакова', 'Евгеньевна', '89594556420', 'beegemma@mail.ru',
              current_date + 10, current_date + 14, '7', '7',
              'rental_manager7', '72', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('23', 'Гавриил', 'Соболев', 'Станиславович', '89505301592', 'administrator7@mail.ru',
              current_date + 9, current_date + 15, '7', '7',
              'administrator7', '75', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('24', 'Гавриил', 'Веселов', 'Тимофеевич', '89560154446', 'funnyman444@gmail.com',
              current_date + 15, current_date + 26, '7', '7',
              'rental_manager7', '76', 'BOOKING');
---

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('25', 'Павел', 'Авдеев', 'Эдуардович', '89594555000', 'avepavel@mail.ru',
              current_date + 21, current_date + 29, '7', '7',
              'rental_manager7', '82', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('26', 'Гавриил', 'Соболев', 'Станиславович', '89505301592', 'administrator7@mail.ru',
              current_date + 19, current_date + 25, '7', '7',
              'administrator7', '85', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('27', 'Артём', 'Овчинников', 'Григорьевич', '89560154555', 'artgreg@gmail.com',
              current_date + 15, current_date + 26, '3', '3',
              'rental_manager3', '86', 'BOOKING');
---

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('28', 'Филипп', 'Шилов', 'Семёнович', '89595094511', 'fillsam@mail.ru',
              current_date + 20, current_date + 29, '3', '3',
              'rental_manager3', '92', 'BOOKING');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('29', 'Василий', 'Филиппов', 'Николаевич', '89480421866', 'administrator2@mail.ru',
              current_date + 19, current_date + 25, '2', '2',
              'administrator2', '95', 'SERVICE');

INSERT INTO preorder(
  id, client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id, end_location_id,
  created_by_user, car_id, preorder_type)
VALUES ('30', 'Эдуард', 'Панов', 'Петрович', '89559015744', 'addpetrivich@gmail.com',
              current_date + 15, current_date + 17, '2', '2',
              'rental_manager2', '96', 'BOOKING');

ALTER SEQUENCE preorder_id_seq RESTART WITH 500;
