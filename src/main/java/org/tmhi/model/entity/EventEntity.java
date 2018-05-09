package org.tmhi.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
public class EventEntity implements Serializable {
    
    /** 事件ID */
    private Integer eventId;
    /** 事件标题 */
    private String eventTitle;
    /** 事件地点 */
    private String eventLocation;
    /** 事件详细 */
    private String eventContent;
    /** 事件开始日期 */
    private Date eventStartDate;
    /** 事件开始时间 */
    private Time eventStartTime;
    /** 事件结束日期 */
    private Date eventEndDate;
    /** 事件结束时间 */
    private Time eventEndTime;
    /** 事件表示颜色*/
    private String eventColor;
    /** 用户ID */
    private Integer userId;
    /** 用户名 */
    private String userName;
}
