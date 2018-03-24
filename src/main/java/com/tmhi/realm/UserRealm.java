package com.tmhi.realm;

import com.tmhi.dao.AuthDao;
import com.tmhi.dao.RoleDao;
import com.tmhi.dao.UserDao;
import com.tmhi.entity.AuthEntity;
import com.tmhi.entity.RoleEntity;
import com.tmhi.entity.UserEntity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  自定义用户域
 * Modified By:
 */
public class UserRealm extends AuthorizingRealm{

    /** 用户信息相关Dao */
    @Autowired
    private UserDao userDao;
    /** 角色信息相关Dao */
    @Autowired
    private RoleDao roleDao;
    /** 权限信息相关Dao */
    @Autowired
    private AuthDao authDao;
    
    /**
     * 添加角色和权限信息
     * @param userName 用户名
     * @param info shiro认证对象
     */
    private void addRoleAndPermission (String userName, SimpleAuthorizationInfo info) throws Exception {
        // 获取用户的所有角色信息
        List<RoleEntity> roleList = roleDao.queryRoleByUserName(userName);
        
        if (Objects.nonNull(roleList) && roleList.size() > 0) {
            for (RoleEntity role : roleList) {
                // 添加角色
                info.addRole(role.getRoleName());
                
                // 获取角色的所有权限信息
                List<AuthEntity> authList = authDao.queryAuthByRoleCode(role.getRoleCode());
                
                if (Objects.nonNull(authList) && authList.size() > 0) {
                    for (AuthEntity auth : authList) {
                        // 添加权限
                        info.addStringPermission(auth.getAuthName());
                    }
                }
            }
        }
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) 
            throws AuthorizationException {
        // 获取用户名（当前Realm的第一个元素）
        String userName = String.valueOf(principalCollection.fromRealm(getName()).iterator().next());
        
        try {
            if (StringUtils.hasText(userName)) {
                // 实例化shiro认证对象
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                // 根据用户ID获取角色和权限信息
                addRoleAndPermission(userName, info);
                // 返回shiro认证对象
                return info;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) 
            throws AuthenticationException {
        // 获取用户Token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        
        try {
            // 用户验证
            UserEntity user = userDao.queryUserByName(token.getUsername());
            
            if (Objects.nonNull(user)) {
                // 获取数据库中的盐值
                ByteSource passwordSalt = ByteSource.Util.bytes(user.getPasswordSalt());
                // 创建并返回shiro认证对象
                return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), passwordSalt, getName());
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }
}
