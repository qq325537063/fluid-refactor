package com.paic.arch.interviews.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* @author 黎鹏
* @description DateUtils
* @version 创建时间：2018年3月5日 下午10:30:54
*/
public class DateUtils {
    private static Calendar calendar;
    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    public static final SimpleDateFormat standard_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat yyMMddHHmmss_sdf = new SimpleDateFormat("yyMMddHHmmss");

    public static final SimpleDateFormat yyyy_MM_dd_sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat HH_mm_ss_sdf = new SimpleDateFormat("HH:mm:ss");

    public static final SimpleDateFormat HHmmss_sdf = new SimpleDateFormat("HHmmss");

    public static final SimpleDateFormat yyMM_sdf = new SimpleDateFormat("yyMM");

    public static final SimpleDateFormat yy_MM_sdf = new SimpleDateFormat("yy_MM");


    /**
     * 转换时间字符串
     *
     * @param time        原时间字符串
     * @param fromPattern 原时间字符串格式
     * @param toPattern   目标字符串格式
     * @return 目标格式的时间字符串<br>如果原时间字符串不满足原格式，返回 {@code null}
     */
    public static String convertTimeByPattern(String time, SimpleDateFormat fromPattern, SimpleDateFormat toPattern) {
        Date dateTime = null;
        try {
            dateTime = fromPattern.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        String toTime = toPattern.format(dateTime);
        return toTime;
    }

    /**
     * Date对象转时间字符串
     *
     * @param date    时间对象
     * @param pattern 待返回的时间字符串格式
     * @return 对应格式 sdf 下的时间字符串
     */
    public static String dateToString(Date date, SimpleDateFormat pattern) {
        String time = pattern.format(date);
        return time;
    }

    /**
     * Date对象转标准格式时间字符串,<br> 实际调用{@link #dateToString(Date, SimpleDateFormat)}
     *
     * @param date 时间对象
     * @return 标准格式 {@link #standard_sdf} 时间字符串
     */
    public static String dateToString(Date date) {
        return dateToString(date, standard_sdf);
    }

    /**
     * 时间字符串转时间类型
     *
     * @param time    时间字符串
     * @param pattern 时间字符串格式
     * @return 时间字符串相对应的时间对象<br>如果时间字符串不满足格式，返回 {@code null}
     */
    public static Date stringToDate(String time, SimpleDateFormat pattern) {
        Date date = null;
        try {
            date = pattern.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取时间字符串的对应偏移后的时间
     *
     * @param time    原时间字符串
     * @param pattern 原时间字符串格式
     * @param field   偏移量单位
     * @param offset  偏移量
     * @return 偏移后的时间字符串
     */
    public static String getTimeOff(String time, SimpleDateFormat pattern, int field, int offset) {
        Date date = stringToDate(time, pattern);

        if (date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, offset);

        String offsetTime = pattern.format(c.getTime());

        return offsetTime;
    }

    /**
     * 时间字符串转时间类型，实际调用 {@link #stringToDate(String, SimpleDateFormat)}
     *
     * @param time 时间字符串
     * @return 标准格式时间字符串 {@link #standard_sdf} 的 <code>Date</code> 对象
     */
    public static Date stringToDate(String time) {
        return stringToDate(time, standard_sdf);
    }

    /**
     * 获取时间范围
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param field     递增字段
     * @param amount    递增值
     * @param pattern   返回时间格式
     * @return 指定开始结束时间、指定时间格式、指定递增字段和递增值 的时间范围
     */
    public static List<String> getRangeTime(Date beginTime, Date endTime, int field, int amount, SimpleDateFormat pattern) {
        Calendar c = Calendar.getInstance();

        List<String> rangeTimeList = new ArrayList<>();

        while (beginTime.compareTo(endTime) <= 0) {
            c.setTime(beginTime);
            c.add(field, amount);
            beginTime = c.getTime();
            rangeTimeList.add(dateToString(beginTime, pattern));
        }
        return rangeTimeList;
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }


    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

//    public static void main(String[] args) {
//        System.out.println(convertTimeByPattern("2017-03-24", yyyy_MM_dd_sdf, standard_sdf));
//        System.out.println(getTimeOff("2017-03-24", yyyy_MM_dd_sdf, Calendar.DAY_OF_MONTH, 10));
//
//        Date beginDate = stringToDate("2017-03-24", yyyy_MM_dd_sdf);
//        Date endDate = stringToDate("2017-04-08", yyyy_MM_dd_sdf);
//        System.out.println(getRangeTime(beginDate, endDate, Calendar.DAY_OF_MONTH, 1, yyMMddHHmmss_sdf));
//    }
}