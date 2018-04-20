package com.cpf.constants;

/**
 * Created by jieping on 2018-04-19
 */
public enum  RuleTypeEnum {
    CPU("win_cpu");
    private String type;

    private RuleTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public RuleTypeEnum typeOf(String type){
        for(RuleTypeEnum ruleTypeEnum : RuleTypeEnum.values()){
            if(ruleTypeEnum.getType().equals(type)){
                return ruleTypeEnum;
            }
        }
        return null;
    }
}
