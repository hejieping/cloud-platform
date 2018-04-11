package com.cpf.knowledgebase.manager;

import com.cpf.constants.ErrorDesc;
import com.cpf.exception.BusinessException;
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
    public CallbackResult<RulePO> addRule(RulePO rulePO){
        Object result = execute(logger, "all", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(rulePO.getId() != null){
                    if(ruleDAO.getById(rulePO.getId()) != null){
                        throw new BusinessException(new ErrorDesc("RULE_AREADY_EXIT","规则已经存在，无法新增"));
                    }
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
}
