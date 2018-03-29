package org.tmhi.model.entity;

public class RoleEntity extends BaseEntity {
    /** 角色ID */
    private Integer roleCode;
    /** 角色名 */
    private String roleName;

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}