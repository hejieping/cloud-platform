package com.cpf.controller;

import com.cpf.constants.ModelTypeEnum;
import com.cpf.knowledgebase.dao.PO.KnowledgeRulePO;
import com.cpf.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jieping on 2018-04-05
 */

@RestController
@RequestMapping("/config")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;
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

}
