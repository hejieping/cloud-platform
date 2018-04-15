package com.cpf.agentbase.manager;

import com.cpf.agentbase.dao.PO.PerformancePO;
import com.cpf.service.CallbackResult;
import com.cpf.service.ServiceExecuteTemplate;
import com.cpf.service.ServiceTemplate;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jieping on 2018-04-15
 */
@Component
public class PerformanceManager extends ServiceTemplate {
    private Logger logger = LoggerFactory.getLogger(PerformanceManager.class);
    public CallbackResult<List<PerformancePO>> realTime(){
        Object result = execute(logger, "realTime", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                return new CallbackResult<>(mock(),true);
            }
        });
        return (CallbackResult<List<PerformancePO>>)result;
    }
    private List<PerformancePO> mock(){
        List<PerformancePO> list = Lists.newArrayList();
        for(int i = 0; i < 4; i++){
            PerformancePO performancePO = new PerformancePO();
            performancePO.setId(Long.valueOf(i));
            performancePO.setUniCode("##A");
            performancePO.setCpu(80L);
            list.add(performancePO);
        }
        return list;
    }
}
