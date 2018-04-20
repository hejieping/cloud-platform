package com.cpf.utils;

/**
 * Created by jieping on 2018-04-20
 */
public class TimeStampUtil {
    /**
     * java时间戳和influx时间戳相差倍数
     */
    public static Long MULTIPLE = 1000000L;
    public static Long convertInfluxTime(Long timestamp){
        return timestamp * MULTIPLE;
    }
    public static Long InfluxTime2java(Long timestamp){
        return  timestamp/MULTIPLE;
    }
}
