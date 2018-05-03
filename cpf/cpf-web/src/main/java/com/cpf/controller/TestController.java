package com.cpf.controller;

import com.cpf.influx.dao.CpuDAO;
import com.cpf.influx.holder.ModelHolder;
import com.cpf.influx.manager.MonitorManager;
import com.cpf.ml.MlEngine;
import com.cpf.monitor.MonitorEngine;
import com.cpf.monitor.RuleHolder;
import com.cpf.mysql.dao.AggreModelDAO;
import com.cpf.task.TrainTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
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
    @Autowired
    private CpuDAO cpuDAO;
    @Autowired
    private ModelHolder modelHolder;
    @Autowired
    private TrainTask trainTask;
    @Autowired
    private MlEngine mlEngine;
    @Autowired
    private MonitorManager monitorManager;
    @Autowired
    private MonitorEngine monitorEngine;
    @Autowired
    private AggreModelDAO aggreModelDAO;
    @Autowired
    private DefaultInfluxDBTemplate template;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Object> test() throws Exception {
////        List<MonitorDO> monitorDOList = monitorManager.queryDataByTime("win_cpu",null,null,null,2L).getResult();
////        mlEngine.predict(monitorDOList.get(0));
////        List<AggreModelPO> aggreModelPOS = aggreModelDAO.findAll();
////        AggreModelPO aggreModelPO = aggreModelDAO.findByModelsIsContaining(aggreModelPOS.get(0).getModels().get(0));
//
//        List<MonitorDO> monitorDOList = monitorManager.queryDataByTime("win_swap",null,null,null,10000L).getResult();
//        String unit = TimeIntervalEnum.generateInterval(TimeIntervalEnum.HOUR,1L);
//
//        MonitorDO derivativeDo = monitorManager.queryChangeRateByTime(monitorDOList.get(0),unit).getResult();
//        System.out.println(JSON.toJSONString(derivativeDo));
//        Map<String,String> derivativeMap = Maps.newHashMap();
//        derivativeMap.put("derivative_System_Calls_persec","-1.13303671875E7");
//        derivativeMap.put("derivative_Processor_Queue_Length","0.0");
//        derivativeMap.put("derivative_Context_Switches_persec","-8391714.2578125");
//        derivativeMap.put("derivative_System_Up_Time","-9.288360039550781E8");
//        Random random = new Random();
//        monitorDOList.forEach(monitorDO -> {
//            monitorDO.setType("win_swap_train");
//            monitorDO.getData().putAll(derivativeMap);
//            monitorDO.getData().put("danger",String.valueOf(random.nextBoolean()));
//            monitorDO.getData().put("time",TimeStampUtil.javaTime2Influx(System.currentTimeMillis()).toString());
//
//        });
//        List<Point> pointList= monitorDOList.stream().map(monitorDO -> monitorManager.convert2Point(monitorDO)).collect(Collectors.toList());
//        template.write(pointList);
//        trainTask.train();
//        List<MonitorDO> monitorDOList = monitorManager.queryDataByTime("win_cpu_train",null,null,null,1L).getResult();
//        MonitorDO monitorDO = monitorDOList.get(0);
//        monitorDO.setType("win_cpu");
//        mlEngine.predict(monitorDO);
        trainTask.train();
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
