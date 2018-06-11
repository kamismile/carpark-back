--liquibase formatted sql
--changeset rmorenko:create-table-access
CREATE TABLE ACCESS_EXPRESSIONS (
  id SERIAL PRIMARY KEY,
  operation_name VARCHAR(300) NOT NULL,
  expression VARCHAR(2000)
);

--liquibase formatted sql
--changeset rmorenko:insert_data
INSERT into ACCESS_EXPRESSIONS (operation_name, expression)
VALUES ('getCars_filter',
'( #userInfo.role == ''rental_manager'' && #userInfo.localityId == #target.currentLocationId )||'||
'(#userInfo.role == ''service_manager'' && ( #target.currentStatus = ''in_service'' ||  #target.nextStatus == ''in_service'' )) ||'||
'(#userInfo.role == ''management'') || (#userInfo.role == ''administrator'')'
);

--changeset rmorenko:insert_data2
INSERT into ACCESS_EXPRESSIONS (operation_name, expression)
VALUES ('getReferencesByRubric_filter','#userInfo.role != ''test''');

INSERT into ACCESS_EXPRESSIONS (operation_name, expression)
VALUES ('changeCarState','#userInfo.role == ''management''');

INSERT into ACCESS_EXPRESSIONS (operation_name, expression)
VALUES ('createCar','#userInfo.role == ''administrator''');

INSERT into ACCESS_EXPRESSIONS (operation_name, expression)
VALUES ('deleteCar','#userInfo.role == ''administrator''');

INSERT into ACCESS_EXPRESSIONS (operation_name, expression)
VALUES ('updateCar','#userInfo.role == ''administrator''');

--changeset rmorenko:2018-05-31
UPDATE  ACCESS_EXPRESSIONS  SET expression =
'#userInfo.role == ''management''|| #userInfo.role == ''administrator'' || ( #userInfo.role == ''rental_manager'' &&  ( #stringEvent == ''READY'' || #stringEvent == ''IN_USE'' ) )'||
'|| ( #userInfo.role == ''service_manager'' &&  #stringEvent == ''IN_SERVICE'')'
WHERE operation_name = 'changeCarState';

--changeset dbegun:2018-06-07
INSERT into ACCESS_EXPRESSIONS (operation_name, expression)
VALUES ('createPreorder','#userInfo.role == ''administrator'' || #userInfo.role == ''rental_manager''');

--changeset rmorenko:2018-06-08
UPDATE  ACCESS_EXPRESSIONS  SET expression =
'( #userInfo.role == ''rental_manager'' && #userInfo.locationId == #target.currentLocationId )||'||
'(#userInfo.role == ''service_manager'' && ( #target.currentStatus = ''in_service'' ||  #target.nextStatus == ''in_service'' )) ||'||
'(#userInfo.role == ''management'') || (#userInfo.role == ''administrator'')'
WHERE operation_name = 'getCars_filter';

--changeset rmorenko:2018-06-08-2
UPDATE  ACCESS_EXPRESSIONS  SET expression =
'( #userInfo.role == ''rental_manager'' && #userInfo.locationId == #target.currentLocationId )||'||
'(#userInfo.role == ''service_manager'' && ( #target.currentStatus == ''in_service'' ||  #target.nextStatus == ''in_service'' )) ||'||
'(#userInfo.role == ''management'') || (#userInfo.role == ''administrator'')'
WHERE operation_name = 'getCars_filter';

--changeset rmorenko:2018-06-11
UPDATE  ACCESS_EXPRESSIONS  SET expression =
'#userInfo.role == ''management''|| #userInfo.role == ''administrator'' || ( #userInfo.role == ''rental_manager'' &&  ( #stringEvent == ''READY'' || #stringEvent == ''RETURN'' ) )'||
'|| ( #userInfo.role == ''service_manager'' &&  #stringEvent == ''SERVICE'')'
WHERE operation_name = 'changeCarState';
