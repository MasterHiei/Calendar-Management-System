package com.tmhi.form;

/**
 * Author:       Hiei
 * Date:         2018/03/25
 * Description:  登录页面
 * Modified By:
 */
public class LoginForm {
    /** 用户名 */
    private String userName;
    /** 用户密码 */
    private String password;
    /** 记住我 */
    private int rememberMe;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(int rememberMe) {
        this.rememberMe = rememberMe;
    }
}
