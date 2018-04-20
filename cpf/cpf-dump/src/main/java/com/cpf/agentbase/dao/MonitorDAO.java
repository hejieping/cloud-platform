package com.cpf.agentbase.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cpf.agentbase.dao.PO.CpuPO;
import com.cpf.utils.TimeStampUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * influxdb 监控数据 DAO
 * Created by jieping on 2018-04-15
 */
@Component
public class MonitorDAO {
    @Autowired
    private DefaultInfluxDBTemplate template;
    /**
     * 查询指定时间内的监控数据平均值
     * @param tags 表 tags
     * @param tableName 表名
     * @param minutes 指定时间
     * @return
     */
    public QueryResult queryAVGByTime(Map<String,String> tags,String tableName,Long minutes){
        String SQL = InfluxSQLGenerator(tags,tableName,minutes);
        Query query = new Query(SQL, template.getDatabase());
        QueryResult result = template.query(query, TimeUnit.MILLISECONDS);
        return result;
    }

    /**
     * influx查询语句生成器
     * @param tags
     * @param tableName
     * @param minutes
     * @return
     */
    private String InfluxSQLGenerator(Map<String,String> tags,String tableName,Long minutes){
        String sql = "select mean(*) from " + tableName + " where ";
        List<String> conditionList = Lists.newArrayList();
        //组装tag条件
        for(Map.Entry<String,String> tag : tags.entrySet()){
            StringBuffer condition = new StringBuffer();
            condition.append(tag.getKey());
            condition.append("='");
            condition.append(tag.getValue());
            condition.append("'");
            conditionList.add(condition.toString());
        }
        //组装监控规则的时间段，转换为influx时间戳
        Long endTime = System.currentTimeMillis();
        Long startTime = endTime - TimeStampUtil.minutes2Time(minutes);
        startTime = TimeStampUtil.javaTime2Influx(startTime);
        endTime = TimeStampUtil.javaTime2Influx(endTime);
        String startTimeCondition = "time >" + startTime;
        String endTimeCondition = "time < " + endTime;
        conditionList.add(startTimeCondition);
        conditionList.add(endTimeCondition);
        sql += Joiner.on(" and ").join(conditionList);
        //组装时间条件
        return sql;
    }

    public static void main(String[] args){
        CpuPO cpuPO = new CpuPO();
        cpuPO.setHost("host");
        cpuPO.setInstance("instance");
        cpuPO.setObjectname("name");
        cpuPO.setPercent_DPC_Time(11.6);
        cpuPO.setPercent_Idle_Time(11.4);
        cpuPO.setPercent_Interrupt_Time(5.9);
        cpuPO.setPercent_Privileged_Time(6D);
        cpuPO.setPercent_Processor_Time(9D);
        cpuPO.setPercent_User_Time(9.6);
        String jsonStr = JSON.toJSONString(cpuPO);
        System.out.println(jsonStr);
        Map<String,String> map = JSON.parseObject(jsonStr,new TypeReference<Map<String,String>>(){});
        System.out.println(JSON.toJSONString(map));
        System.out.println(1524218182184861300L);


        Map<String,String> tagMap = Maps.newHashMap();
        tagMap.put("instance","0");
        tagMap.put("objectname","PC-44");
        tagMap.put("host","intel");
        MonitorDAO monitorDAO = new MonitorDAO();
        System.out.println(monitorDAO.InfluxSQLGenerator(tagMap,"win_cpu",10L));

    }
}
