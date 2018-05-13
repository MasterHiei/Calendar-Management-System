package org.tmhi.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
public class EventEntity implements Serializable {
    
    /** 事件ID */
    private Long eventId;
    /** 事件标题 */
    private String eventTitle;
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
    /** 事件表示颜色 */
    private String eventColor;
    /** 事件所有者ID */
    private Integer eventOwnerId;
    /** 事件所有者名 */
    private String eventOwnerName;
    /** 用户ID */
    private Long userId;
}
