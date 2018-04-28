package com.cpf.influx.manager.DO;

import lombok.Data;

import java.util.Map;

/**
 * @author jieping
 * @create 2018-04-20
 **/

@Data
public class MonitorDO {
    /**
     * 监控数据类型 对应数据库表名
     */
    private String type;
    /**
     * 监控的数据
     */
    private Map<String,String> data;
}
