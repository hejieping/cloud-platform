package com.cpf.monitor;

import com.alibaba.fastjson.JSON;
import com.cpf.knowledgebase.manager.DO.RuleDO;
import com.cpf.knowledgebase.manager.RuleManager;
import com.cpf.logger.BusinessLogger;
import com.cpf.service.CallbackResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 监控规则 内存持有类，将监控规则从数据库拿到内存中
 * Created by jieping on 2018-04-19
 */
@Component
public class RuleHolder {
    @Autowired
    private RuleManager ruleManager;
    private static Logger logger = LoggerFactory.getLogger(RuleHolder.class);
    private  Map<String,List<RuleDO>> ruleMap = Maps.newHashMap();
    /**
     * 从数据库获取所有监控规则,替代现有规则,类初始化后会自动调用一次
     */
    @PostConstruct
    public  void refresh(){
        CallbackResult<List<RuleDO>> result = ruleManager.all();
        if(result.getSuccess()){
            //加锁，防止多个线程同时修改
            synchronized (ruleMap){
                for(RuleDO ruleDO : result.getResult()){
                    if(ruleMap.get(ruleDO.getType()) == null){
                        ruleMap.put(ruleDO.getType(),Lists.newArrayList());
                    }
                    ruleMap.get(ruleDO.getType()).add(ruleDO);
                }
            }
            BusinessLogger.infoLog("RuleHolder.refresh",new String[]{JSON.toJSONString(ruleMap)},"success","监控规则刷新成功",logger);
        }else {
            BusinessLogger.errorLog("RuleHolder.refresh",new String[]{JSON.toJSONString(result)},"RULES_REFRESH_FAILED","监控规则刷新失败",logger);
        }
    }
    public List<RuleDO> getRules(String type){
        if(ruleMap.get(type) == null){
            ruleMap.put(type,Lists.newArrayList());
        }
        return ruleMap.get(type);
    }
    public Map<String,List<RuleDO>> getRuleMap(){
        return ruleMap;
    }
}