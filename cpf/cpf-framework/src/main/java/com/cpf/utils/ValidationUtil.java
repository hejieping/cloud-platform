package com.cpf.utils;

/**
 * Created by jieping on 2018-04-22
 */
public class ValidationUtil {
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
}