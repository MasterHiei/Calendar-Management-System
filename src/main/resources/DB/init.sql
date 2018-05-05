-- USE DATABASE --
USE CALENDAR_DB;

-- INIT TABLE ROLE --
INSERT INTO tbl_role (
  role_code, role_name
) VALUES (1, 'Admiral');
INSERT INTO tbl_role (
  role_code, role_name
) VALUES (2, 'Commodore');
INSERT INTO tbl_role (
  role_code, role_name
) VALUES (3, 'Soldier');

-- INIT TABLE USER --
INSERT INTO tbl_user (
  user_name, mail_address, user_avatar, password, password_salt, now_action, session_id, current_login_datetime,
  delete_flag, create_user_id, create_datetime,
  update_user_id, update_datetime, version
) 
VALUES (
  'hiei', NULL, 'img/avatar/default/default-avatar.png',
  'CAAC75EF1FA696255F61ADDF40D7D11D246ED5FEDFF2636FBEE45E0FE56A1340', 'b', NULL, NULL, NULL,
  0, 10000001, current_timestamp, 10000001, current_timestamp, 1
);

-- INIT TABLE EVENT --
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color
)
VALUES (
  '测试标题1', '测试地点1', '测试内容1', '2018-05-23 ', '00:00', '2018-06-08', '13:00', 'ff0000'
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color
)
VALUES (
  '测试标题2', '测试地点2', '测试内容2', '2018-05-12 ', '10:00', null, '12:00', '1e90ff'
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color
)
VALUES (
  '测试标题22', '测试地点22', '测试内容22', '2018-05-12 ', '8:45', null, '10:00', 'ff0000'
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color
)
VALUES (
  '测试标题3', '测试地点3', '测试内容3', '2018-06-02 ', '14:45', null, '10:00', '32cd32'
);

-- INIT TABLE USER_EVENT --
INSERT INTO tbl_user_event (
  user_id, role_code, event_id
)
VALUES (
  10000001, 1, 10000001
);
INSERT INTO tbl_user_event (
  user_id, role_code, event_id
)
VALUES (
  10000001, 1, 10000002
);
INSERT INTO tbl_user_event (
  user_id, role_code, event_id
)
VALUES (
  10000001, 1, 10000003
);
INSERT INTO tbl_user_event (
  user_id, role_code, event_id
)
VALUES (
  10000001, 1, 10000004
);