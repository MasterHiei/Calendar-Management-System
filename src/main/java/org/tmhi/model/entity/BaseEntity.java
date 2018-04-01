package org.tmhi.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  基础实体类
 * Modified By:  可继承的通用实体
 */
@Data
public class BaseEntity implements Serializable {
    /** DELETE_FLAG_0：未删除 */
    public static final int DELETE_FLAG_0 = 0;
    /** DELETE_FLAG_1：已删除 */
    public static final int DELETE_FLAG_1 = 1;
    
    /** 删除标记 */
    private Integer deleteFlag;
    /** 创建者 */
    private String createUserId;
    /** 创建时间 */
    private Timestamp createDatetime;
    /** 更新者 */
    private String updateUserId;
    /** 更新时间 */
    private Timestamp updateDatetime;
    /** 版本号 */
    private Integer version;
}
