package com.cpf.agentbase.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cpf.agentbase.dao.PO.CpuPO;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by jieping on 2018-04-15
 */
@Component
public class MonitorDAO {
    @Autowired
    private DefaultInfluxDBTemplate template;
    @Autowired
    private InfluxDBResultMapper mapper;

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
    private String InfluxSQLGenerator(Map<String,String> tags,String tableName,Long minutes){
        //TODO
        return null;
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
