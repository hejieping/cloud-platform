package com.cpf.utils;

import com.cpf.constants.ModelTypeEnum;
import com.cpf.constants.OptionTypeEnum;
import com.cpf.mysql.manager.DO.ModelDO;
import com.cpf.mysql.manager.DO.ModelOptionDO;
import com.cpf.mysql.manager.DO.ModelOptionsDO;
import com.google.common.collect.Lists;

import java.util.List;

/**模型工厂，产生指定模型的类
 * Created by jieping on 2018-04-08
 */
public class ModelFactory {
    /**
     * 算法模型的可选参数
     */
    private static List<ModelOptionDO> IBk = Lists.newArrayList();
    private static List<ModelOptionDO> LBR = Lists.newArrayList();
    private static List<ModelOptionDO> J48 = Lists.newArrayList();
    private static List<ModelOptionDO> LMT = Lists.newArrayList();
    private static List<ModelOptionDO> DECISION_STUMP = Lists.newArrayList();
    private static List<ModelOptionDO> SMO = Lists.newArrayList();
    private static List<ModelOptionDO> ADABOOST_M1 = Lists.newArrayList();
    private static List<ModelOptionDO> Bagging = Lists.newArrayList();
    private static List<ModelOptionDO> LOGIT_BOOST = Lists.newArrayList();
    private static List<ModelOptionDO> STACKING = Lists.newArrayList();
    private static List<ModelOptionDO> LOGISTIC = Lists.newArrayList();
    private static List<ModelOptionDO> JRIP = Lists.newArrayList();
    private static List<ModelOptionDO> ONE_R = Lists.newArrayList();
    private static List<ModelOptionDO> PART = Lists.newArrayList();
    private static List<ModelOptionDO> TEST = Lists.newArrayList();
    static {
        //TEST
        ModelOptionDO modelOptionDO1 = new ModelOptionDO();
        modelOptionDO1.setKey("-k");
        modelOptionDO1.setDesc("测试参数1");
        modelOptionDO1.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO2 = new ModelOptionDO();
        modelOptionDO2.setKey("-p");
        modelOptionDO2.setDesc("测试参数2");
        modelOptionDO2.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO3 = new ModelOptionDO();
        modelOptionDO3.setKey("-c");
        modelOptionDO3.setDesc("测试参数3");
        modelOptionDO3.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO4 = new ModelOptionDO();
        modelOptionDO4.setKey("-m");
        modelOptionDO4.setDesc("测试参数4");
        modelOptionDO4.setValueType(OptionTypeEnum.ENUM);
        modelOptionDO4.setExtension(Lists.newArrayList(new String[]{"a","b","c"}));
        TEST.add(modelOptionDO1);
        TEST.add(modelOptionDO2);
        TEST.add(modelOptionDO3);
        TEST.add(modelOptionDO4);
        //IBK
    }
    /**
     * 根据模型名称和模型类型产生一个模型类，
     * @param name 模型名称
     * @param modelType 模型类型
     * @return
     */
    public static ModelDO getModel(String name,String modelType){
        ModelDO modelDO = new ModelDO();
        modelDO.setWeight(0L);
        modelDO.setName(name);
        modelDO.setConfig(getModelOptions(modelType));
        return modelDO;
    }

    /**
     * 根据模型类型获取模型参数
     * @param modeltype
     * @return
     */
    public static ModelOptionsDO getModelOptions(String modeltype){
        ModelTypeEnum modelTypeEnum = ModelTypeEnum.valueOf(modeltype);
        if(modelTypeEnum != null){
            ModelOptionsDO modelOptionsDO = new ModelOptionsDO();
            modelOptionsDO.setModelType(modelTypeEnum);
            modelOptionsDO.setOptions(getOptionList(modelTypeEnum));
            return modelOptionsDO;
        }
        return null;
    }

    /**
     * 根据模型类型获取模型参数
     * @param modelTypeEnum
     * @return
     */
    private static List<ModelOptionDO> getOptionList(ModelTypeEnum modelTypeEnum){
        List<ModelOptionDO> list = Lists.newArrayList();
        //TODO 修改为完整的模型类型
        switch (modelTypeEnum){
            case TEST:list = getTestList();break;
        }
        return list;
    }
    private static List<ModelOptionDO> getTestList(){
        List<ModelOptionDO> testOptions = Lists.newArrayList();
        ModelOptionDO modelOptionDO1 = new ModelOptionDO();
        modelOptionDO1.setKey("-k");
        modelOptionDO1.setDesc("测试参数1");
        modelOptionDO1.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO2 = new ModelOptionDO();
        modelOptionDO2.setKey("-p");
        modelOptionDO2.setDesc("测试参数2");
        modelOptionDO2.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO3 = new ModelOptionDO();
        modelOptionDO3.setKey("-c");
        modelOptionDO3.setDesc("测试参数3");
        modelOptionDO3.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO4 = new ModelOptionDO();
        modelOptionDO4.setKey("-m");
        modelOptionDO4.setDesc("测试参数4");
        modelOptionDO4.setValueType(OptionTypeEnum.ENUM);
        modelOptionDO4.setExtension(Lists.newArrayList(new String[]{"a","b","c"}));
        testOptions.add(modelOptionDO1);
        testOptions.add(modelOptionDO2);
        testOptions.add(modelOptionDO3);
        testOptions.add(modelOptionDO4);
        return testOptions;
    }
}
