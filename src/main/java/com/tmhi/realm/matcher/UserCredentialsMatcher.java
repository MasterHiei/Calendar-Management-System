package com.tmhi.realm.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Author:       Hiei
 * Date:         2018/03/25
 * Description:  自定义密码验证器
 * Modified By:
 */
public class UserCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch (AuthenticationToken token, AuthenticationInfo info) {
        // TODO 密码验证规则实现
        return true;
    }
}
