package com.cpf.constants;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * 监控数据类型（对应influx的表名称）
 * Created by jieping on 2018-04-19
 */
public enum  RuleTypeEnum {

    CPU("win_cpu",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname"}))),
    DISK("win_disk",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname"}))),
    DISKIO("win_diskio",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname"}))),
    MEM("win_mem",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","objectname"}))),
    NET("win_net",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname"}))),
    PERF("win_perf_counters",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname"}))),
    SWAP("win_swap",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname"}))),
    SYSTEM("win_system",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","objectname"}))),
    SYSTEM_DAYS("win_system_days",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host"})))
    ;
    /**
     * 监控规则类型，等于数据库表名
     */
    private String type;
    /**
     * 数据库表的tags
     */
    private List<String> tagList;

    private RuleTypeEnum(String type,List<String> tagList){
        this.type = type;
        this.tagList = tagList;
    }

    public String getType() {
        return type;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public static RuleTypeEnum typeOf(String type){
        for(RuleTypeEnum ruleTypeEnum : RuleTypeEnum.values()){
            if(ruleTypeEnum.getType().equals(type)){
                return ruleTypeEnum;
            }
        }
        return null;
    }
}
