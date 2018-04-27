package com.cpf.influx.holder;

import com.alibaba.fastjson.JSON;
import com.cpf.logger.BusinessLogger;
import com.cpf.mysql.manager.AggreModelManager;
import com.cpf.mysql.manager.DO.AggreModelDO;
import com.cpf.mysql.manager.DO.ModelDO;
import com.cpf.service.CallbackResult;
import com.cpf.utils.ModelUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weka.classifiers.Classifier;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author jieping
 * @create 2018-04-25 21:28
 * @desc 算法模型持有类，将持久化所有算法模型加载到内存中
 **/
@Component
public class ModelHolder {
    @Autowired
    private AggreModelManager aggreModelManager;
    private static Logger logger = LoggerFactory.getLogger(ModelHolder.class);

    private Map<String,List<CpfClassifier>> classifiesMap = Maps.newConcurrentMap();
    @PostConstruct
    public void refresh(){
        CallbackResult<List<AggreModelDO>> result = aggreModelManager.all();
        if(result.getSuccess()){
            for(AggreModelDO aggreModelDO : result.getResult()){
                refresh(aggreModelDO);
            }
            BusinessLogger.infoLog("ModelHolder.refresh",new String[]{JSON.toJSONString(result)},"success","算法模型刷新成功",logger);

        }else {
            BusinessLogger.errorLog("ModelHolder.refresh",new String[]{JSON.toJSONString(result)},"MODELS_REFRESH_FAILED","获取聚合模型失败",logger);

        }

    }

    public void refresh(AggreModelDO aggreModelDO){
        List<CpfClassifier> classifierList = Lists.newArrayList();
        for(ModelDO modelDO : aggreModelDO.getModels()){
            try {
                CpfClassifier cpfClassifier = new CpfClassifier();
                cpfClassifier.setWeight(modelDO.getWeight());
                cpfClassifier.setClassifier((Classifier)ModelUtil.deSerialization(modelDO.getId()));
                classifierList.add(cpfClassifier);
            } catch (Exception e) {
                BusinessLogger.errorLog("ModelHolder.refresh",new String[]{JSON.toJSONString(modelDO),JSON.toJSONString(e)},"MODELS_DESERIALIZATION","算法模型反序列化失败",logger);
            }
        }
        classifiesMap.put(aggreModelDO.getScene(),classifierList);
    }
    public List<CpfClassifier> getClassifiers(String scene){
        if(classifiesMap.get(scene) == null){
            classifiesMap.put(scene,Lists.newArrayList());
        }
        return classifiesMap.get(scene);
    }
}
