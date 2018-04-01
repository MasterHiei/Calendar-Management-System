package org.tmhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tmhi.dao.UserDao;
import org.tmhi.model.entity.UserEntity;
import org.tmhi.util.DateConvertUtils;

/**
 * Author:       Hiei
 * Date:         2018/03/17
 * Description:  用户业务逻辑实现类
 * Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
    
    /** 用户数据访问层对象 */
    private  UserDao userDao;
    
    /** 构造器注入 */
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional
    public void updateUserLoginTime(String userName) throws Exception {

        // 写入参数
        UserEntity params = new UserEntity();
        params.setUserName(userName);
        params.setCurrentLoginDatetime(DateConvertUtils.getNowTimeStamp());
        // 执行更新
        if (userDao.updateUserByName(params) != 1) {
            // 更新失败，抛出异常并使事务回滚
            throw new RuntimeException("用户最近登录时间更新失败。");
        }
    }
}
