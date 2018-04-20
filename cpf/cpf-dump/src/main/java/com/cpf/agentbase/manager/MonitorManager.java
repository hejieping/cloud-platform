package com.cpf.agentbase.manager;

import com.cpf.agentbase.dao.MonitorDAO;
import com.cpf.agentbase.manager.DO.MonitorDO;
import com.cpf.constants.CpfDumpConstants;
import com.cpf.exception.BusinessException;
import com.cpf.utils.TagUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
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
    private List<MonitorDO> parseQueryResult(QueryResult result){
        //TODO
        return Lists.newArrayList();
    }
}
