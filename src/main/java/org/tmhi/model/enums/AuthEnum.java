package org.tmhi.model.enums;

/**
 * Author:       Hiei
 * Date:         2018/04/08
 * Description:  用户权限枚举
 * Modified By:
 */
public enum AuthEnum {
    
    /** 权限类型：Hiei */
    HIEI(1),
    /** 权限类型：Member */
    MEMBER(2);
    
    /** 权限ID */
    private int authCode;
    
    /** 构造函数 */
    AuthEnum(int authCode) {
        this.authCode = authCode;
    }

    public int getAuthCode() {
        return authCode;
    }
}
