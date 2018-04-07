-- USE DATABASE --
USE CALENDAR_DB;

-- INIT TABLE AUTH --
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (1, 'Hiei', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 'Member', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
/*INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (2, 'Admiral', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (3, 'Captain', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);
INSERT INTO tbl_auth (
  auth_code, auth_name,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) VALUES (4, 'Soldier', 0, 10000001, current_timestamp, 10000001, current_timestamp, 1);*/

-- INIT TABLE USER --
INSERT INTO tbl_user (
  auth_code, user_name, mail_address, user_avatar, password, password_salt, now_action, session_id, current_login_datetime,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) 
VALUES (
  1, 'hiei', NULL, 'img/avatar/default/default-avatar.png',
  'CAAC75EF1FA696255F61ADDF40D7D11D246ED5FEDFF2636FBEE45E0FE56A1340', 'b', NULL, NULL, NULL,
  0, 10000001, current_timestamp, 10000001, current_timestamp, 1
);