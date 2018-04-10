package com.cpf.utils;

import com.cpf.constants.ModelTypeEnum;
import com.cpf.constants.OptionTypeEnum;
import com.cpf.knowledgebase.manager.DO.ModelDO;
import com.cpf.knowledgebase.manager.DO.ModelOptionDO;
import com.cpf.knowledgebase.manager.DO.ModelOptionsDO;
import com.google.common.collect.Lists;

import java.util.List;

/**模型工厂，产生指定模型的类
 * Created by jieping on 2018-04-08
 */
public class ModelFactory {
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
            case BAYES:list = getTestList();break;
            case TEST:list = getTestList();break;
            case SVM:list = getTestList();break;
            case DF:list = getTestList();break;
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
