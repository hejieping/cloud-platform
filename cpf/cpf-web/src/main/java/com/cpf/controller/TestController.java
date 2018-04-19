package com.cpf.controller;

import com.cpf.monitor.RuleHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jieping on 2018-04-19
 */
@RestController
public class TestController {
    @Autowired
    private RuleHolder ruleHolder;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Object> test(){
        return new ResponseEntity<>(ruleHolder.getRuleMap(),HttpStatus.OK);
    }
}
