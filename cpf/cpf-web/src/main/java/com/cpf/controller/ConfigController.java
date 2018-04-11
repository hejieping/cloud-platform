package com.cpf.controller;

import com.cpf.constants.ModelTypeEnum;
import com.cpf.knowledgebase.dao.PO.RulePO;
import com.cpf.knowledgebase.manager.DO.ModelDO;
import com.cpf.knowledgebase.manager.ModelManager;
import com.cpf.knowledgebase.manager.RuleManager;
import com.cpf.service.CallbackResult;
import com.cpf.utils.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by jieping on 2018-04-05
 */

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private RuleManager ruleManager;
    @Autowired
    private ModelManager modelManager;

    /**
     * 添加实时监控规则
     * @param rulePO
     * @return
     */
    @RequestMapping(value = "/addRule", method = RequestMethod.POST)
    ResponseEntity<Object> addRule(@RequestBody RulePO rulePO){
        rulePO.setModifyTime(rulePO.getCreateTime());
        return new ResponseEntity<Object>(ruleManager.addRule(rulePO),HttpStatus.OK);
    }

    /**
     * 获取模型的类型
     * @return
     */
    @RequestMapping(value = "/modelType", method = RequestMethod.GET)
    ResponseEntity<Object> modelType(){
        return  new ResponseEntity<Object>(new CallbackResult<List<Map<String,String>>>(ModelTypeEnum.getEnums(),true),HttpStatus.OK);
    }

    /**
     * 获取模型类
     * @param name 模型名称
     * @param modelType 模型类型
     * @return
     */
    @RequestMapping(value = "/model",method = RequestMethod.GET)
    ResponseEntity<Object> model(String name, String modelType){
        CallbackResult<ModelDO> result = modelManager.addModel(ModelFactory.getModel(name,modelType));
        return new ResponseEntity<Object>(result,HttpStatus.OK);
    }

    /**
     * 获取所有的模型
     * @param name
     * @param modelType
     * @return
     */
    @RequestMapping(value = "/models",method = RequestMethod.GET)
    ResponseEntity<Object> models(){
        return new ResponseEntity<Object>(modelManager.all(),HttpStatus.OK);
    }

    /**
     * 保存model
     * @param modelDO
     * @return
     */
    @RequestMapping(value = "/model",method = RequestMethod.POST)
    ResponseEntity<Object> model(@RequestBody ModelDO modelDO){
        CallbackResult<ModelDO> result = modelManager.modifyModel(modelDO);
        return new ResponseEntity<Object>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteModel",method = RequestMethod.GET)
    ResponseEntity<Object> model(Long id){
        CallbackResult<Object> result = modelManager.delete(id);
        return new ResponseEntity<Object>(result,HttpStatus.OK);
    }
}
