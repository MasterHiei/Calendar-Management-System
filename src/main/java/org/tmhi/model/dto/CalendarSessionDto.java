package org.tmhi.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Author:       Hiei
 * Date:         2018/05/06
 * Description:  日历信息Session
 * Modified By:
 */
@Data
public class CalendarSessionDto implements Serializable {
    /** 目标日期 */
    private LocalDate date;
}
