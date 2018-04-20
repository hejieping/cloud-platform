package com.cpf.monitor;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

/**
 * 监控数据比较器
 * Created by jieping on 2018-04-19
 */
public class ComparatorMap {
    private static Map<String,MonitorComparator> map = Maps.newHashMap();
    static{
        //CPU rule params
        map.put("Percent_DPC_Time",MaxDoubleComparator());
        map.put("Percent_Idle_Time",MaxDoubleComparator());
        map.put("Percent_Interrupt_Time",MaxDoubleComparator());
        map.put("Percent_Privileged_Time",MaxDoubleComparator());
        map.put("Percent_Processor_Time",MaxDoubleComparator());
        map.put("Percent_User_Time",MaxDoubleComparator());
    }
    public static MonitorComparator getComparator(String key){
        return map.get(key);
    }
    /**
     * 预期数据是否大于规则数据的double类型比较器
     * @return
     */
    private static MonitorComparator MaxDoubleComparator(){
        return ( monitorValue, ruleValue)->{
            //为空代表条件不限制
            if(ruleValue == "" || monitorValue == ""){
                return true;
            }else {
                return NumberUtils.toDouble(monitorValue) >= NumberUtils.toDouble(ruleValue);
            }
        };
    }
    /**
     * 预期数据是否小于规则数据的double类型比较器
     * @return
     */
    private static MonitorComparator MinDoubleComparator(){
        return ( monitorValue, ruleValue)->{
            //为空代表条件不限制
            if(ruleValue == "" || monitorValue == ""){
                return true;
            }else {
                return NumberUtils.toDouble(monitorValue) <= NumberUtils.toDouble(ruleValue);
            }
        };
    }
    /**
     * 预期数据是否大于规则数据的long类型比较器
     * @return
     */
    private static MonitorComparator MaxLongComparator(){
        return ( monitorValue, ruleValue)->{
            //为空代表条件不限制
            if(ruleValue == "" || monitorValue == ""){
                return true;
            }else {
                return NumberUtils.toLong(monitorValue) >= NumberUtils.toLong(ruleValue);
            }
        };
    }
}
