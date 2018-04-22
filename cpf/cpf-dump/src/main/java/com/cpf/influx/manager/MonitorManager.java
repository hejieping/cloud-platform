package com.cpf.influx.manager;

import com.cpf.constants.CpfDumpConstants;
import com.cpf.constants.RuleTypeEnum;
import com.cpf.exception.BusinessException;
import com.cpf.influx.dao.MonitorDAO;
import com.cpf.influx.manager.DO.MonitorDO;
import com.cpf.service.CallbackResult;
import com.cpf.service.ServiceExecuteTemplate;
import com.cpf.service.ServiceTemplate;
import com.cpf.utils.TimeStampUtil;
import com.cpf.utils.ValidationUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jieping on 2018-04-20
 */
@Component
public class MonitorManager extends ServiceTemplate {
    @Autowired
    private MonitorDAO monitorDAO;
    private static Logger logger = LoggerFactory.getLogger(MonitorManager.class);
    /**
     * 查询过去一段时间的数据平均值
     * monitorEngine专用，不对外，所以不用执行ServiceTemplate
     * @param monitorDO
     * @param minutes
     * @return
     */
    public MonitorDO queryAVGByTime(MonitorDO monitorDO, Long minutes){
        Map<String,String> tagMap = Maps.newHashMap();
        RuleTypeEnum ruleType = RuleTypeEnum.typeOf(monitorDO.getType());
        if(ruleType == null){
            //找不到监控类型
            throw new BusinessException(CpfDumpConstants.MONITOR_DATA_TYPE_ERROR);
        }
        //组装表的标签
        for(String tag : ruleType.getTagList()){
            tagMap.put(tag,monitorDO.getData().get(tag));
        }
        Long endTime = System.currentTimeMillis();
        Long startTime = endTime - TimeStampUtil.minutes2Time(minutes);
        QueryResult result = monitorDAO.queryAVGByTime(tagMap,monitorDO.getType(),startTime,endTime);
        //查询结果解析成domain对象
        List<MonitorDO> monitorDOList = parseQueryResult(result).get(monitorDO.getType());
        if(CollectionUtils.isNotEmpty(monitorDOList) && monitorDOList.size() == 1){
            return monitorDOList.get(0);
        }else {
            throw new BusinessException(CpfDumpConstants.QUERY_AVG_DATA_FAILED);
        }
    }
    public CallbackResult<List<MonitorDO>> queryAllAVGByTime(String hostName, Date startTime, Date endTime){
        Object result = execute(logger, "queryAllAVGByTime", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                 if(startTime == null || endTime == null || hostName == null){
                     return CallbackResult.failure();
                 }
                 return CallbackResult.success();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                Map<String,String> tagMap = Maps.newHashMap();
                tagMap.put("host",hostName);
                QueryResult result = monitorDAO.queryAllAVGByTime(tagMap,startTime.getTime(),endTime.getTime());
                Map<String,List<MonitorDO>> resultMap =  parseQueryResult(result);
                List<MonitorDO> resultList = Lists.newLinkedList();
                for(List<MonitorDO> monitorDOList : resultMap.values()){
                    resultList.addAll(monitorDOList);
                }
                return new CallbackResult<>(resultList,true);
            }
        });
        return ( CallbackResult<List<MonitorDO>>)result;
    }

    /**
     * 查询绘制图表所需数据
     */
    public CallbackResult<List<List<String>>> queryChartDataByTime(String hostName,String tableName,String col,Date startTime, Date endTime){
        Object result = execute(logger, "queryChartDataByTime", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(ValidationUtil.isNotNull(hostName,tableName,col,startTime,endTime)){
                    if(RuleTypeEnum.typeOf(tableName) != null){
                        return CallbackResult.success();

                    }
                }
                return CallbackResult.failure();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                List<String> meanList  = Lists.newArrayList(col);
                Map<String,String> tagMap = Maps.newHashMap();
                tagMap.put("host",hostName);
                QueryResult result = monitorDAO.queryDatasByTime(tagMap,meanList,startTime.getTime(),endTime.getTime(),RuleTypeEnum.typeOf(tableName));
                Map<String,List<MonitorDO>> resultMap =  parseQueryResult(result);
                List<String> timeList = Lists.newLinkedList();
                List<String> colList = Lists.newLinkedList();
                for(MonitorDO monitorDO : resultMap.get(tableName)){
                    timeList.add(TimeStampUtil.influxTime2Java(monitorDO.getData().get("time")));
                    colList.add(monitorDO.getData().get(col));
                }
                return new CallbackResult<>(Lists.newArrayList(timeList,colList),true);
            }
        });
        return (CallbackResult<List<List<String>>>)result;
    }
    /**
     * 将查询结果转换为MonitorDO
     * @param result
     * @return
     */
    private Map<String,List<MonitorDO>> parseQueryResult(QueryResult result){
        Map<String,List<MonitorDO>> resultMap = Maps.newHashMap();
        //一个Result代表一个表的查询结果
        for(QueryResult.Result r : result.getResults()){
            //查询结果为空
            if(CollectionUtils.isEmpty(r.getSeries())){
                continue;
            }
            QueryResult.Series series = r.getSeries().get(0);
            List<MonitorDO> list = Lists.newArrayList();
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
            resultMap.put(series.getName(),list);
        }
        return resultMap;
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
