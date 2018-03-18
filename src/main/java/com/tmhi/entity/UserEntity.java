package com.tmhi.entity;

public class UserEntity extends BaseEntity {
    /** 用户 */
    private Integer userId;
    /** 角色ID */
    private Integer roleCode;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress == null ? null : mailAddress.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    public String getNowAction() {
        return nowAction;
    }

    public void setNowAction(String nowAction) {
        this.nowAction = nowAction == null ? null : nowAction.trim();
    }
}