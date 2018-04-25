package com.cpf.logger;

import com.google.common.base.Joiner;

/**
 * 日志格式打印
 * Created by jieping on 2018-04-02.
 */
public class LoggerFormat {
    public static final String FIRST_SPLIT = "|";
    public static final String SECOND_SPLIT = ",";
    public static String format(String business,String[] params,String result,String desc){
        if(params == null){
            params = new String[]{"null"};
        }
        return Joiner.on(FIRST_SPLIT).useForNull("null").join(business,
                Joiner.on(SECOND_SPLIT).useForNull("null").join(params),result,desc);

    }
    public static void main(String[] args){
        String[] a = new String[]{"asd",null,"asd"};
        System.out.print(LoggerFormat.format("console",null,"success",null));
    }
}

