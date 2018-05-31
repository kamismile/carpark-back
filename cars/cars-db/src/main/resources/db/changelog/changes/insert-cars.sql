--liquibase formatted sql
--changeset rmorenko:insrert-cars
INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ( '2017', 'lexus', '10156.22', current_date - 45, current_date + 48,
            'ready', current_date -1 , NULL, NULL,
            'READY', '2', '2', 'X716TA77');
INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ('2015', 'peugeot', '12456.37', current_date - 60, current_date + 10,
            'ready', current_date - 30, 'in_use', current_date + 1,
            'READY', '3', '2', 'T478HM199');

INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ('2018', 'lada', '100.00', current_date - 5, current_date + 10,
            'in_service', current_date - 5, 'ready', current_date + 1,
            'IN_SERVICE', '2', '4', 'A777MP777');

INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ('2015', 'renault', '56860.00', current_date - 29, current_date + 25,
            'in_use', current_date - 4, 'ready', current_date + 14,
            'IN_USE', '2', '2', 'M462PT99');

INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ('2013', 'bmw', '25156.42', current_date - 20, current_date + 17,
            'ready', current_date -4 , NULL, NULL,
            'READY', '3', '3', 'M315MB99');

INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ('2013', 'mercedes', '232456.37', current_date - 16, current_date + 70,
            'ready', current_date - 10, 'in_use', current_date + 15,
            'READY', '2', '3', 'T478HM199');

INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ('2015', 'mazda', '129574.00', current_date - 30, current_date + 18,
            'in_service', current_date - 1, 'ready', current_date + 1,
            'IN_SERVICE', '3', '4', 'E349CT79');

INSERT INTO car(
            year, mark, mileage, prev_maintenance_date, next_maintenance_date,
            current_status, current_status_date, next_status, next_status_date,
            state, location_id, current_location_id, reg_number)
    VALUES ('2015', 'renault', '17562.13', current_date - 11, current_date + 45,
            'in_use', current_date - 6, 'ready', current_date + 13,
            'IN_USE', '3', '3', 'T463AB77');
select * from car;

commit;