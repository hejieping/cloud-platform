package com.cpf.utils;

import com.cpf.constants.RuleTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 记录 influxdb 各个表的tag
 * Created by jieping on 2018-04-20
 */
public class TagUtil {
    private static Map<String,List<String>> tagMap = Maps.newHashMap();
    static {
        //win_cpu tags
        List<String> cpuTags = Lists.newArrayList();
        cpuTags.add("host");
        cpuTags.add("instance");
        cpuTags.add("objectname");
        tagMap.put(RuleTypeEnum.CPU.getType(),cpuTags);
    }
    public static List<String> getTags(String type){
        List<String> tagList = tagMap.get(type);
        if(tagList == null){
            tagList = Lists.newArrayList();
        }
        return tagList;
    }
}
