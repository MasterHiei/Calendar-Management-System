package org.tmhi.model.form;

import lombok.Data;
import org.tmhi.model.entity.EventEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       Hiei
 * Date:         2018/04/08
 * Description: calendar页面数据模型
 * Modified By:
 */
@Data
public class CalendarForm implements Serializable {

    /** 初始化 */
    public static final Integer IS_INIT_YES = 1;
    /** 非初始化 */
    public static final Integer IS_INIT_NO = 0;

    /** 目标年份 */
    private Integer year;
    /** 目标月份 */
    private Integer month;
    /** 是否为初始化 */
    private Integer isInit;
    /** 事件列表 */
    private List<EventEntity> eventList;
}
