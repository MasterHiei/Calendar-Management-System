package org.tmhi.service;

/**
 * Author:       Hiei
 * Date:         2018/03/17
 * Description:  用户业务逻辑抽象类
 * Modified By:
 */
public interface UserService {

    /**
     * 更新用户登录时间
     * 
     * @param   userName 用户名
     * @throws  Exception 异常
     */
    void updateUserLoginTime(String userName) throws Exception;
}
