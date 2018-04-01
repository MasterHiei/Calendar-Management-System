-- CREATE DATABASE --
DROP DATABASE IF EXISTS CALENDAR_DB;
CREATE DATABASE CALENDAR_DB DEFAULT CHARACTER SET utf8;

-- USE DATABASE --
USE CALENDAR_DB;

-- DROP TABLES --
DROP TABLE IF EXISTS tbl_role;
DROP TABLE IF EXISTS tbl_auth;
DROP TABLE IF EXISTS tbl_role_auth;
DROP TABLE IF EXISTS tbl_user;
DROP TABLE IF EXISTS tbl_user_role;

-- TABLE ROLE --
CREATE TABLE tbl_role(
  role_code INTEGER NOT NULL COMMENT '角色ID',
  role_name VARCHAR(20) NOT NULL COMMENT '角色名',
  delete_flag TINYINT NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(role_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE tbl_role ADD INDEX delete_flag ( delete_flag );

-- TABLE AUTH --
CREATE TABLE tbl_auth(
  auth_code INTEGER NOT NULL COMMENT '权限ID',
  auth_name VARCHAR(20) NOT NULL COMMENT '权限名',
  delete_flag TINYINT NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(auth_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE tbl_auth ADD INDEX delete_flag ( delete_flag );

-- TABLE ROLE_AUTH --
CREATE TABLE tbl_role_auth(
  role_code INTEGER NOT NULL COMMENT '角色ID',
  auth_code INTEGER NOT NULL COMMENT '权限ID',
  delete_flag TINYINT NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(role_code, auth_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE tbl_role_auth ADD INDEX auth_code ( auth_code );
ALTER TABLE tbl_role_auth ADD INDEX delete_flag ( delete_flag );

-- TABLE USER --
CREATE TABLE tbl_user(
  user_id INTEGER AUTO_INCREMENT COMMENT '用户ID',
  user_name VARCHAR(12) NOT NULL COMMENT '用户名',
  mail_address VARCHAR(50) COMMENT '用户邮箱',
  user_avatar VARCHAR(50) NOT NULL DEFAULT 'img/avatar/default/default-avatar.png' COMMENT '用户头像',
  password VARCHAR(64) NOT NULL COMMENT '用户密码',
  password_salt VARCHAR(64) NOT NULL COMMENT '密码盐值',
  now_action VARCHAR(128) COMMENT '当前状态',
  current_login_datetime TIMESTAMP COMMENT '最近登录时间',
  delete_flag TINYINT NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(user_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000001 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE tbl_user ADD UNIQUE INDEX user_name ( user_name );
ALTER TABLE tbl_user ADD UNIQUE INDEX mail_address ( mail_address );
ALTER TABLE tbl_user ADD INDEX delete_flag ( delete_flag );

-- TABLE USER_ROLE --
CREATE TABLE tbl_user_role(
  user_id INTEGER NOT NULL COMMENT '用户ID',
  role_code INTEGER NOT NULL COMMENT '角色ID',
  delete_flag TINYINT NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(user_id, role_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE tbl_user_role ADD INDEX role_code ( role_code );
ALTER TABLE tbl_user_role ADD INDEX delete_flag ( delete_flag );