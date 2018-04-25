package com.cpf.utils;

import com.cpf.constants.TimeIntervalEnum;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * influx sql语句生成器
 * Created by jieping on 2018-04-21
 */
public class InfluxSQLGenerator {
    private static final String FROM = " from ";
    private static final String SELECT = " select ";
    private static final String WHERE = " where ";
    private static final String GROUP_BY = " group by ";
    private static final String AND = " and ";
    private static final String FINISH = " ; ";
    private static final String FILL = "  fill(0)" + FINISH;
    private static final String LIMIT = " limit ";
    private static final String COMMA = " , ";
    private static final String ALL  = " * ";


    /**
     *  生成 select mean(*) from tableName where [tag=tagValue] and time > startTime and time < endTime fill(0);
     * @param tags 条件
     * @param tableName 表名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static String meanDataSQL(Map<String,String> tags, String tableName, Long startTime, Long endTime){
        return meanSql(tags,null,tableName,startTime,endTime) + FILL;
    }

    /**
     *  生成 select mean(*) from tableName where [tag=tagValue] and time > startTime and time < endTime group by time(interval) fill(0);
     * @param tags
     * @param tableName
     * @param startTime
     * @param endTime
     * @return
     */
    public static String meanDatasSql(Map<String,String> tags,List<String> meanList, String tableName, Long startTime, Long endTime){
        String sql = meanSql(tags,meanList,tableName,startTime,endTime);
        String group = GROUP_BY + "time(" + TimeIntervalEnum.interval(startTime,endTime) +")";
        return sql + group + FILL;
    }

    /**
     *  生成 select * from tableName where  and time > startTime and time < endTime ;
     * @param tableName
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    public static String dataSQL(String tableName,Map<String,String> tagMap,Long startTime,Long endTime,Long limit){
        StringBuffer sql = new StringBuffer();
        sql.append(SELECT + ALL + FROM + tableName + condition(tagMap,startTime,endTime));
        if(limit != null){
            sql.append(LIMIT + limit);
        }
        sql.append(FINISH);
        return sql.toString();
    }
    private static String meanSql(Map<String,String> tags, List<String> meanList, String tableName, Long startTime, Long endTime){
        return SELECT + mean(meanList) + FROM + tableName + condition(tags,startTime,endTime);
    }

    /**
     *  产生 mean(select1),mean(select2),mean(select2)
     * @param selectList
     * @return
     */
    private  static String mean(List<String> selectList){
        //为空代表全选
        if(selectList == null){
            return " mean(*) ";
        }
        List<String> means = Lists.newArrayList();
        for(String select : selectList){
            String str = " mean(" + select + ") as " + select + " ";
            means.add(str);
        }
        return Joiner.on(COMMA).join(means);
    }

    /**
     * 产生 key1=value1 and key2=value1 and key3=value1 and key4=value1 的条件语句
     * @param conditionMap
     * @return
     */
    private static String condition(Map<String,String> conditionMap,Long startTime,Long endTime){
        if(ValidationUtil.isAllNull(conditionMap,startTime,endTime)){
            return "";
        }
        List<String> conditionList = Lists.newArrayList();
        if(MapUtils.isNotEmpty(conditionMap)){
            for(Map.Entry<String,String> tag : conditionMap.entrySet()){
                StringBuffer condition = new StringBuffer();
                condition.append(tag.getKey());
                condition.append("='");
                condition.append(tag.getValue());
                condition.append("'");
                conditionList.add(condition.toString());
            }
        }
        if(startTime != null){
            conditionList.add(" time > " +  TimeStampUtil.javaTime2Influx(startTime));
        }
        if(endTime != null){
            conditionList.add(" time < " +  TimeStampUtil.javaTime2Influx(endTime));
        }
        return WHERE + Joiner.on(AND).join(conditionList);
    }
    public static void main(String[] args){

        Map<String,String> tagMap = Maps.newHashMap();
        tagMap.put("instance","0");
        tagMap.put("objectname","PC-44");
        tagMap.put("host","intel");
        List<String> meanList = Lists.newArrayList("dile_time");
        System.out.println(InfluxSQLGenerator.meanDataSQL(tagMap,"win_cpu",12000000L,20000000L));
        System.out.println(InfluxSQLGenerator.meanDatasSql(tagMap,meanList,"win_cpu",12000000L,20000000L));
        System.out.println(InfluxSQLGenerator.dataSQL("win_cpu",null,12L,32L,5L));
        System.out.println(InfluxSQLGenerator.dataSQL("win_cpu",null,null,32L,5L));
    }
}
