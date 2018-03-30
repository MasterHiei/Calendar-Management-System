package org.tmhi.model.entity;

import lombok.Data;

@Data
public class AuthEntity extends BaseEntity {
    /** 权限ID */
    private Integer authCode;
    /** 权限名 */
    private String authName;
}