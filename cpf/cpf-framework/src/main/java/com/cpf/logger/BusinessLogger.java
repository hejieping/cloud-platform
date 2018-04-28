package com.cpf.logger;

import org.slf4j.Logger;


/**
 * @author jieping
 * @create 2018-04-02.
 * @desc 日志打印
 **/
public class BusinessLogger {
    public static void errorLog(String business, String[] params, String errorCode, String desc, Logger logger){
        logger.error(LoggerFormat.format(business,params,errorCode,desc));
    }
    public static void infoLog(String business, String[] params, String result, String desc, Logger logger){
        logger.info(LoggerFormat.format(business,params,result,desc));
    }
}
