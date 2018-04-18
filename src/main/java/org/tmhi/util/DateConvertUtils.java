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
     * 
     * @return 当前日期（java.sql.Date）
     */
    public static Date getNowSQLDate() {
        return  Date.valueOf(LocalDate.now());
    }

    /**
     * 获取当前时间
     * 
     * @return 当前时间（java.sql.Timestamp）
     */
    public static Timestamp getNowTimeStamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * 获取当前日期在星期中的天数
     * 
     * @return 当前日期在星期中的天数
     */
    public static int getDayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * 获取当前日期（字符串格式）
     * 
     * @return 当前日期（yyyy-MM-dd）
     */
    public static String getTodayString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
    }

    /**
     * 获取目标月第一天在星期中的天数
     * 
     * @param date LocalDate
     * @return 目标月第一天在星期中的天数
     */
    public static int getFirstDayOfWeek(LocalDate date) {
        return date.withDayOfMonth(1).getDayOfWeek().getValue();
    }

    /**
     * 获取目标月最大天数
     *
     * @param date LocalDate
     * @return 目标月最大天数
     */
    public static int getLengthOfMonth(LocalDate date) {
        return date.lengthOfMonth();
    }

    /**
     * 获取目标月前月最大天数
     *
     * @param date LocalDate
     * @return 目标月前月最大天数
     */
    public static int getLengthOfPrevMonth(LocalDate date) {
        return date.minusMonths(1).lengthOfMonth();
    }

    /**
     * 根据年月日获取LocalDate
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @return LocalDate
     */
    public static LocalDate getLocalDateByYMD(Object year, Object month, Object day) {
        return LocalDate.of(Integer.valueOf(year.toString()), Integer.valueOf(month.toString()), Integer.valueOf(day.toString()));
    }
}
