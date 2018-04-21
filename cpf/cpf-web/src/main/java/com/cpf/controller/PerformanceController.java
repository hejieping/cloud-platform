package com.cpf.controller;

import com.cpf.agentbase.manager.PerformanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jieping on 2018-04-15
 */
@RestController
@RequestMapping("/monitor")
public class PerformanceController {
    @Autowired
    private PerformanceManager performanceManager;

    /**
     * 获取所有设备的实时监控数据
     * @return
     */
    @RequestMapping(value = "/performance", method = RequestMethod.GET)
    ResponseEntity<Object> performance(){
        return  new ResponseEntity<Object>(performanceManager.realTime(),HttpStatus.OK);
    }
}
