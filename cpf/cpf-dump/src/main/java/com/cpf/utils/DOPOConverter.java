package com.cpf.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cpf.constants.ModelTypeEnum;
import com.cpf.constants.OptionTypeEnum;
import com.cpf.knowledgebase.dao.PO.ModelOptionPO;
import com.cpf.knowledgebase.dao.PO.ModelOptionsPO;
import com.cpf.knowledgebase.dao.PO.ModelPO;
import com.cpf.knowledgebase.manager.DO.ModelDO;
import com.cpf.knowledgebase.manager.DO.ModelOptionDO;
import com.cpf.knowledgebase.manager.DO.ModelOptionsDO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**DO PO 转换工具
 * Created by jieping on 2018-04-09
 */
public class DOPOConverter {
    public static ModelPO modelDO2PO(ModelDO modelDO){
        ModelPO modelPO = new ModelPO();
        modelPO.setId(modelDO.getId());
        modelPO.setName(modelDO.getName());
        modelPO.setWeight(modelDO.getWeight());
        modelPO.setConfig(modelOptionsDO2PO(modelDO.getConfig()));
        return modelPO;
    }
    public static ModelDO modelPO2DO(ModelPO modelPO){
        ModelDO modelDO = new ModelDO();
        modelDO.setId(modelPO.getId());
        modelDO.setName(modelPO.getName());
        modelDO.setWeight(modelPO.getWeight());
        modelDO.setConfig(modelOptionsPO2DO(modelPO.getConfig()));
        return modelDO;
    }
    public static ModelOptionPO modelOptionDO2PO(ModelOptionDO modelOptionDO){
        ModelOptionPO modelOptionPO = new ModelOptionPO();
        modelOptionPO.setDesc(modelOptionDO.getDesc());
        modelOptionPO.setKey(modelOptionDO.getKey());
        modelOptionPO.setValue(modelOptionDO.getValue());
        modelOptionPO.setValueType(modelOptionDO.getValueType().toString());
        modelOptionPO.setExtension(modelOptionDO.getExtension());
        return modelOptionPO;
    }
    public static ModelOptionDO modelOptionPO2DO(ModelOptionPO modelOptionPO){
        ModelOptionDO modelOptionDO = new ModelOptionDO();
        modelOptionDO.setDesc(modelOptionPO.getDesc());
        modelOptionDO.setKey(modelOptionPO.getKey());
        modelOptionDO.setValue(modelOptionPO.getValue());
        modelOptionDO.setValueType(OptionTypeEnum.valueOf(modelOptionPO.getValueType()));
        modelOptionDO.setExtension(modelOptionPO.getExtension());
        return modelOptionDO;
    }
    public static ModelOptionsDO modelOptionsPO2DO(ModelOptionsPO modelOptionsPO){
        ModelOptionsDO modelOptionsDO = new ModelOptionsDO();
        modelOptionsDO.setId(modelOptionsPO.getId());
        modelOptionsDO.setModelType(ModelTypeEnum.valueOf(modelOptionsPO.getModelType()));
        modelOptionsDO.setOptions(JSON.parseObject(modelOptionsPO.getOptions(),new TypeReference<HashMap<String,ModelOptionDO>>(){}));
        return modelOptionsDO;
    }
    public static ModelOptionsPO modelOptionsDO2PO(ModelOptionsDO modelOptionsDO){
        ModelOptionsPO modelOptionsPO = new ModelOptionsPO();
        modelOptionsPO.setId(modelOptionsDO.getId());
        modelOptionsPO.setModelType(modelOptionsDO.getModelType().toString());
        modelOptionsPO.setOptions(JSON.toJSONString(modelOptionsDO.getOptions()));
        return modelOptionsPO;
    }
    public static void main(String[] args){
        ModelOptionsDO modelOptionsDO = new ModelOptionsDO();
        modelOptionsDO.setId(1L);
        modelOptionsDO.setModelType(ModelTypeEnum.BAYES);
        ModelOptionDO modelOptionDO = new ModelOptionDO();
        modelOptionDO.setExtension(Lists.newArrayList(new String[]{"asd","avasd"}));
        modelOptionDO.setValue("asd");
        modelOptionDO.setKey("key");
        modelOptionDO.setValueType(OptionTypeEnum.BOOLEAN);
        Map<String,ModelOptionDO> map = Maps.newHashMap();
        map.put("test1",modelOptionDO);
        map.put("test2",modelOptionDO);
        modelOptionsDO.setOptions(map);

        ModelOptionsPO modelOptionsPO = DOPOConverter.modelOptionsDO2PO(modelOptionsDO);
        System.out.println(JSON.toJSONString(DOPOConverter.modelOptionsPO2DO(modelOptionsPO)));


    }
}
