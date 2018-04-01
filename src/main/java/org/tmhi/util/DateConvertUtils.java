package org.tmhi.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Author:       Hiei
 * Date:         2018/04/01
 * Description:  日期及时间通用方法封装类
 * Modified By:
 */
public class DateConvertUtils {

    /**
     * 获取当前日期
     * @return 当前日期（java.sql.Date）
     */
    public static Date getNowSQLDate() {
        return  Date.valueOf(LocalDate.now());
    }

    /**
     * 获取当前时间
     * @return 当前时间（java.sql.Timestamp）
     */
    public static Timestamp getNowTimeStamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
