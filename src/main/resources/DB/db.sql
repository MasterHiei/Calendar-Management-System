-- CREATE DATABASE --
DROP DATABASE IF EXISTS CALENDAR_DB;
CREATE DATABASE CALENDAR_DB DEFAULT CHARACTER SET utf8;

-- USE DATABASE --
USE CALENDAR_DB;

-- DROP TABLES --
DROP TABLE IF EXISTS tbl_auth;
DROP TABLE IF EXISTS tbl_user;

-- TABLE ROLE --
CREATE TABLE tbl_role(
  role_code INTEGER NOT NULL COMMENT '角色ID',
  role_name VARCHAR(20) NOT NULL COMMENT '角色名',
  delete_flag INTEGER NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(role_code)
) DEFAULT CHARSET=utf8;

-- TABLE AUTH --
CREATE TABLE tbl_auth(
  auth_code INTEGER NOT NULL COMMENT '权限ID',
  auth_name VARCHAR(20) NOT NULL COMMENT '权限名',
  role_code INTEGER NOT NULL COMMENT '角色ID',
  delete_flag INTEGER NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(auth_code)
) DEFAULT CHARSET=utf8;

-- TABLE USER --
CREATE TABLE tbl_user(
  user_id INTEGER AUTO_INCREMENT COMMENT '用户ID',
  role_code INTEGER NOT NULL COMMENT '角色ID',
  user_name VARCHAR(12) NOT NULL COMMENT '用户名',
  mail_address VARCHAR(50) UNIQUE COMMENT '用户邮箱',
  user_avatar VARCHAR(50) NOT NULL DEFAULT 'img/avatar/default/default-avatar.png' COMMENT '用户头像',
  password VARCHAR(64) NOT NULL COMMENT '用户密码',
  password_salt VARCHAR(26) NOT NULL COMMENT '密码盐值',
  now_action VARCHAR(128) NOT NULL COMMENT '当前状态',
  delete_flag INTEGER NOT NULL COMMENT '删除标记',
  create_user_id INTEGER NOT NULL COMMENT '创建者ID',
  create_datetime TIMESTAMP NOT NULL COMMENT '创建时间',
  update_user_id INTEGER NOT NULL COMMENT '更新者ID',
  update_datetime TIMESTAMP NOT NULL COMMENT '更新时间',
  version INTEGER NOT NULL COMMENT '版本号',
  PRIMARY KEY(user_id)
) AUTO_INCREMENT=10000001 DEFAULT CHARSET=utf8;