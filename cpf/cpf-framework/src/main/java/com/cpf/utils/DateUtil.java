package com.cpf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jieping
 * @create 2018-04-28 16:11
 * @desc 时间 转换工具
 **/
public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间转换成默认格式的字符串
     * @param date
     * @return
     */
    public static String format(Date date){
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }
}
