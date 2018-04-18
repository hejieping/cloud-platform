package com.cpf.knowledgebase.manager;

import com.cpf.knowledgebase.dao.PO.RulePO;
import com.cpf.knowledgebase.dao.RuleDAO;
import com.cpf.knowledgebase.manager.DO.RuleDO;
import com.cpf.service.CallbackResult;
import com.cpf.service.ServiceExecuteTemplate;
import com.cpf.service.ServiceTemplate;
import com.cpf.utils.DOPOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jieping on 2018-04-05
 */
@Component
public class RuleManager extends ServiceTemplate {
    @Autowired
    private RuleDAO ruleDAO;
    private static Logger logger = LoggerFactory.getLogger(RuleManager.class);
    public CallbackResult<RuleDO> save(RuleDO ruleDO){
        Object result = execute(logger, "save", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(ruleDO == null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                RulePO rulePO = ruleDAO.save(DOPOConverter.ruleDO2PO(ruleDO));
                return new CallbackResult<>(DOPOConverter.rulePO2DO(rulePO),true);
            }
        });
        return (CallbackResult<RuleDO>)result;
    }
    public CallbackResult<List<RuleDO>> all(){
        Object result = execute(logger, "all", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                return CallbackResult.success();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                return new CallbackResult<>(DOPOConverter.rulePOS2DOS(ruleDAO.findAll()),true);
            }
        });
        return (CallbackResult<List<RuleDO>>)result;
    }
    public CallbackResult<Object> delete(Long id){
        Object result = execute(logger, "delete", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(id == null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                ruleDAO.deleteById(id);
                return CallbackResult.success();
            }
        });
        return (CallbackResult<Object>)result;
    }
}
