package com.tmhi.entity;

public class UserRoleEntity extends BaseEntity {
    /** 用户 */
    private Integer userId;
    /** 角色ID */
    private Integer roleCode;

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
}
