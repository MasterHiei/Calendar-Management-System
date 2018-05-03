package org.tmhi.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleEntity implements Serializable {
    /** 角色ID */
    private Integer roleCode;
    /** 角色名 */
    private String roleName;
}