package com.wd.tech.data.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by &{USER} on &{DATE}.
 */
public class DataUtils {

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    private static SimpleDateFormat mSimpleDateFormat = null;

    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff  = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    //获取系统时间
    public static String getCurrentDate(long time) {
        Date d = new Date();
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return mSimpleDateFormat.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        mSimpleDateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        return mSimpleDateFormat.format(d);
    }
    /*时间戳转换成字符窜*/
    public static String getDateToStrings(long time) {
        Date d = new Date(time);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return mSimpleDateFormat.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToStringL(long time) {
        Date d = new Date(time);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return mSimpleDateFormat.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        mSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒SSS毫秒", Locale.getDefault());
        Date date = new Date();
        try {
            date = mSimpleDateFormat.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

}
