package com.cpf.agentbase.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cpf.agentbase.dao.PO.CpuPO;
import com.cpf.constants.RuleTypeEnum;
import com.cpf.utils.InfluxSQLGenerator;
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
     * 查询指定时间内的性能数据平均值
     * @param tags tag条件
     * @param tableName 表名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public QueryResult queryAVGByTime(Map<String,String> tags,String tableName,Long startTime,Long endTime){

        String SQL =  InfluxSQLGenerator.meanDataSQL(tags,tableName,startTime,endTime);
        Query query = new Query(SQL, template.getDatabase());
        QueryResult result = template.query(query, TimeUnit.MILLISECONDS);
        return result;
    }

    /**
     * 查询所主机 一段时间的平均性能数据
     * @param tags tag属性 ，只包含host属性
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public QueryResult queryAllAVGByTime(Map<String,String> tags,Long startTime,Long endTime){
        StringBuffer sqls = new StringBuffer();
        List<RuleTypeEnum> ruleTypeEnumList = Lists.newArrayList(RuleTypeEnum.values());
        for(RuleTypeEnum ruleTypeEnum : RuleTypeEnum.values()){
            sqls.append(InfluxSQLGenerator.meanDataSQL(tags,ruleTypeEnum.getType(),startTime,endTime));
        }
        Query query = new Query(sqls.toString(), template.getDatabase());
        QueryResult result = template.query(query, TimeUnit.MILLISECONDS);
        return result;
    }

    /**
     * 查询指定时间内的连续性能数据
     * @param tags
     * @param startTime
     * @param endTime
     * @param ruleTypeEnum
     * @return
     */
    public QueryResult queryDatasByTime(Map<String,String> tags,Long startTime,Long endTime,RuleTypeEnum ruleTypeEnum){
        Query query = new Query(InfluxSQLGenerator.meanDatasSql(tags,ruleTypeEnum.getType(),startTime,endTime),template.getDatabase());
        QueryResult result = template.query(query, TimeUnit.MILLISECONDS);
        return result;
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
        System.out.println(InfluxSQLGenerator.meanDataSQL(tagMap,"win_cpu",12L,20L));
        System.out.println(InfluxSQLGenerator.meanDatasSql(tagMap,"win_cpu",12L,20L));

    }
}
