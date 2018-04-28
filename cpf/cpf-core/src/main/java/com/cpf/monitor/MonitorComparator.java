package com.cpf.monitor;
/**
 * @author jieping
 * @create 2018-04-25 19:56
 * @desc 监控数据比较器接口
 **/
public interface MonitorComparator {
    /**
     * 监控数据和监控规则数据之间的比较
     * @param monitorParam
     * @param ruleParam
     * @return
     */
    boolean compare(String monitorParam,String ruleParam);
}
