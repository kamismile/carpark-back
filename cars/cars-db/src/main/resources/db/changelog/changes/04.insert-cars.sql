--liquibase formatted sql
--changeset dbegun:insert-cars-anew
--
DELETE FROM public.car;
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('1','2017', 'lexus', '10156.22', current_date - 45, current_date + 48,
            'ready', current_date -1 , NULL, NULL,
            'READY', '2', '2', 'X716TA77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ( '2','2015', 'peugeot', '12456.37', current_date - 60, current_date + 10,
             'ready', current_date - 30, 'in_use', current_date + 1,
             'READY', '3', '2', 'T478HM199');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('3','2018', 'lada', '100.00', current_date - 5, current_date + 10,
            'in_service', current_date - 5, 'ready', current_date + 1,
            'IN_SERVICE', '2', '4', 'A777MP777');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('4','2015', 'renault', '56860.00', current_date - 29, current_date + 25,
            'in_use', current_date - 4, 'ready', current_date + 14,
            'IN_USE', '2', '2', 'M462PT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('5','2013', 'bmw', '25156.42', current_date - 20, current_date + 1,
            'ready', current_date -4 , 'in_service', current_date + 1,
            'READY', '3', '3', 'M315MB99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('6','2013', 'mercedes', '232456.37', current_date - 16, current_date + 70,
            'ready', current_date - 10, 'in_use', current_date + 15,
            'READY', '2', '3', 'T478HM199');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('7','2015', 'mazda', '129574.00', current_date - 30, current_date + 18,
            'in_service', current_date - 1, 'ready', current_date + 1,
            'IN_SERVICE', '3', '4', 'E349CT79');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('8','2015', 'renault', '17562.13', current_date - 11, current_date + 45,
            'in_use', current_date - 6, 'ready', current_date + 13,
            'IN_USE', '3', '3', 'T463AB77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('9','2012', 'kia', '2347562.53', current_date - 50, current_date - 30,
            'decommissioned', current_date - 6, NULL, NULL,
            'DECOMMISSIONED', '3', '3', 'H597TB99');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('10', '2011', 'volkswagen', '3347642.91', current_date - 70, current_date - 40,
              'decommissioned', current_date - 30, NULL, NULL,
              'DECOMMISSIONED', '3', '3', 'M349KK199');
