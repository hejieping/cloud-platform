package com.cpf.utils;

import com.cpf.constants.TimeIntervalEnum;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * influx sql语句生成器
 * Created by jieping on 2018-04-21
 */
public class InfluxSQLGenerator {
    private static String FROM = " from ";
    private static String SELECT = " select ";
    private static String WHERE = " where ";
    private static String GROUP_BY = " group by ";
    private static String AND = " and ";
    private static String FINISH = " ; ";
    private static String FILL = "  fill(0)" + FINISH;
    private static String LIMIT = " limit ";
    private static String COMMA = " , ";
    private static String ALL  = " * ";


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
    public static String dataSQL(String tableName,Long startTime,Long endTime,Long limit){
        StringBuffer sql = new StringBuffer();
        sql.append(SELECT + ALL + FROM + tableName + WHERE);
        startTime = TimeStampUtil.javaTime2Influx(startTime);
        endTime = TimeStampUtil.javaTime2Influx(endTime);
        String startTimeCondition = "time >" + startTime;
        String endTimeCondition = "time < " + endTime;
        sql.append(Joiner.on(AND).join(startTimeCondition,endTimeCondition));
        if(limit != null){
            sql.append(LIMIT + limit);
        }
        sql.append(FINISH);
        return sql.toString();
    }
    private static String meanSql(Map<String,String> tags, List<String> meanList, String tableName, Long startTime, Long endTime){
        String sql = SELECT + mean(meanList) + FROM + tableName + WHERE;
        //组装tag条件
        String conditionStr = condition(tags);
        //组装监控规则的时间段，转换为influx时间戳
        startTime = TimeStampUtil.javaTime2Influx(startTime);
        endTime = TimeStampUtil.javaTime2Influx(endTime);
        String startTimeCondition = "time >" + startTime;
        String endTimeCondition = "time < " + endTime;
        conditionStr = Joiner.on(AND).join(Lists.newArrayList(conditionStr,startTimeCondition,endTimeCondition));
        sql  = sql + conditionStr;
        return sql;
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
    private static String condition(Map<String,String> conditionMap){
        List<String> conditionList = Lists.newArrayList();
        for(Map.Entry<String,String> tag : conditionMap.entrySet()){
            StringBuffer condition = new StringBuffer();
            condition.append(tag.getKey());
            condition.append("='");
            condition.append(tag.getValue());
            condition.append("'");
            conditionList.add(condition.toString());
        }
        return Joiner.on(AND).join(conditionList);
    }
    public static void main(String[] args){
        System.out.println(InfluxSQLGenerator.dataSQL("win_cpu",12L,32L,5L));
    }
}
