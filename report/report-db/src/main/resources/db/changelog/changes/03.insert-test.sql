--changeset dbegun:2018-06-09-2
DELETE FROM public.location;
----- ЦО
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('1', '(ЦО) Лубянский пр-д, 9 строение 1, Москва, 101000', 'head_office');
-- прокат 1
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('2', '1-я Брестская ул., 2, Москва, 125047', 'rental_station');
--прокат
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('3', 'Кожевническая ул., 2, Москва, 115114', 'rental_station');
-- пункт обслуживания
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('4', '(Станция обсуживнаия) Лубянский пр-д, 9 строение 1, Москва, 101000', 'service_station');
-- прокат
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('5', 'Рогожский Вал ул., 9/2, Москва, 109544', 'rental_station');
-- прокат
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('6', 'Авиамоторная ул., 30А, Москва, 111024', 'rental_station');
-- прокат
INSERT INTO public.location(
            id, address, location_type)
    VALUES ('7', 'Крюковская ул., 23, Москва, 111020', 'rental_station');

ALTER SEQUENCE location_id_seq RESTART WITH 500;