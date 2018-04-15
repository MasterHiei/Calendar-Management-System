package org.tmhi.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     * 获取当前日期在星期中的天数
     * @return 当前日期在星期中的天数
     */
    public static int getDayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * 获取当前日期（字符串格式）
     * @return 当前日期（yyyy-MM-dd）
     */
    public static String getTodayString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
    }

    /**
     * 获取当月第一天在星期中的天数
     * @return 当月第一天在星期中的天数
     */
    public static int getFirstDayOfWeek() {
        return LocalDate.now().withDayOfMonth(1).getDayOfWeek().getValue();
    }

    /**
     * 获取当月最大天数
     * @return 当月最大天数
     */
    public static int getLengthOfMonth() {
        return LocalDate.now().lengthOfMonth();
    }

    /**
     * 获取前月最大天数
     * @return 前月最大天数
     */
    public static int getLengthOfPrevMonth() {
        return LocalDate.now().minusMonths(1).lengthOfMonth();
    }
}
