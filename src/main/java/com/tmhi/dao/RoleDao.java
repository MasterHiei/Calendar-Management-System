package com.tmhi.dao;

import com.tmhi.entity.RoleEntity;

import java.util.List;

/**
 * Author:       Hiei
 * Date:         2018/03/24
 * Description:  角色信息数据访问层
 * Modified By:
 */
public interface RoleDao {
    
    /**
     * 根据用户获取用户角色信息
     * @param   userId 用户ID
     * @return  用户角色信息
     * @throws  Exception 异常
     */
    List<RoleEntity> queryRoleByUserId (int userId) throws Exception;
}
