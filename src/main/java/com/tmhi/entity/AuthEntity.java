package com.tmhi.entity;

public class AuthEntity extends BaseEntity {
    /** 权限ID */
    private Integer authCode;
    /** 权限名 */
    private String authName;
    /** 角色ID */
    private Integer roleCode;

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }
}