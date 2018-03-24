package com.tmhi.dao;

import com.tmhi.entity.AuthEntity;

import java.util.List;

/**
 * Author:       Hiei
 * Date:         2018/03/24
 * Description:  权限信息数据访问层
 * Modified By:
 */
public interface AuthDao {

    /**
     * 根据角色ID获取角色权限信息
     * @param   roleCode 角色ID
     * @return  角色权限信息
     * @throws  Exception 异常
     */
    List<AuthEntity> queryAuthByRoleCode (int roleCode) throws Exception;
}
