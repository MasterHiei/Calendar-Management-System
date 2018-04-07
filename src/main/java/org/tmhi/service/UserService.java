package org.tmhi.service;

import org.tmhi.model.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:       Hiei
 * Date:         2018/03/17
 * Description:  用户业务逻辑抽象类
 * Modified By:
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param   request HttpServletRequest
     * @param   userName 用户名
     * @param   password 用户密码
     * @return  错误信息代码
     * @throws  Exception 异常
     */
    String checkLogin(HttpServletRequest request, String userName, String password) throws Exception;

    /**
     * 根据用户名查询用户信息
     *
     * @param   userName 用户名
     * @return  用户信息
     * @throws  Exception 异常
     */
    UserEntity queryUserByName(String userName) throws Exception;
}