-------------------------------------------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('11', '2014', 'renault', '52356.22', current_date - 90, current_date + 1,
              'ready', current_date - 10 , NULL, NULL,
              'READY', '2', '2', 'E442MK177');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('12',  '2015', 'lexus', '15256.76', current_date - 10, current_date + 70,
               'ready', current_date - 6, 'in_use', current_date + 40,
               'READY', '3', '2', 'K667MK77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('13', '2014', 'volkswagen', '250337.90', current_date - 40, current_date + 50,
              'in_service', current_date - 1, 'ready', current_date + 2,
              'IN_SERVICE', '2', '4', 'T669KM77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('14', '2015', 'lada', '46860.00', current_date - 30, current_date + 40,
              'in_use', current_date - 10, 'ready', current_date + 35,
              'IN_USE', '2', '2', 'M734PT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('15', '2013', 'bmw', '31156.42', current_date - 22, current_date + 19,
              'ready', current_date -3 , 'in_service', current_date + 19 ,
              'READY', '3', '2', 'E106EK97');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('16', '2013', 'mazda', '232456.37', current_date - 11, current_date + 18,
              'ready', current_date - 9, 'in_use', current_date + 15,
              'READY', '2', '2', 'T440EH99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('17', '2016', 'renault', '519574.00', current_date - 22, current_date + 26,
              'in_service', current_date - 2, 'ready', current_date + 2,
              'IN_SERVICE', '3', '2', 'T226MH99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('18', '2014', 'mercedes', '217562.13', current_date - 9, current_date + 37,
              'in_use', current_date - 3, 'ready', current_date + 13,
              'IN_USE', '3', '2', 'T463AB77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('19', '2011', 'ford', '2322562.57', current_date - 90, current_date - 67,
              'decommissioned', current_date - 6, NULL, NULL,
              'DECOMMISSIONED', '3', '2', 'A467OB77');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('20', '2011', 'ford', '3107642.18', current_date - 99, current_date - 37,
              'decommissioned', current_date - 47, NULL, NULL,
              'DECOMMISSIONED', '3', '2', 'A675OB77');
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('21', '2017', 'renault', '22156.49', current_date - 41, current_date + 4,
              'ready', current_date -4 , NULL, NULL,
              'READY', '2', '3', 'O465MA99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('22',  '2015', 'mazda', '12447.99', current_date - 55, current_date + 15,
               'ready', current_date - 29, 'in_use', current_date + 3,
               'READY', '3', '3', 'T669HM99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('23', '2018', 'lexus', '691.13', current_date - 10, current_date + 11,
              'in_service', current_date - 5, 'ready', current_date + 1,
              'IN_SERVICE', '3', '4', 'O765BA99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('24', '2013', 'mazda', '156860.60', current_date - 31, current_date + 26,
              'in_use', current_date - 3, 'ready', current_date + 19,
              'IN_USE', '3', '3', 'O467AC77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('25', '2012', 'volkswagen', '127156.49', current_date - 22, current_date + 11,
              'ready', current_date -2 , 'in_service', current_date + 11,
              'READY', '3', '3', 'M315MB99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('26', '2012', 'renault', '322456.37', current_date - 19, current_date + 22,
              'ready', current_date - 10, 'in_use', current_date + 15,
              'READY', '3', '2', 'C946AO99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('27', '2014', 'peugeot', '229664.19', current_date - 26, current_date + 24,
              'in_service', current_date - 2, 'ready', current_date + 3,
              'IN_SERVICE', '3', '4', 'A355CT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('28', '2015', 'renault', '22659.49', current_date - 13, current_date + 46,
              'in_use', current_date - 2, 'ready', current_date + 15,
              'IN_USE', '3', '3', 'A462CM77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('29', '2013', 'volkswagen', '256493.59', current_date - 40, current_date - 20,
              'decommissioned', current_date - 9, NULL, NULL,
              'DECOMMISSIONED', '3', '3', 'M659CO99');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('30', '2010', 'lexus', '3476292.71', current_date - 100, current_date - 70,
              'decommissioned', current_date - 60, NULL, NULL,
              'DECOMMISSIONED', '3', '3', 'C623OH99');
-------------------------------------------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('31', '2014', 'bmw', '53491.29', current_date - 30, current_date + 20,
              'ready', current_date - 16 , NULL, NULL,
              'READY', '5', '5', 'A539OC77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('32',  '2013', 'volkswagen', '234659.43', current_date - 11, current_date + 20,
               'ready', current_date - 6, 'in_use', current_date + 10,
               'READY', '5', '6', 'C234CA77');--comming here

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('33', '2017', 'mazda', '10594.21', current_date - 10, current_date + 30,
              'in_service', current_date - 1, 'ready', current_date + 6,
              'IN_SERVICE', '2', '4', 'C234OA77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('34', '2015', 'bmw', '15468.02', current_date - 31, current_date + 30,
              'in_use', current_date - 12, 'ready', current_date + 25,
              'IN_USE', '5', '5', 'C236PO99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('35', '2014', 'ford', '31536.79', current_date - 24, current_date + 29,
              'ready', current_date -4 , 'in_service', current_date + 29,
              'READY', '3', '5', 'C112EM99');--comming here

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('36', '2015', 'lexus', '243296.29', current_date - 21, current_date + 28,
              'ready', current_date - 10, 'in_use', current_date + 15,
              'READY', '5', '5', 'C234EO99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('37', '2015', 'peugeot', '54624.04', current_date - 24, current_date + 22,
              'in_service', current_date - 2, 'ready', current_date + 2,
              'IN_SERVICE', '5', '4', 'T249MH99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('38', '2016', 'kia', '24292.15', current_date - 4, current_date + 50,
              'in_use', current_date - 3, 'ready', current_date + 26,
              'IN_USE', '3', '5', 'T329CO77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('39', '2011', 'volkswagen', '232290.46', current_date - 140, current_date - 100,
              'decommissioned', current_date - 130, NULL, NULL,
              'DECOMMISSIONED', '3', '5', 'A294CM77');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('40', '2009', 'kia', '234692.26', current_date - 190, current_date - 140,
              'decommissioned', current_date - 47, NULL, NULL,
              'DECOMMISSIONED', '7', '5', 'C234MA77');
