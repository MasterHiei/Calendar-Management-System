package org.tmhi.model.form;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:       Hiei
 * Date:         2018/04/08
 * Description: calendar页面数据模型
 * Modified By:
 */
@Data
public class CalendarForm implements Serializable {
    /** 目标日期-年 */
    private String targetYear;
    /** 目标日期-月 */
    private String targetMonth;
    /** 目标日期-日 */
    private String targetDay;
    /** 当前月份第一天在星期中的天数 */
    private Integer firstDayOfWeek;
    /** 当月最大天数 */
    private Integer lengthOfMonth;
    /** 前月最大天数 */
    private Integer lengthOfPrevMonth;
    /** 是否为当前日期 */
    private Integer isToday;
    /** 执行模式 */
    private String mode;
}
