package com.cpf.utils;

/**
 * Created by jieping on 2018-04-20
 */
public class TimeStampUtil {
    /**
     * java时间戳和influx时间戳相差倍数
     */
    public static Long MULTIPLE = 1000000L;

    /**
     * java时间戳转influx时间戳
     * @param timestamp
     * @return
     */
    public static Long javaTime2Influx(Long timestamp){
        return timestamp * MULTIPLE;
    }

    /**
     * influx时间戳转java时间戳
     * @param timestamp
     * @return
     */
    public static Long InfluxTime2java(Long timestamp){
        return  timestamp/MULTIPLE;
    }

    /**
     * 分钟转毫秒
     * @param minutes
     * @return
     */
    public static Long minutes2Time(Long minutes){
        return minutes*60*1000;
    }
}
