package com.cpf.knowledgebase.manager;

import com.cpf.knowledgebase.dao.PO.RulePO;
import com.cpf.knowledgebase.dao.RuleDAO;
import com.cpf.service.CallbackResult;
import com.cpf.service.ServiceExecuteTemplate;
import com.cpf.service.ServiceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jieping on 2018-04-05
 */
@Service
public class RuleManager extends ServiceTemplate {
    @Autowired
    private RuleDAO ruleDAO;
    private static Logger logger = LoggerFactory.getLogger(RuleManager.class);
    public CallbackResult<RulePO> save(RulePO rulePO){
        Object result = execute(logger, "save", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(rulePO == null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                return new CallbackResult<>(ruleDAO.save(rulePO),true);
            }
        });
        return (CallbackResult<RulePO>)result;
    }
    public CallbackResult<List<RulePO>> all(){
        Object result = execute(logger, "all", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                return CallbackResult.success();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                return new CallbackResult<>(ruleDAO.findAll(),true);
            }
        });
        return (CallbackResult<List<RulePO>>)result;
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
