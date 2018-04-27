package com.cpf.influx.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cpf.constants.RuleTypeEnum;
import com.cpf.influx.dao.PO.CpuPO;
import com.cpf.utils.InfluxSQLGenerator;
import com.google.common.collect.Lists;
import org.influxdb.dto.Point;
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
    public QueryResult queryAVGGroupByTime(Map<String,String> tags, List<String> meanList, Long startTime, Long endTime, RuleTypeEnum ruleTypeEnum){
        Query query = new Query(InfluxSQLGenerator.meanDatasSql(tags,meanList,ruleTypeEnum.getType(),startTime,endTime),template.getDatabase());
        QueryResult result = template.query(query, TimeUnit.MILLISECONDS);
        return result;
    }

    /**
     * select * from tableNanem where time > startTime and time < endTime limit
     * @param tableName
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    public QueryResult queryDataByTime(String tableName,Map<String,String> tagMap,Long startTime,Long endTime,Long limit){
        Query query = new Query(InfluxSQLGenerator.dataSQL(tableName,tagMap,startTime,endTime,limit),template.getDatabase());
        QueryResult result = template.query(query, TimeUnit.MILLISECONDS);
        return result;
    }

    /**
     * 插入数据
     * @param point
     */
    public void insert(Point point){
        template.write(point);
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



    }
}
