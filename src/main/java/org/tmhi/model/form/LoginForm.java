package org.tmhi.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Author:       Hiei
 * Date:         2018/03/25
 * Description:  登录页面
 * Modified By:
 */
@Data
public class LoginForm implements Serializable {
    
    /** 用户名 */
    @NotBlank(message = "E001-0001")
    private String userName;
    
    /** 用户密码 */
    @NotBlank(message = "E001-0002")
    private String password;
    
    /** 记住我 */
    private Integer rememberMe;
}
