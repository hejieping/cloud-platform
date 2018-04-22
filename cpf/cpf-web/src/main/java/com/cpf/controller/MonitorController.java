package com.cpf.controller;

import com.cpf.influx.manager.MonitorManager;
import com.cpf.mysql.manager.DO.AssetManager;
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
    @Autowired
    private AssetManager assetManager;

    /**
     * 获取指定主机的在指定时间段内的所有性能数据平均值
     * @param host
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/allAVG", method = RequestMethod.GET)
    ResponseEntity<Object> allAVG(String host,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endTime){
        return new ResponseEntity<>(monitorManager.queryAllAVGByTime(host,startTime,endTime),HttpStatus.OK);
    }
    @RequestMapping(value = "/chartdata", method = RequestMethod.GET)
    ResponseEntity<Object> chartdata(String host,String table,String col,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endTime){
        return new ResponseEntity<>(monitorManager.queryChartDataByTime(host,table,col,startTime,endTime),HttpStatus.OK);
    }
    @RequestMapping(value = "/assets", method = RequestMethod.GET)
    ResponseEntity<Object> assets(){
        return new ResponseEntity<>(assetManager.all(),HttpStatus.OK);
    }

}
