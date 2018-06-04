--liquibase formatted sql
--changeset dbegun:insert-table-transition
INSERT INTO transition(
  from_state, to_state, event)
VALUES ('READY', 'IN_USE', 'RENT');

INSERT INTO transition(
  from_state, to_state, event)
VALUES ('IN_USE', 'READY', 'RETURN');

INSERT INTO transition(
  from_state, to_state, event)
VALUES ('READY', 'IN_SERVICE', 'SERVICE');

INSERT INTO transition(
  from_state, to_state, event)
VALUES ('IN_SERVICE', 'READY', 'RETURN');

INSERT INTO transition(
  from_state, to_state, event)
VALUES ('IN_SERVICE', 'DECOMMISSIONED', 'RETIRE');