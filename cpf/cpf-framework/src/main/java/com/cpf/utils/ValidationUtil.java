package com.cpf.utils;


/**
 * @author jieping
 * @create 2018-04-22
 * @desc 校验工具
 **/
public class ValidationUtil {
    /**
     * 判断参数是否全部不为null
     * @param objects
     * @return
     */
    public static Boolean isNotNull(Object ...objects){
        boolean result = true;
        for(Object object : objects){
            if(object == null){
                result = false;
                break;
            }
        }
        return result;
    }
    public static Boolean isNull(Object ...objects){
        return !isNotNull(objects);
    }
    public static Boolean isAllNull(Object ...objects){
        boolean result = true;
        for(Object object : objects){
            if(object != null){
                result = false;
                break;
            }
        }
        return result;
    }

}