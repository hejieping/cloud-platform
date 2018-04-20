package com.cpf.monitor;

/**
 * 监控数据比较器接口
 */
public interface MonitorComparator {
    boolean compare(String monitorParam,String ruleParam);
}
