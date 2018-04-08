package com.cpf.utils;

import com.cpf.constants.CpfDumpConstants;
import com.cpf.constants.OptionTypeEnum;
import com.cpf.exception.BusinessException;
import com.cpf.exception.SystemException;
import com.cpf.knowledgebase.dao.PO.ModelOption;
import com.cpf.knowledgebase.dao.PO.ModelOptionsPO;
import com.cpf.knowledgebase.dao.PO.ModelPO;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import weka.core.OptionHandler;
import weka.core.SerializationHelper;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jieping on 2018-04-08
 */
public class ModelUtil  {
    public static String modelPath =  "cpf-dump/src/main/resources/";
    public static String suffix = ".model";

    /**
     * 将训练模型持久化
     * @param id 模型对应的数据库信息ID
     * @param classifier 模型
     */
    public void serialization(Long id, Object classifier){
        try {
            SerializationHelper.write(modelPath+id+suffix,classifier);
        } catch (Exception e) {
            throw new SystemException(CpfDumpConstants.SERIALIZATION_ERROR,e);
        }
    }

    /**
     * 从文件提取出模型
     * @param id 模型对应的数据库信息ID
     * @return
     */
    public Object deSerialization(Long id){
        Object classifier = null;
        try {
            classifier  =  SerializationHelper.read(modelPath+id+suffix);
        } catch (Exception e) {
            throw new SystemException(CpfDumpConstants.DESERIALIZATION_ERROR,e);
        }
        return classifier;
    }

    /**
     * 解析modelPO相关联的模型，并提取出模型的参数
     * @param modelPO
     * @return
     */
    public ModelOptionsPO getOptions(ModelPO modelPO){
        OptionHandler optionHandler = (OptionHandler) deSerialization(modelPO.getId());
        String[] options = optionHandler.getOptions();
        ModelOptionsPO optionsPO = new ModelOptionsPO();
        //复制option，使函数不影响原来model的配置
        BeanUtils.copyProperties(optionsPO,modelPO.getConfig(),ModelOption.class);
        Iterator<String> iterator = Lists.newArrayList(options).iterator();
        //将参数解析成对象
        while(iterator.hasNext()){
            String key = iterator.next();
            ModelOption option = optionsPO.getOptions().get(key);
            if(option != null){
                //如果参数为bool类型，不需要设置参数的值
                if(option.getValueType() == OptionTypeEnum.BOOLEAN){
                    continue;
                }
                //设定参数的值
                if(judgeOptionType(key,option)){
                    if(iterator.hasNext()){
                        option.setValue(iterator.next());

                    }
                }
            }
        }
        return modelPO.getConfig();

    }

    /**
     * 设置模型参数，并持久化
     * @param modelPO
     */
    public void setOptions(ModelPO modelPO){
        OptionHandler optionHandler = (OptionHandler) deSerialization(modelPO.getId());
        List<String> optionList = Lists.newArrayList();
        for(ModelOption option : modelPO.getConfig().getOptions().values()){
            optionList.add(option.getKey());
            if(option.getValueType() != OptionTypeEnum.BOOLEAN){
                optionList.add(option.getValue());
            }
        }
        try {
            optionHandler.setOptions(optionList.toArray(new String[optionList.size()]));
        } catch (Exception e) {
            throw new BusinessException(CpfDumpConstants.SET_OPTION_ERROR,e);
        }
        serialization(modelPO.getId(),optionHandler);
    }

    /**
     * 判断value是否符合option的指定数据类型
     * @param value
     * @param option
     * @return
     */
    private  boolean judgeOptionType(String value, ModelOption option){
        if(option.getValueType() == OptionTypeEnum.INTEGER){
            return isInteger(value);
        }
        if(option.getValueType() == OptionTypeEnum.DOUBLE){
            return NumberUtils.isDigits(value);
        }
        if(option.getValueType() == OptionTypeEnum.ENUM){
            //TODO 需要修改为指定的枚举
            return true;
        }
        return false;
    }
    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


}
