package com.cpf.constants;

import com.alibaba.fastjson.JSON;
import com.cpf.logger.BusinessLogger;
import com.cpf.utils.ValidationUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;

import java.util.List;
import java.util.Map;
/**
 * @author jieping
 * @create 2018-04-09
 * @desc 算法模型类型
 **/
public enum ModelTypeEnum {

    IBk("k最近邻分类","weka.classifiers.lazy.IBk"),
    NAIVE_BAYES("朴素贝叶斯","weka.classifiers.bayes.NaiveBayes"),
    J48("C4.5决策树","weka.classifiers.trees.J48"),
    LMT("逻辑模型树","weka.classifiers.trees.LMT"),
    DECISION_STUMP("单层决策树","weka.classifiers.trees.DecisionStump"),
    SMO("支持向量机","weka.classifiers.functions.SMO"),
    ADABOOST_M1("AdaBoostM1","weka.classifiers.meta.AdaBoostM1"),
    Bagging("套袋法","weka.classifiers.meta.Bagging"),
    LOGIT_BOOST("LogitBoost","weka.classifiers.meta.LogitBoost"),
    STACKING("模型融合","weka.classifiers.meta.Stacking"),
    LOGISTIC("逻辑回归","weka.classifiers.functions.Logistic"),
    JRIP("规则学习","weka.classifiers.rules.JRip"),
    ONE_R("1-R分类","weka.classifiers.rules.OneR"),
    PART("PART决策","weka.classifiers.rules.PART");
    /**
     * 模型描述
     */
    private String desc;
    /**
     * 模型对应的类名
     */
    private String className;
    private static final Logger logger = LoggerFactory.getLogger(ModelTypeEnum.class);
    ModelTypeEnum(String desc,String className){
        this.desc = desc;
        this.className = className;
    }

    /**
     * 根据算法类型获取初始状态的算法模型
     * @param modelTypeEnum
     * @return
     */
    public static Classifier getClasifier(ModelTypeEnum modelTypeEnum){

        Classifier classifier = null;
        if(ValidationUtil.isNull(modelTypeEnum)){
            BusinessLogger.errorLog("ModelTypeEnum.getClasifier",
                    new String[]{JSON.toJSONString(modelTypeEnum)},
                    "MODEL_TYPE_INVALID",
                    "模型类型非法",
                    logger);
            return classifier;
        }
        try {
            Class c = Class.forName(modelTypeEnum.getClassName());
            classifier = (Classifier) c.newInstance();
        } catch (Exception e) {
            BusinessLogger.errorLog("ModelTypeEnum.getClasifier",
                    new String[]{JSON.toJSONString(modelTypeEnum),JSON.toJSONString(e)},
                    "MODEL_INIT_INVALID",
                    "模型初始化异常",
                    logger);
        }
        return classifier;
    }
    /**
     * 获取 前端展示格式的规则类型
     * @return
     */
    public static List<Map<String,String>> getViewOptions(){
        List<Map<String,String>> list = Lists.newArrayList();
        for(ModelTypeEnum modelTypeEnum : ModelTypeEnum.values()){
            Map<String,String> map = Maps.newHashMap();
            map.put("value",modelTypeEnum.toString());
            map.put("label",modelTypeEnum.getDesc());
            list.add(map);
        }
        return list;
    }
    public String getDesc() {
        return desc;
    }

    public String getClassName() {
        return className;
    }

    public static void main(String[] args) {
        //Class c = Class.forName("weka.classifiers.lazy.IBk");
        for(ModelTypeEnum modelTypeEnum : ModelTypeEnum.values()){
            System.out.println(ModelTypeEnum.getClasifier(modelTypeEnum));
        }
    }
}
