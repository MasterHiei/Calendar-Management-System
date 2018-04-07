package org.tmhi.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:       Hiei
 * Date:         2018/04/06
 * Description:  用户信息Session
 * Modified By:
 */
@Data
public class UserSessionDto implements Serializable {
    /** 用户ID*/
    private Integer userId;
    /** 用户名 */
    private String userName;
}