--------------------------------
--------------------------------
--------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('41', '2017', 'peugeot', '23465.59', current_date - 25, current_date + 28,
              'ready', current_date -2 , NULL, NULL,
              'READY', '6', '6', 'C346OM99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('42',  '2015', 'bmw', '12456.37', current_date - 60, current_date + 10,
               'ready', current_date - 30, 'in_use', current_date + 1,
               'READY', '7', '6', '459HM199');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('43', '2016', 'renault', '12346.29', current_date - 5, current_date + 10,
              'in_service', current_date - 5, 'ready', current_date + 1,
              'IN_SERVICE', '6', '4', 'A128MP99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('44', '2016', 'bmw', '12960.00', current_date - 29, current_date + 25,
              'in_use', current_date - 4, 'ready', current_date + 14,
              'IN_USE', '6', '6', 'C462PT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('45', '2013', 'mercedes', '25156.42', current_date - 20, current_date + 17,
              'ready', current_date -4 , 'in_service', current_date + 17,
              'READY', '6', '6', '159MB99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('46', '2013', 'mercedes', '232456.37', current_date - 16, current_date + 70,
              'ready', current_date - 10, 'in_use', current_date + 10,
              'READY', '5', '6', 'T198HM199');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('47', '2017', 'ford', '29574.19', current_date - 20, current_date + 19,
              'in_service', current_date - 1, 'ready', current_date + 1,
              'IN_SERVICE', '6', '4', 'C289CT79');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('48', '2016', 'bmw', '26456.59', current_date - 11, current_date + 45,
              'in_use', current_date - 4, 'ready', current_date + 15,
              'IN_USE', '6', '6', 'T463AB77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('49', '2013', 'volkswagen', '212562.19', current_date - 60, current_date - 40,
              'decommissioned', current_date - 35, NULL, NULL,
              'DECOMMISSIONED', '6', '6', 'H236TB99');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('50', '2011', 'lexus', '347956.94', current_date - 80, current_date - 61,
              'decommissioned', current_date - 29, NULL, NULL,
              'DECOMMISSIONED', '6', '6', 'M349KK199');
-------------------------------------------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('51', '2014', 'renault', '56556.12', current_date - 90, current_date + 1,
              'ready', current_date - 10 , NULL, NULL,
              'READY', '6', '6', 'E267MK177');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('52',  '2015', 'peugeot', '26556.76', current_date - 10, current_date + 70,
               'ready', current_date - 6, 'in_use', current_date + 40,
               'READY', '7', '6', 'K956MK77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('53', '2014', 'lexus', '326033.19', current_date - 40, current_date +46,
              'in_service', current_date - 1, 'ready', current_date + 2,
              'IN_SERVICE', '6', '4', 'T298KM77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('54', '2015', 'renault', '46860.00', current_date - 30, current_date + 40,
              'in_use', current_date - 10, 'ready', current_date + 35,
              'IN_USE', '6', '6', 'M265PT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('55', '2013', 'mercedes', '31156.29', current_date - 22, current_date + 19,
              'ready', current_date -3 , 'in_service', current_date + 19,
              'READY', '7', '6', 'E329EK97');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('56', '2013', 'ford', '232457.19', current_date - 11, current_date + 18,
              'ready', current_date - 9, 'in_use', current_date + 15,
              'READY', '6', '6', 'T219EH99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('57', '2016', 'bmw', '124584.19', current_date - 22, current_date + 26,
              'in_service', current_date - 2, 'ready', current_date + 2,
              'IN_SERVICE', '6', '4', 'T226MH99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('58', '2014', 'mazda', '217562.13', current_date - 9, current_date + 37,
              'in_use', current_date - 3, 'ready', current_date + 13,
              'IN_USE', '7', '6', 'T249AB77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('59', '2011', 'kia', '123225.49', current_date - 90, current_date - 67,
              'decommissioned', current_date - 6, NULL, NULL,
              'DECOMMISSIONED', '7', '6', 'A122OB77');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('60', '2011', 'ford', '107642.29', current_date - 99, current_date - 37,
              'decommissioned', current_date - 47, NULL, NULL,
              'DECOMMISSIONED', '6', '6', 'A699OB77');
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('61', '2017', 'bmw', '22156.49', current_date - 41, current_date + 4,
              'ready', current_date -4 , NULL, NULL,
              'READY', '3', '7', 'O234MA99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ( '62', '2015', 'ford', '12447.19', current_date - 55, current_date + 15,
               'ready', current_date - 29, 'in_use', current_date + 3,
               'READY', '7', '7', 'T294HM99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('63', '2018', 'peugeot', '1649.13', current_date - 12, current_date + 15,
              'in_service', current_date - 2, 'ready', current_date + 2,
              'IN_SERVICE', '7', '4', 'A319BA99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('64', '2013', 'ford', '26560.14', current_date - 31, current_date + 26,
              'in_use', current_date - 3, 'ready', current_date + 19,
              'IN_USE', '7', '7', 'O234MH77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('65', '2012', 'lexus', '127156.49', current_date - 24, current_date + 12,
              'ready', current_date -4 , 'in_service', current_date + 12,
              'READY', '7', '7', 'OA659MB99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('66', '2016', 'bmw', '3456.37', current_date - 21, current_date + 27,
              'ready', current_date - 10, 'in_use', current_date + 25,
              'READY', '3', '7', 'O329AO99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('67', '2017', 'lada', '2264.69', current_date - 26, current_date + 24,
              'in_service', current_date - 2, 'ready', current_date + 3,
              'IN_SERVICE', '7', '4', 'C329CT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('68', '2015', 'bmw', '22659.49', current_date - 13, current_date + 46,
              'in_use', current_date - 2, 'ready', current_date + 15,
              'IN_USE', '7', '7', 'O329CM77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('69', '2013', 'lexus', '256493.59', current_date - 40, current_date - 20,
              'decommissioned', current_date - 9, NULL, NULL,
              'DECOMMISSIONED', '7', '7', 'M495CO99');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('70', '2010', 'peugeot', '3476292.71', current_date - 100, current_date - 70,
              'decommissioned', current_date - 60, NULL, NULL,
              'DECOMMISSIONED', '7', '7', 'C234OH99');
