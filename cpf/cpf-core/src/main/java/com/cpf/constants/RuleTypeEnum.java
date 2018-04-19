package com.cpf.constants;

/**
 * Created by jieping on 2018-04-19
 */
public enum  RuleTypeEnum {
    CPU("cpu","win_cpu");
    private String type;
    private String tableName;

    private RuleTypeEnum(String type,String tableName){
        this.type = type;
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public String getTableName() {
        return tableName;
    }
}
