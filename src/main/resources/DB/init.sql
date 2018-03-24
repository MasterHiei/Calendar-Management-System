-- USE DATABASE --
USE CALENDAR_DB;

-- INIT TABLE ROLE --
INSERT INTO tbl_role (
  role_code, role_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (1, 'Admin', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role (
  role_code, role_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 'Leader', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role (
  role_code, role_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (3, 'Member', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);

-- INIT TABLE AUTH --
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (1, 'Insert', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 'Delete', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (3, 'Update', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (4, 'Query', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);

-- INIT TABLE ROLE_AUTH --
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (1, 1, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (1, 2, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (1, 3, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (1, 4, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);


INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 1, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 2, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 3, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 4, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);


INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (3, 1, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (3, 2, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (3, 3, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_role_auth (
  role_code, auth_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (3, 4, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);


-- INIT TABLE USER --
INSERT INTO tbl_user (
  user_name, mail_address, user_avatar, password, password_salt, now_action,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) 
VALUES (
  'Hiei', 'Hiei@test.com', 'img/avatar/default/default-avatar.png',
  'f55ff16f66f43360266b95db6f8fec01d76031054306ae4a4b380598f6cfd114', '1', NULL,
  0, 10000001, current_timestamp, 10000001, current_timestamp, 1
);

-- INIT TABLE USER_ROLE --
INSERT INTO tbl_user_role (
  user_id, role_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (10000001, 1, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_user_role (
  user_id, role_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (10000001, 2, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_user_role (
  user_id, role_code,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (10000001, 3, 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);