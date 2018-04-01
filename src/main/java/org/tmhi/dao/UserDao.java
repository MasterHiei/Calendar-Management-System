package org.tmhi.dao;

import org.tmhi.model.entity.UserEntity;
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
     * 根据用户名获取用户信息（未删除）
     * @param   userName 用户名
     * @return  用户信息
     * @throws  Exception 异常
     */
    UserEntity queryUserByName(String userName) throws Exception;

    /**
     * 根据用户名更新用户信息
     * @param   params 用户名
     * @return  用户信息
     * @throws  Exception 异常
     */
    int updateUserByName(UserEntity params) throws Exception;
}
