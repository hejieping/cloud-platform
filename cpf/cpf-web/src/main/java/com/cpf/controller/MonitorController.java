package com.cpf.controller;

import com.cpf.agentbase.manager.MonitorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by jieping on 2018-04-21
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private MonitorManager monitorManager;

    /**
     * 获取指定主机的在指定时间段内的所有性能数据平均值
     * @param host
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/allAVG", method = RequestMethod.GET)
    ResponseEntity<Object> allAVG(String host, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endTime){
        return new ResponseEntity<>(monitorManager.queryAllAVGByTime(host,startTime,endTime),HttpStatus.OK);
    }

}
