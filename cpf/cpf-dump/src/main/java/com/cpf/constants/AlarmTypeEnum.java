package com.cpf.constants;

/**
 * @author jieping
 * @create 2018-04-28 10:51
 * @desc 报警类型
 **/
public enum  AlarmTypeEnum {
    PREDICT("算法预警"),
    MONITOR("监控报警");
    private String desc;
    private AlarmTypeEnum(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
