package com.tmhi.dao;

import com.tmhi.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  用户信息数据访问层
 * Modified By:
 */
@Repository
public interface UserDao {

    /**
     * 根据用户名获取用户信息
     * @param   userName 用户名
     * @return  用户信息
     * @throws  Exception 异常
     */
    UserEntity queryUserByName(String userName) throws Exception;
}
