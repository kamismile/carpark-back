--liquibase formatted sql

--changeset dbegun:insert-table-preorder
INSERT INTO public.preorder (
  client_name, client_surname, client_patronymic, phone, email,
  lease_start_date, lease_end_date, start_location_id,
  end_location_id, created_by_user, car_id, preorder_type
) values (
  'Иванов', 'Иван', 'Иванович', '499 999 99 99', 'iii@mail.ru', current_date + 2, current_date + 5, '3', '2', 'administrator', '3', 'BOOKING'
);