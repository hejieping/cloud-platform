package com.cpf.constants;

/**
 * 时间间隔
 */
public enum TimeIntervalEnum {
    MINUTE("m"),
    HOUT("h"),
    DAY("d"),
    WEEK("w")
    ;
    private String unit;
    /**
     * 分钟，小时，天数和星期的毫秒数
     */
    private static Long M = 60*1000L;
    private static Long H = M*60;
    private static Long D = H*24;
    private static Long W = D*7;
    private TimeIntervalEnum(String unit){
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    /**
     * 组装指定时间
     * @param intervalEnum 时间单位
     * @param num 时间数值
     * @return
     */
    private static String generateInterval(TimeIntervalEnum intervalEnum, Long num){
        return  num+intervalEnum.getUnit();
    }

    /**
     * 切分时间段，确保每段份数<100
     * @param startTime
     * @param endTime
     * @return
     */
    public static String interval(Long startTime,Long endTime){
        Long diff = (endTime - startTime)/100;
        return generateInterval(MINUTE,diff/M);
    }
}
