package com.cpf.constants;

public enum OptionTypeEnum {
    INTEGER("integer","整数"),
    DOUBLE("double","浮点数"),
    ENUM("enum","枚举"),
    BOOLEAN("bool","是否");

    private String type;
    private String desc;
    private OptionTypeEnum(String type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    }
}