-------------------------------------------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('71', '2014', 'mercedes', '53491.29', current_date - 30, current_date + 20,
              'ready', current_date - 16 , NULL, NULL,
              'READY', '7', '7', 'A539OC77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ( '72', '2013', 'volkswagen', '234659.43', current_date - 11, current_date + 20,
               'ready', current_date - 6, 'in_use', current_date + 10,
               'READY', '6', '7', 'C167CA77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('73', '2017', 'ford', '26594.21', current_date - 15, current_date + 20,
              'in_service', current_date - 2, 'ready', current_date + 6,
              'IN_SERVICE', '7', '4', 'C249MH77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('74', '2015', 'mercedes', '15468.02', current_date - 31, current_date + 30,
              'in_use', current_date - 12, 'ready', current_date + 25,
              'IN_USE', '7', '7', 'C449PO99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('75', '2014', 'kia', '31536.79', current_date - 24, current_date + 9,
              'ready', current_date -4 , 'in_service', current_date + 9,
              'READY', '3', '7', 'C446EM99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('76', '2015', 'peugeot', '243296.29', current_date - 21, current_date + 28,
              'ready', current_date - 10, 'in_use', current_date + 15,
              'READY', '7', '7', 'C436EO99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('77', '2015', 'lada', '54494.49', current_date - 24, current_date + 22,
              'in_service', current_date - 2, 'ready', current_date + 2,
              'IN_SERVICE', '7', '4', 'T442MH99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('78', '2016', 'volkswagen', '24292.15', current_date - 4, current_date + 50,
              'in_use', current_date - 3, 'ready', current_date + 26,
              'IN_USE', '3', '7', 'T442CO77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('79', '2011', 'lexus', '232290.46', current_date - 140, current_date - 100,
              'decommissioned', current_date - 130, NULL, NULL,
              'DECOMMISSIONED', '2', '7', 'A421CM77');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('80', '2009', 'volkswagen', '234692.26', current_date - 190, current_date - 140,
              'decommissioned', current_date - 47, NULL, NULL,
              'DECOMMISSIONED', '7', '7', 'C845MA77');
