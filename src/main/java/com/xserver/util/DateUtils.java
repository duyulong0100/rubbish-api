package com.xserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_FORMAT_NO_SPACE = "yyyyMMdd";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_TIME_MIN_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_TIME_AM_PM_FORMAT = "MM/dd/yyyy hh:mm:ss aa";

    public static long MS_OF_ONE_DAY = 24 * 3600 * 1000l;

    public static String formatDate(Date date) {
        return formatDate(DATE_TIME_FORMAT, date);
    }

    public static String formatDate(String pattern, Date date) {
        if (date == null || pattern == null)
            return null;
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date getDate(Long timeInMillis) {
        if (timeInMillis == null) {
            return null;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeInMillis);
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDate() {
        return new Date();
    }

    public static Date getDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        if (dateStr.length() == 10) {
            return getDate(DATE_FORMAT, dateStr);
        }
        return getDate(DATE_TIME_FORMAT, dateStr);
    }

    public static Date getDate(String pattern, String dateStr) {
        try {
            if (dateStr == null || pattern == null) {
                return null;
            }
            if (pattern.indexOf("aa") > -1) {
                // Example: 8/11/2026 2:58:30 PM
                return new SimpleDateFormat(pattern, Locale.ENGLISH).parse(dateStr);
            }
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取日期的起始时间
     * 将时间置为：00:00:00
     */
    public static Date getDateBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取日期的起始时间
     * 将时间置为：00:00:00
     */
    public static Date getDateYesBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis() - 24 * 3600 * 1000));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取日期的起始时间final
     * 将时间置为：00:00:00
     */
    public static String getDateBeginFinal(Date date) {
        String beginTime = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        if (hour.equals("0")) {
            hour = "00";
        }
        String min = String.valueOf(calendar.get(Calendar.MINUTE));
        if (min.equals("0")) {
            min = "00";
        }
        beginTime = hour + ":" + min;

        return beginTime;
    }

    /**
     * 获取日期的结束时间final
     * 将时间置为：00:00:00
     */
    public static String getDateEndFinal(Date date) {
        String beginTime = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        if (hour.equals("0")) {
            hour = "00";
        }
        String min = String.valueOf(calendar.get(Calendar.MINUTE));
        if (min.equals("0")) {
            min = "00";
        }
        beginTime = hour + ":" + min;

        return beginTime;
    }

    /**
     * 获取日期的结束时间
     * 将时间置为：23:59:59
     */
    public static Date getDateEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 比较时间，是否在几天内
     */
    public static boolean checkDate(Date date, int day) {
        if (date == null) {
            return false;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -day);
        return date.after(c.getTime());
    }

    /**
     * 修改日期时间
     * @param date
     *            日期
     * @param field
     *            指定待修改的位置，如：
     *            Calendar.YEAR
     *            Calendar.MONTH
     *            Calendar.DATE
     *            Calendar.HOUR
     *            Calendar.MINUTE
     *            Calendar.SECOND
     * @param howMany
     *            加/减 数值
     */
    public static Date changeDate(Date date, int field, int howMany) {
        if (date == null)
            return null;
        if (howMany == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, howMany);
        return c.getTime();
    }

    /**
     * 计算时间差
     */
    public static String diff(Date begin, Date end) {
        long diff = end.getTime() - begin.getTime();
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long minute = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long second = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);
        StringBuffer result = new StringBuffer();
        if (day > 0) {
            result.append(day).append("天");
        }
        if (hour > 0) {
            result.append(hour).append("小时");
        }
        result.append(minute).append("分");
        result.append(second).append("秒");
        return result.toString();
    }

    /**
     * 计算时间差 ,返回天数
     */
    public static long diffDays(Date begin, Date end) {
        long diff = end.getTime() - begin.getTime();
        long day = diff / MS_OF_ONE_DAY;
        return day;
    }

    /**
     * 计算时间差 ,返回天数 ceil
     */
    public static int diffDaysCeil(Date begin, Date end) {
        long diff = end.getTime() - begin.getTime();
        double day = Math.ceil(diff * 1.0d / MS_OF_ONE_DAY);
        return (int) day;
    }

}