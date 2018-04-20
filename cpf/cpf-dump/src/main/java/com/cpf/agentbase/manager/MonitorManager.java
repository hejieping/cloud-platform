package com.cpf.agentbase.manager;

import com.cpf.agentbase.dao.MonitorDAO;
import com.cpf.agentbase.manager.DO.MonitorDO;
import com.cpf.constants.CpfDumpConstants;
import com.cpf.exception.BusinessException;
import com.cpf.utils.TagUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jieping on 2018-04-20
 */
@Component
public class MonitorManager {
    @Autowired
    private MonitorDAO monitorDAO;

    /**
     * 查询过去一段时间的数据平均值
     * @param monitorDO
     * @param minutes
     * @return
     */
    public MonitorDO queryAVGByTime(MonitorDO monitorDO, Long minutes){
        Map<String,String> tagMap = Maps.newHashMap();
        //组装表的标签
        for(String tag : TagUtil.getTags(monitorDO.getType())){
            tagMap.put(tag,monitorDO.getData().get(tag));
        }
        QueryResult result = monitorDAO.queryAVGByTime(tagMap,monitorDO.getType(),minutes);
        //查询结果解析成domain对象
        List<MonitorDO> monitorDOList = parseQueryResult(result);
        if(CollectionUtils.isNotEmpty(monitorDOList) && monitorDOList.size() == 1){
            return monitorDOList.get(0);
        }else {
            throw new BusinessException(CpfDumpConstants.QUERY_AVG_DATA_FAILED);
        }
    }

    /**
     * 将查询结果转换为MonitorDO
     * @param result
     * @return
     */
    private List<MonitorDO> parseQueryResult(QueryResult result){
        List<MonitorDO> list = Lists.newArrayList();
        QueryResult.Series series = result.getResults().get(0).getSeries().get(0);
        //如果返回的是平均结果，需要去除结果属性的mean_前缀
        List<String> keys =  deleteMeanPrefix(series.getColumns());
        for(List<Object> values : series.getValues()){
             MonitorDO monitorDO = new MonitorDO();
             monitorDO.setType(series.getName());
             Map<String,String> data = Maps.newHashMap();
             for(int i = 0; i < values.size();i++){
                 data.put(keys.get(i),values.get(i).toString());
             }
             monitorDO.setData(data);
             list.add(monitorDO);
        }
        return list;
    }

    /**
     * 去除 mean_ 前缀
     * @param keys
     * @return
     */
    private List<String> deleteMeanPrefix(List<String> keys){
        List<String> list = Lists.newArrayList();
        for(String key : keys){
            list.add(StringUtils.stripStart(key,"mean_"));
        }
        return list;
    }
    public static void main(String[] args){
        String s = "mean_Percent_time";
        System.out.println(StringUtils.stripStart(s,"mean_"));
    }
}
