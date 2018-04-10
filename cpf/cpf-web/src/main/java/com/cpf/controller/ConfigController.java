package com.cpf.controller;

import com.alibaba.fastjson.JSON;
import com.cpf.constants.ModelTypeEnum;
import com.cpf.knowledgebase.dao.PO.KnowledgeRulePO;
import com.cpf.knowledgebase.manager.DO.ModelDO;
import com.cpf.knowledgebase.manager.ModelDOManager;
import com.cpf.service.CallbackResult;
import com.cpf.service.KnowledgeService;
import com.cpf.utils.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jieping on 2018-04-05
 */

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private ModelDOManager modelDOManager;

    /**
     * 添加实时监控规则
     * @param knowledgeRulePO
     * @return
     */
    @RequestMapping(value = "/addRule", method = RequestMethod.POST)
    ResponseEntity<Boolean> addRule(@RequestBody KnowledgeRulePO knowledgeRulePO){
        knowledgeRulePO.setModifyTime(knowledgeRulePO.getCreateTime());
        Boolean saveResult = null;
        try {
            saveResult = knowledgeService.save(knowledgeRulePO);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Boolean>(saveResult,HttpStatus.OK);
    }

    /**
     * 获取模型的类型
     * @return
     */
    @RequestMapping(value = "/modelType", method = RequestMethod.GET)
    ResponseEntity<Object> modelType(){
        return  new ResponseEntity<Object>(ModelTypeEnum.getEnums(),HttpStatus.OK);
    }

    /**
     * 获取模型类
     * @param name 模型名称
     * @param modelType 模型类型
     * @return
     */
    @RequestMapping(value = "/model",method = RequestMethod.GET)
    ResponseEntity<Object> model(String name, String modelType){
        ModelDO modelDO = modelDOManager.save(ModelFactory.getModel(name,modelType));
        if(modelDO != null){
            return new ResponseEntity<Object>(modelDO,HttpStatus.OK);
        }
        return new ResponseEntity<Object>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 获取所有的模型
     * @param name
     * @param modelType
     * @return
     */
    @RequestMapping(value = "/models",method = RequestMethod.GET)
    ResponseEntity<Object> models(){
        return new ResponseEntity<Object>(modelDOManager.all(),HttpStatus.OK);
    }

    /**
     * 保存model
     * @param modelDO
     * @return
     */
    @RequestMapping(value = "/model",method = RequestMethod.POST)
    ResponseEntity<Object> model(@RequestBody ModelDO modelDO){
        CallbackResult<ModelDO> result = modelDOManager.modifyModel(modelDO);
        return new ResponseEntity<Object>(result.getSuccess(),HttpStatus.OK);
    }
}
