package com.cpf.constants;

import com.cpf.mysql.manager.DO.ModelOptionDO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 算法模型类型
 * Created by jieping on 2018-04-09
 */
public enum ModelTypeEnum {
    IBk("k最近邻分类"),
    LBR("贝叶斯"),
    J48("C4.5决策树"),
    LMT("逻辑模型树"),
    DECISION_STUMP("单层决策树"),
    SMO("支持向量机"),
    ADABOOST_M1("AdaBoostM1"),
    Bagging("套袋法"),
    LOGIT_BOOST("LogitBoost"),
    STACKING("模型融合"),
    LOGISTIC("逻辑回归"),
    JRIP("规则学习"),
    ONE_R("1-R分类"),
    PART("PART决策"),
    TEST("测试");
    private List<ModelOptionDO> options;
    private String desc;
    private ModelTypeEnum(String desc){
        this.desc = desc;
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

    public List<ModelOptionDO> getOptions() {
        return options;
    }
}
