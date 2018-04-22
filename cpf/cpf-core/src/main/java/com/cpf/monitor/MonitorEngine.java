package com.cpf.monitor;

import com.alibaba.fastjson.JSON;
import com.cpf.influx.manager.DO.MonitorDO;
import com.cpf.influx.manager.MonitorManager;
import com.cpf.mysql.manager.DO.RuleDO;
import com.cpf.logger.BusinessLogger;
import com.cpf.service.CallbackResult;
import com.cpf.service.ServiceExecuteTemplate;
import com.cpf.service.ServiceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 实时监控引擎
 * Created by jieping on 2018-04-19
 */
@Component
public class MonitorEngine extends ServiceTemplate{
    private Logger logger = LoggerFactory.getLogger(MonitorEngine.class);
    @Autowired
    private RuleHolder ruleHolder;
    @Autowired
    private MonitorManager monitorManager;

    /**
     * 实时监控，先判断该时刻数据是否满足监控规则，满足则判断监控规则规定的时间段内 监控数据平均值是否满足监控规则，满足则报警
     * @param monitorDO 实时监控的数据
     */
    public void monitor(MonitorDO monitorDO){
        execute(logger, "monitor", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(monitorDO == null){
                    return CallbackResult.failure();
                }else {
                    return CallbackResult.success();
                }
            }

            @Override
            public CallbackResult<Object> executeAction() {
                List<RuleDO> ruleDOList = ruleHolder.getRules(monitorDO.getType());
                for(RuleDO ruleDO : ruleDOList){
                    //判断该时刻是否满足监控规则
                    if(verify(monitorDO,ruleDO)){
                        //判断一定时间内的平均值是否满足监控规则
                        MonitorDO meanMonitor = monitorManager.queryAVGByTime(monitorDO,ruleDO.getTime());
                        if(verify(meanMonitor,ruleDO)){
                            warn(meanMonitor,ruleDO);
                        }
                    }
                }
                return CallbackResult.success();
            }
        });
    }

    /**
     * 判断监控数据是否命中规则
     * @param monitorDO
     * @param ruleDO
     * @return
     */
    private boolean verify(MonitorDO monitorDO,RuleDO ruleDO){
        Boolean result = true;
        //遍历监控数据，判断数据是否满足监控规则
        for(Map.Entry<String,String> monitorEntry : monitorDO.getData().entrySet()){
            MonitorComparator comparator = ComparatorMap.getComparator(monitorEntry.getKey());
            if(comparator == null){
                //该属性不需要比较
                continue;
            }
            //监控数据没有满足 规则
            if(!comparator.compare(monitorEntry.getValue(),ruleDO.getConfig().get(monitorEntry.getKey()))){
                result = false;
                return result;
            }
        }
        return result;
    }

    /**
     * 报警
     * @param monitorDO
     * @param ruleDO
     */
    private void warn(MonitorDO monitorDO,RuleDO ruleDO){
        //TODO 添加报警措施
        BusinessLogger.errorLog("MonitorEngine.monitor",
                new String[]{JSON.toJSONString(monitorDO),JSON.toJSONString(ruleDO)},
                "MONITOR_DANGER","监控报警",logger);
    }
}
