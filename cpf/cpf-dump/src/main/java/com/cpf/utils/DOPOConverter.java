package com.cpf.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cpf.constants.ModelTypeEnum;
import com.cpf.constants.OptionTypeEnum;
import com.cpf.knowledgebase.dao.PO.AggreModelPO;
import com.cpf.knowledgebase.dao.PO.ModelOptionPO;
import com.cpf.knowledgebase.dao.PO.ModelOptionsPO;
import com.cpf.knowledgebase.dao.PO.ModelPO;
import com.cpf.knowledgebase.manager.DO.AggreModelDO;
import com.cpf.knowledgebase.manager.DO.ModelDO;
import com.cpf.knowledgebase.manager.DO.ModelOptionDO;
import com.cpf.knowledgebase.manager.DO.ModelOptionsDO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

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
    public static List<ModelDO> modelPOs2DOs(List<ModelPO> modelPOList){
        List<ModelDO> list = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(modelPOList)){
            for(ModelPO modelPO : modelPOList){
                list.add(modelPO2DO(modelPO));
            }
        }
        return list;
    }
    public static List<ModelPO> modelDOs2POs(List<ModelDO> modelDOList){
        List<ModelPO> list = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(modelDOList)){
            for(ModelDO modelDO : modelDOList){
                list.add(modelDO2PO(modelDO));
            }
        }
        return list;
    }
    public static ModelOptionPO modelOptionDO2PO(ModelOptionDO modelOptionDO){
        ModelOptionPO modelOptionPO = new ModelOptionPO();
        modelOptionPO.setDesc(modelOptionDO.getDesc());
        modelOptionPO.setKey(modelOptionDO.getKey());
        modelOptionPO.setValue(modelOptionDO.getValue().toString());
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
        modelOptionsDO.setOptions(JSON.parseObject(modelOptionsPO.getOptions(),new TypeReference<List<ModelOptionDO>>(){}));
        return modelOptionsDO;
    }
    public static ModelOptionsPO modelOptionsDO2PO(ModelOptionsDO modelOptionsDO){
        ModelOptionsPO modelOptionsPO = new ModelOptionsPO();
        modelOptionsPO.setId(modelOptionsDO.getId());
        modelOptionsPO.setModelType(modelOptionsDO.getModelType().toString());
        modelOptionsPO.setOptions(JSON.toJSONString(modelOptionsDO.getOptions()));
        return modelOptionsPO;
    }
    public static AggreModelDO aggreModelPO2DO(AggreModelPO aggreModelPO){
        AggreModelDO aggreModelDO = new AggreModelDO();
        aggreModelDO.setId(aggreModelPO.getId());
        aggreModelDO.setName(aggreModelPO.getName());
        aggreModelDO.setScene(aggreModelPO.getScene());
        aggreModelDO.setModels(modelPOs2DOs(aggreModelPO.getModels()));
        return aggreModelDO;
    }
    public static AggreModelPO aggreModelDO2PO(AggreModelDO aggreModelDO){
        AggreModelPO aggreModelPO = new AggreModelPO();
        aggreModelPO.setId(aggreModelDO.getId());
        aggreModelPO.setScene(aggreModelDO.getScene());
        aggreModelPO.setName(aggreModelDO.getName());
        aggreModelPO.setModels(modelDOs2POs(aggreModelDO.getModels()));
        return aggreModelPO;
    }
    public static List<AggreModelDO> aggreModelPOS2DOS(List<AggreModelPO> aggreModelPOList){
        List<AggreModelDO> list = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(aggreModelPOList)){
            for(AggreModelPO aggreModelPO : aggreModelPOList){
                list.add(aggreModelPO2DO(aggreModelPO));
            }
        }
        return list;
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
        List<ModelOptionDO> list = Lists.newArrayList();
        list.add(modelOptionDO);
        modelOptionsDO.setOptions(list);

        ModelOptionsPO modelOptionsPO = DOPOConverter.modelOptionsDO2PO(modelOptionsDO);
        System.out.println(JSON.toJSONString(DOPOConverter.modelOptionsPO2DO(modelOptionsPO)));



    }
}
