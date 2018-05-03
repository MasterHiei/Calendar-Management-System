package org.tmhi.model.enums;

/**
 * Author:       Hiei
 * Date:         2018/04/08
 * Description:  用户角色枚举
 * Modified By:
 */
public enum RoleEnum {
    
    /** 角色类型：Admiral */
    ADMIRAL(1),
    /** 角色类型：Commodore */
    COMMODORE(2),
    /** 角色类型：Soldier */
    SOLDIER(3);
    
    /** 角色ID */
    private int roleCode;
    
    /** 构造函数 */
    RoleEnum(int roleCode) {
        this.roleCode = roleCode;
    }

    public int getCode() {
        return roleCode;
    }
}
