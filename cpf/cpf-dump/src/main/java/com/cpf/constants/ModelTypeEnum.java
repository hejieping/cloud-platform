package com.cpf.constants;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by jieping on 2018-04-09
 */
public enum ModelTypeEnum {
    SVM("svm","支持向量"),
    BAYES("bayes","贝叶斯"),
    DF("df","决策树"),
    TEST("test","测试");
    private String type;
    private String desc;
    private ModelTypeEnum(String type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
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
}
