package com.wd.tech.data.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by ZCQ on 2019/3/15.
 */
public class TimeUtil {

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long s){
        String res;
        //时间模板
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //将时间戳转换成Data
        Date date = new Date(s);
        //将data转换成时间字串
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToTime(Long s){
        String res;
        //时间模板
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        //将时间戳转换成Data
        Date date = new Date(s);
        //将data转换成时间字串
        res = simpleDateFormat.format(date);
        return res;
    }
}