------------------------
------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('81','2017', 'peugeot', '20158.45', current_date - 25, current_date + 28,
             'ready', current_date -2 , NULL, NULL,
             'READY', '2', '7', 'X254TA77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ( '82','2015', 'lada', '56294.47', current_date - 30, current_date + 30,
              'ready', current_date - 20, 'in_use', current_date + 21,
              'READY', '3', '7', 'C245HM99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('83','2018', 'bmw', '1564.17', current_date - 19, current_date + 25,
             'in_service', current_date - 2, 'ready', current_date + 2,
             'IN_SERVICE', '7', '4', 'O435MP97');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('84','2015', 'bmw', '51960.23', current_date - 24, current_date + 22,
             'in_use', current_date - 4, 'ready', current_date + 14,
             'IN_USE', '7', '7', 'M226PT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('85','2013', 'mercedes', '19563.23', current_date - 22, current_date + 19,
             'ready', current_date -4 , 'in_service', current_date + 19,
             'READY', '3', '7', 'M226MB99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('86','2013', 'mazda', '232456.37', current_date - 16, current_date + 70,
             'ready', current_date - 10, 'in_use', current_date + 15,
             'READY', '7', '3', 'T118HM199');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('87','2017', 'ford', '2349.00', current_date - 32, current_date + 31,
             'in_service', current_date - 1, 'ready', current_date + 1,
             'IN_SERVICE', '6', '4', 'A245CM77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('88','2015', 'bmw', '17562.13', current_date - 11, current_date + 45,
             'in_use', current_date - 6, 'ready', current_date + 13,
             'IN_USE', '7', '7', 'A349OH77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('89','2012', 'volkswagen', '2347562.53', current_date - 52, current_date - 31,
             'decommissioned', current_date - 6, NULL, NULL,
             'DECOMMISSIONED', '7', '7', 'A847TB99');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('90', '2011', 'lexus', '23659.92', current_date - 70, current_date - 40,
              'decommissioned', current_date - 32, NULL, NULL,
              'DECOMMISSIONED', '3', '3', 'M265OA199');
-------------------------------------------------------------------
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('91', '2014', 'bmw', '23465.29', current_date - 92, current_date + 3,
              'ready', current_date - 10 , NULL, NULL,
              'READY', '7', '6', 'O571MK77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('92',  '2015', 'peugeot', '15256.76', current_date - 12, current_date + 30,
               'ready', current_date - 6, 'in_use', current_date + 20,
               'READY', '6', '3', 'O471MK77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('93', '2014', 'peugeot', '26459.92', current_date - 42, current_date + 50,
              'in_service', current_date - 1, 'ready', current_date + 2,
              'IN_SERVICE', '6', '4', 'T235ACM77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('94', '2015', 'renault', '46264.01', current_date - 30, current_date + 40,
              'in_use', current_date - 12, 'ready', current_date + 31,
              'IN_USE', '2', '2', 'M234PT99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('95', '2013', 'mercedes', '31156.43', current_date - 22, current_date + 19,
              'ready', current_date -3 , 'in_service', current_date + 19,
              'READY', '3', '2', 'E269EK97');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('96', '2013', 'ford', '232456.37', current_date - 11, current_date + 18,
              'ready', current_date - 9, 'in_use', current_date + 15,
              'READY', '2', '2', 'T290EH99');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('97', '2016', 'bmw', '512571.19', current_date - 21, current_date + 22,
              'in_service', current_date - 3, 'ready', current_date + 1,
              'IN_SERVICE', '7', '4', 'T228MH77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('98', '2014', 'mazda', '217562.29', current_date - 9, current_date + 37,
              'in_use', current_date - 3, 'ready', current_date + 12,
              'IN_USE', '3', '2', 'T234AB77');

--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('99', '2011', 'kia', '22262.19', current_date - 90, current_date - 67,
              'decommissioned', current_date - 6, NULL, NULL,
              'DECOMMISSIONED', '3', '2', 'A225OB77');
--
INSERT INTO public.car(
  id, year, mark, mileage, prev_maintenance_date, next_maintenance_date,
  current_status, current_status_date, next_status, next_status_date,
  state, location_id, current_location_id, reg_number)
VALUES ('100', '2011', 'kia', '31042.12', current_date - 92, current_date - 32,
               'decommissioned', current_date - 42, NULL, NULL,
               'DECOMMISSIONED', '7', '7', 'A159OB77');

ALTER SEQUENCE car_id_seq RESTART WITH 500;