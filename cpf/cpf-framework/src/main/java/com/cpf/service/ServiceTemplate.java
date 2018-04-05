package com.cpf.service;

import com.alibaba.fastjson.JSON;
import com.cpf.constants.ErrorConstants;
import com.cpf.exception.BusinessException;
import com.cpf.exception.SystemException;
import com.cpf.logger.BusinessLogger;
import org.slf4j.Logger;

/**
 * 业务service执行模板
 */
public class ServiceTemplate {
    public CallbackResult<Object> execute( Logger logger,String methodName,ServiceExecuteTemplate executeTemplate){
        methodName += this.getClass().toString();
        Long time = System.currentTimeMillis();
        CallbackResult<Object> returnResult = null;
        CallbackResult<Object> checkResult = null;
        CallbackResult<Object> executeResult = null;
        try {
            checkResult = executeTemplate.checkParams();
        }catch (BusinessException e) {
            BusinessLogger.errorLog(methodName,null,e.getErrorCode(),e.getMessage(),logger);
            throw e;
        }
        catch (SystemException e) {
            BusinessLogger.errorLog(methodName,null,e.getErrorCode(),e.getMessage(),logger);
            throw e;
        }
        catch (Exception e) {
            BusinessLogger.errorLog(methodName,null,ErrorConstants.SYSTEM_RRROR.getErrorCode(),ErrorConstants.SYSTEM_RRROR.getErrorMSG(),logger);

            throw new SystemException(ErrorConstants.CHECK_PARAM_ERROR,e);
        }
        //参数校验结果判断
        if(!checkResult.getSuccess()){
            returnResult = CallbackResult.failure();
            returnResult.setErrorCode(ErrorConstants.PARAMS_INVALID.getErrorCode());
        }
        try {
            executeResult = executeTemplate.executeAction();
        }catch (BusinessException e) {
            BusinessLogger.errorLog(methodName,null,e.getErrorCode(),e.getMessage(),logger);
            throw e;
        }
        catch (SystemException e) {
            BusinessLogger.errorLog(methodName,null,e.getErrorCode(),e.getMessage(),logger);
            throw e;
        }
        catch (Exception e) {
            BusinessLogger.errorLog(methodName,null,ErrorConstants.SYSTEM_RRROR.getErrorCode(),ErrorConstants.SYSTEM_RRROR.getErrorMSG(),logger);
            throw new SystemException(ErrorConstants.EXECUTE_SERVICE_ERROR,e);
        }
        String executeTime = String.valueOf(time - System.currentTimeMillis())+"ms";
        BusinessLogger.infoLog(methodName,null,JSON.toJSONString(executeResult.getResult()),executeTime,logger);
        return executeResult;
    }
}
