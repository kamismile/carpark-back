--liquibase formatted sql
--changeset mirzoevnik:insert-dicts-rubric
INSERT INTO dict_rubric(code, title, system)
  VALUES ('car_status', 'Статус автомобиля', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('employee_role', 'Роль сотрудника', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('location_type', 'Тип локации', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('position', 'Должность', TRUE);
INSERT INTO dict_rubric(code, title, system)
  VALUES ('car_mark', 'Марка автомобиля', TRUE);

--changeset mirzoevnik:insert-dicts-reference-location-type
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('rental_station', 'Пункт проката', TRUE, 'location_type');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('service_station', 'Пункт обслуживания', TRUE, 'location_type');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('head_office', 'Головной офис', TRUE, 'location_type');

--changeset mirzoevnik:insert-dicts-reference-car-status
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('ready', 'В наличии', TRUE, 'car_status');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('in_use', 'В прокате', TRUE, 'car_status');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('in_service', 'На обслуживании', TRUE, 'car_status');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('decommissioned', 'Выбыл из автопарка', TRUE, 'car_status');

--changeset mirzoevnik:insert-dicts-reference-employee-role
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('management', 'Руководство', TRUE, 'employee_role');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('rental_manager', 'Менеджер по прокату', TRUE, 'employee_role');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('service_manager', 'Менеджер по обслуживанию', TRUE, 'employee_role');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('administrator', 'Администратор', TRUE, 'employee_role');

--changeset mirzoevnik:insert-dicts-reference-car-mark
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('lexus', 'Lexus', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('bmw', 'BMW', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('audi', 'Audi', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('mercedes', 'Mercedes-Benz', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('ford', 'Ford', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('kia', 'KIA', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('mazda', 'Mazda', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('volkswagen', 'Volkswagen', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('lada', 'LADA', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('renault', 'Renault', TRUE, 'car_mark');
INSERT INTO dict_reference(code, title, system, rubric_code)
  VALUES ('peugeot', 'Peugeot', TRUE, 'car_mark');