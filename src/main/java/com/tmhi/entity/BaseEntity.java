package com.tmhi.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  基础实体类
 * Modified By:  可继承的通用实体
 */
public class BaseEntity implements Serializable {
    /** DELETE_FLAG_0：未删除 */
    public static int DELETE_FLAG_0 = 0;
    /** DELETE_FLAG_1：已删除 */
    public static int DELETE_FLAG_1 = 1;
    
    /** 删除标记 */
    private String deleteFlag;
    /** 创建者 */
    private String createUserId;
    /** 创建日期 */
    private Timestamp createDatetime;
    /** 更新者 */
    private String updateUserId;
    /** 更新日期 */
    private Timestamp updateDatetime;
    /** 版本号 */
    private int version;

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Timestamp createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Timestamp getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Timestamp updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
