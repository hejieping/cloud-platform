package com.cpf.agentbase.manager.DO;

import lombok.Data;

import java.util.Map;

/**
 * Created by jieping on 2018-04-20
 */
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
