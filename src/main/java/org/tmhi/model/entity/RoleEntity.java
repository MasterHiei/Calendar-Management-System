package org.tmhi.model.entity;

import lombok.Data;

@Data
public class RoleEntity extends BaseEntity {
    /** 角色ID */
    private Integer roleCode;
    /** 角色名 */
    private String roleName;
}