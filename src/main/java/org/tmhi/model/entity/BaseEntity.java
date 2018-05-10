package org.tmhi.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  基础实体类
 * Modified By:  可继承的通用实体
 */
@Data
public class BaseEntity implements Serializable {
    /** DELETE_FLAG_NO：未删除 */
    public static final int DELETE_FLAG_NO = 0;
    /** DELETE_FLAG_YES：已删除 */
    public static final int DELETE_FLAG_YES = 1;
    
    /** 是否删除 */
    private Integer deleteFlag;
}
