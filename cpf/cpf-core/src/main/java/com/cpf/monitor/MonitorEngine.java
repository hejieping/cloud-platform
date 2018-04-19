package com.cpf.monitor;

import com.alibaba.fastjson.JSON;
import com.cpf.agentbase.dao.CpuDAO;
import com.cpf.agentbase.dao.PO.CpuPO;
import com.cpf.constants.RuleTypeEnum;
import com.cpf.knowledgebase.manager.DO.RuleDO;
import com.cpf.logger.BusinessLogger;
import com.cpf.service.ServiceTemplate;
import org.apache.commons.lang3.math.NumberUtils;
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
public class MonitorEngine extends ServiceTemplate {
    private Logger logger = LoggerFactory.getLogger(MonitorEngine.class);
    @Autowired
    private RuleHolder ruleHolder;
    @Autowired
    private CpuDAO cpuDAO;
    public void monitor(CpuPO cpuPO){
        List<RuleDO> ruleDOList = ruleHolder.getRules(RuleTypeEnum.CPU.getType());
        for(RuleDO ruleDO : ruleDOList){
            //校验该时刻是否命中监控规则
            if(verify(cpuPO,ruleDO)){
                //如果该时刻命中规则，再判断一段时间内是否命中该规则
                if(verify(cpuDAO.queryAVGByTime(cpuPO,ruleDO.getTime()),ruleDO)){
                    warn(cpuPO,ruleDO);
                }
            }
        }
    }

    /**
     * 判断监控数据是否命中规则
     * @param cpuPO
     * @param ruleDO
     * @return
     */
    private boolean verify(CpuPO cpuPO,RuleDO ruleDO){
        boolean result = true;
        Map<String,Object> config = ruleDO.getConfig();
        if(cpuPO.getPercent_DPC_Time() < parseValueDefaultMax(config.get("Percent_DPC_Time").toString())){
            result = false;
            return result;
        }
        if(cpuPO.getPercent_Idle_Time() < parseValueDefaultMax(config.get("Percent_Idle_Time").toString())){
            result = false;
            return result;
        }
        if(cpuPO.getPercent_Interrupt_Time() < parseValueDefaultMax(config.get("Percent_Interrupt_Time").toString())){
            result = false;
            return result;
        }
        if(cpuPO.getPercent_Privileged_Time() < parseValueDefaultMax(config.get("Percent_Privileged_Time").toString())){
            result = false;
            return result;
        }
        if(cpuPO.getPercent_Processor_Time() < parseValueDefaultMax(config.get("Percent_Processor_Time").toString())){
            result = false;
            return result;
        }
        if(cpuPO.getPercent_User_Time() < parseValueDefaultMax(config.get("Percent_User_Time").toString())){
            result = false;
            return result;
        }
        return result;
    }
    private Double parseValueDefaultMax(String value){
        if(value == ""){
            return Double.MAX_VALUE;
        }else {
            return NumberUtils.toDouble(value);
        }
    }
    private void warn(Object object,RuleDO ruleDO){
        BusinessLogger.errorLog("MonitorEngine.monitor",
                new String[]{JSON.toJSONString(object),JSON.toJSONString(ruleDO)},
                "MONITOR_DANGER","监控报警",logger);
    }
}
