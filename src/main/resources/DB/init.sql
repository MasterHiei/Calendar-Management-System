-- USE DATABASE --
USE CALENDAR_DB;

-- INIT TABLE ROLE --
INSERT INTO tbl_role (
  role_name
) VALUES (1, 'Admiral');
INSERT INTO tbl_role (
  role_name
) VALUES (2, 'Commodore');
INSERT INTO tbl_role (
  role_name
) VALUES (3, 'Soldier');

-- INIT TABLE USER --
INSERT INTO tbl_user (
  user_name, mail_address, user_avatar, password, password_salt, now_action, session_id, current_login_datetime, delete_flag
) 
VALUES (
  'hiei', NULL, 'img/avatar/default/default-avatar.png',
  'CAAC75EF1FA696255F61ADDF40D7D11D246ED5FEDFF2636FBEE45E0FE56A1340', 'b', NULL, NULL, NULL, 0
);

-- INIT TABLE EVENT --
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color, event_owner_id
)
VALUES (
  '测试标题1', '测试地点1', '测试内容1', '2018-05-23 ', '00:00', '2018-06-08', '13:00', 'ff0000', 10000001
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color, event_owner_id
)
VALUES (
  '测试标题2', '测试地点2', '测试内容2', '2018-05-12 ', '10:00', null, '12:00', '1e90ff', 10000001
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color, event_owner_id
)
VALUES (
  '测试标题22', '测试地点22', '测试内容22', '2018-05-12 ', '8:45', null, '10:00', 'ff0000', 10000001
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color, event_owner_id
)
VALUES (
  '测试标题23', '测试地点23', '测试内容23', '2018-05-12 ', '10:45', null, '12:00', '1e90ff', 10000001
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color, event_owner_id
)
VALUES (
  '测试标题24', '测试地点24', '测试内容24', '2018-05-12 ', '14:00', null, '15:00', '32cd32', 10000001
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color, event_owner_id
)
VALUES (
  '测试标题25', '测试地点25', '测试内容25', '2018-05-12 ', '16:15', null, '18:00', 'ff0000', 10000001
);
INSERT INTO tbl_event (
  event_title, event_location, event_content, event_start_date, event_start_time, event_end_date, event_end_time, event_color, event_owner_id
)
VALUES (
  '测试标题3', '测试地点3', '测试内容3', '2018-06-02 ', '14:45', null, '10:00', '32cd32', 10000001
);

-- INIT TABLE USER_EVENT --
INSERT INTO tbl_user_event (
  user_id, event_id
)
VALUES (
  10000001, 10000001
);
INSERT INTO tbl_user_event (
  user_id, event_id
)
VALUES (
  10000001, 10000002
);
INSERT INTO tbl_user_event (
  user_id, event_id
)
VALUES (
  10000001, 10000003
);
INSERT INTO tbl_user_event (
  user_id, event_id
)
VALUES (
  10000001, 10000004
);
INSERT INTO tbl_user_event (
  user_id, event_id
)
VALUES (
  10000001, 10000005
);
INSERT INTO tbl_user_event (
  user_id, event_id
)
VALUES (
  10000001, 10000006
);
INSERT INTO tbl_user_event (
  user_id, event_id
)
VALUES (
  10000001, 10000007
);