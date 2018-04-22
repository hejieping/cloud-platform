package com.cpf.influx.dao;

import com.cpf.influx.dao.PO.CpuPO;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jieping on 2018-04-17
 */
@Component
public class CpuDAO {
    @Autowired
    private DefaultInfluxDBTemplate template;
    @Autowired
    private InfluxDBResultMapper mapper;
    public List<CpuPO> all() {
        Query query = new Query("select mean(*) from win_cpu group by time(10080m) fill(0);", template.getDatabase());
        QueryResult result = template.query(query, TimeUnit.MILLISECONDS);
        List<CpuPO> influxDataList = mapper.toPOJO(result, CpuPO.class);
        return influxDataList;
    }

    /**
     * 查询指定时间内的平均值
     * @param cpuPO
     * @param minutes
     * @return
     */
    public CpuPO queryAVGByTime(CpuPO cpuPO,Long minutes){
        //TODO 需要去除mock接口
        return cpuPO;
    }
}
