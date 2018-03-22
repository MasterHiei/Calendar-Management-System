package com.tmhi.entity;

public class RoleAuthEntity extends BaseEntity {
    /** 角色ID */
    private Integer roleCode;
    /** 权限ID */
    private Integer authCode;

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }
}
