package org.tmhi.model.entity;

import lombok.Data;

@Data
public class UserEntity extends BaseEntity {
    /** 用户ID*/
    private Integer userId;
    /** 用户名 */
    private String userName;
    /** 用户邮箱 */
    private String mailAddress;
    /** 用户头像 */
    private String userAvatar;
    /** 用户密码 */
    private String password;
    /** 密码盐值 */
    private String passwordSalt;
    /** 当前状态 */
    private String nowAction;
}