package com.cpf.controller;

import com.cpf.knowledgebase.dao.PO.KnowledgeRulePO;
import com.cpf.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jieping on 2018-04-05
 */
@RestController
@RequestMapping("/knowledge")
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
}
