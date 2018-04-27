package com.cpf.constants;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 监控数据类型（对应influx的表名称）
 * Created by jieping on 2018-04-19
 */
public enum  RuleTypeEnum {

    CPU("win_cpu",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname","danger"}))),
    DISK("win_disk",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname","danger"}))),
    DISKIO("win_diskio",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname","danger"}))),
    MEM("win_mem",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","objectname","danger"}))),
    NET("win_net",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname","danger"}))),
    PERF("win_perf_counters",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname","danger"}))),
    SWAP("win_swap",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","instance","objectname","danger"}))),
    SYSTEM("win_system",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","objectname","danger"}))),
    SYSTEM_DAYS("win_system_days",Collections.unmodifiableList(Lists.newArrayList(new String[]{"host","danger"})))
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

    /**
     * 获取 前端展示格式的规则类型
     * @return
     */
    public static List<Map<String,String>> getViewOptions(){
        List<Map<String,String>> list = Lists.newArrayList();
        for(RuleTypeEnum ruleTypeEnum : RuleTypeEnum.values()){
            Map<String,String> map = Maps.newHashMap();
            map.put("value",ruleTypeEnum.getType());
            map.put("label",ruleTypeEnum.getType());
            list.add(map);
        }
        return list;
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
