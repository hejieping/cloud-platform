package com.cpf.controller;

import com.cpf.knowledgebase.dao.PO.RulePO;
import com.cpf.knowledgebase.manager.RuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jieping on 2018-04-11
 */
@RestController
@RequestMapping("/config")
public class RuleController {
    @Autowired
    private RuleManager ruleManager;
    /**
     * 添加或者修改实时监控规则
     * @param rulePO
     * @return
     */
    @RequestMapping(value = "/rule", method = RequestMethod.POST)
    ResponseEntity<Object> rule(@RequestBody RulePO rulePO){
        rulePO.setModifyTime(rulePO.getCreateTime());
        return new ResponseEntity<Object>(ruleManager.save(rulePO),HttpStatus.OK);
    }
    /**
     * 获取所有监控规则
     * @return
     */
    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    ResponseEntity<Object> rules(){
        return new ResponseEntity<Object>(ruleManager.all(),HttpStatus.OK);
    }

    /**
     * 删除指定监控规则
     * @param id
     * @return
     */
    @RequestMapping(value = "/rule", method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteRule(@RequestParam Long id){
        return new ResponseEntity<Object>(ruleManager.delete(id),HttpStatus.OK);
    }

}
