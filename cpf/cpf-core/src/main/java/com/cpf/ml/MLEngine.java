package com.cpf.ml;

import com.cpf.constants.AlarmTypeEnum;
import com.cpf.constants.ErrorConstants;
import com.cpf.exception.BusinessException;
import com.cpf.influx.holder.CpfClassifier;
import com.cpf.influx.holder.ModelHolder;
import com.cpf.influx.manager.DO.MonitorDO;
import com.cpf.mysql.manager.AlarmManager;
import com.cpf.mysql.manager.DO.AlarmDO;
import com.cpf.utils.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weka.core.Instance;

import java.util.Date;
import java.util.List;

/**
 * @author jieping
 * @create 2018-04-25 19:44
 * @desc 机器学习算法引擎
 **/
@Component
public class MLEngine  {
    @Autowired
    private ModelHolder modelHolder;
    private static final Double THRESHOLD  = 0.5D;
    @Autowired
    private AlarmManager alarmManager;
    public void predict(MonitorDO monitorDO) throws Exception {
        if(monitorDO == null){
            throw new BusinessException(ErrorConstants.PARAMS_INVALID);
        }
        List<CpfClassifier> cpfClassifierList = modelHolder.getClassifiers(monitorDO.getType());
        Instance instance = ModelUtil.monitorDO2Instance(monitorDO);
        //计算权重
        Double sum = 0D;
        Double predicts = 0D;
        for(CpfClassifier cpfClassifier : cpfClassifierList){
            sum+=cpfClassifier.getWeight();
            Double predict = cpfClassifier.getClassifier().classifyInstance(instance)*cpfClassifier.getWeight();
            predicts+=predict;
        }
        predicts /= sum;
        //如果小于阈值，代表存在危险
        if(predicts < THRESHOLD){
            //保存预警信息
            AlarmDO alarmDO = new AlarmDO();
            alarmDO.setType(AlarmTypeEnum.PREDICT);
            alarmDO.setMonitorDO(monitorDO);
            alarmDO.setTime(new Date());
            alarmManager.save(alarmDO);
        }
    }
}
