package org.tmhi.model.form;

import lombok.Data;

/**
 * Author:       Hiei
 * Date:         2018/03/25
 * Description:  登录页面
 * Modified By:
 */
@Data
public class LoginForm {
    /** 用户名 */
    private String userName;
    /** 用户密码 */
    private String password;
    /** 记住我 */
    private Integer rememberMe;
}
