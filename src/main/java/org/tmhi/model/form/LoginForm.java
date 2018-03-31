package org.tmhi.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Author:       Hiei
 * Date:         2018/03/25
 * Description:  登录页面
 * Modified By:
 */
@Data
public class LoginForm {
    
    /** 用户名 */
    @NotBlank(message = "E001-0001")
    private String userName;
    
    /** 用户密码 */
    @NotBlank(message = "E001-0002")
    @Length(min = 6, max = 16, message = "E001-0003")
    private String password;
    
    /** 记住我 */
    private Integer rememberMe;
}
