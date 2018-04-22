package com.cpf.controller;

import com.cpf.influx.dao.CpuDAO;
import com.cpf.monitor.RuleHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by jieping on 2018-04-19
 */
@RestController
public class TestController {
    @Autowired
    private RuleHolder ruleHolder;
    @Autowired
    private CpuDAO cpuDAO;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Object> test(@RequestParam Date time){
        return new ResponseEntity<>(cpuDAO.all(),HttpStatus.OK);
    }
}
