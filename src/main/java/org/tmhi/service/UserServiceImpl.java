package org.tmhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tmhi.dao.UserDao;
import org.tmhi.facade.SessionFacade;
import org.tmhi.model.entity.UserEntity;
import org.tmhi.util.EncryptUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Author:       Hiei
 * Date:         2018/03/17
 * Description:  用户相关业务逻辑实现类
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
    public String checkLogin(HttpServletRequest request, String userName, String password) 
            throws Exception {
        
        String errorCode = "";
        
        // 通过用户名获取用户信息
        UserEntity user = userDao.queryUserByName(userName);
        
        if (Objects.isNull(user)) {
            // 用户名不存在
            errorCode = "E001-0003";
            return errorCode;
        }

        // 密码验证
        boolean isEquation = EncryptUtils.equalPassword(password, user.getPassword(), user.getPasswordSalt());
        
        if (!isEquation) {
            // 密码输入错误
            errorCode = "E001-0004";
            return errorCode;
        }
        // 验证成功，更新用户会话ID和登录时间
        UserEntity params = new UserEntity();
        params.setSessionId(request.getSession().getId());
        params.setCurrentLoginDatetime(Timestamp.valueOf(LocalDateTime.now()));
        params.setUserName(user.getUserName());
        // 执行更新ww
        if (userDao.updateUserByName(params) != 1) {
            // 更新失败，抛出异常并使事务回滚
            throw new RuntimeException("用户会话ID和登录时间更新失败。");
        }
        // 将用户信息存入Session
        SessionFacade.saveUserSession(request, user);
        
        return errorCode;
    }

    @Override
    public UserEntity queryUserByName(String userName) throws Exception {
        return userDao.queryUserByName(userName);
    }
}
