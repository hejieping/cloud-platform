package com.cpf.ml;

import com.cpf.constants.ErrorConstants;
import com.cpf.exception.BusinessException;
import com.cpf.influx.holder.CpfClassifier;
import com.cpf.influx.holder.ModelHolder;
import com.cpf.influx.manager.DO.MonitorDO;
import com.cpf.utils.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weka.core.Instance;

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
    public boolean predict(MonitorDO monitorDO) throws Exception {
        if(monitorDO == null){
            throw new BusinessException(ErrorConstants.PARAMS_INVALID);
        }
        List<CpfClassifier> cpfClassifierList = modelHolder.getClassifiers(monitorDO.getType());
        Instance instance = ModelUtil.monitorDO2Instance(monitorDO);
        Double sum = 0D;
        Double predicts = 0D;
        for(CpfClassifier cpfClassifier : cpfClassifierList){
            sum+=cpfClassifier.getWeight();
            Double predict = cpfClassifier.getClassifier().classifyInstance(instance);
            predicts+=predict;
        }
        predicts /= sum;
        if(predicts < 0.5){
            return false;
        }else {
            return true;
        }
    }
}
