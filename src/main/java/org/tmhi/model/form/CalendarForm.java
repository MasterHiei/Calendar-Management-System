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
    /** 目标年份 */
    private Integer year;
    /** 目标月份 */
    private Integer month;
    /** 事件列表 */
    private List<EventEntity> eventList;
}
