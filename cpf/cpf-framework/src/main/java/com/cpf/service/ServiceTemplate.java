package com.cpf.service;

import com.cpf.constants.ErrorConstants;
import com.cpf.exception.BusinessException;
import com.cpf.exception.SystemException;
import com.cpf.logger.BusinessLogger;
import org.slf4j.Logger;

/**
 * service执行模板
 */
public class ServiceTemplate {
    public CallbackResult<Object> execute(ServiceExecuteTemplate executeTemplate, Logger logger){
        Long time = System.currentTimeMillis();
        CallbackResult<Object> returnResult = null;
        CallbackResult<Object> checkResult = null;
        CallbackResult<Object> executeResult = null;
        try {
            checkResult = executeTemplate.checkParams();
        }catch (BusinessException e) {
            BusinessLogger.errorLog(this.getClass().toString(),null,e.getErrorCode(),e.getMessage(),logger);
            throw e;
        }
        catch (SystemException e) {
            BusinessLogger.errorLog(this.getClass().toString(),null,e.getErrorCode(),e.getMessage(),logger);
            throw e;
        }
        catch (Exception e) {
            BusinessLogger.errorLog(this.getClass().toString(),null,ErrorConstants.SYSTEM_RRROR.getErrorCode(),ErrorConstants.SYSTEM_RRROR.getErrorMSG(),logger);

            throw new SystemException(ErrorConstants.CHECK_PARAM_ERROR,e);
        }
        //参数校验结果判断
        if(!checkResult.getSuccess()){
            returnResult = CallbackResult.failure();
            returnResult.setErrorCode(ErrorConstants.PARAMS_INVALID.getErrorCode());
        }
        //执行service内容
        try {
            executeResult = executeTemplate.executeAction();
        }catch (BusinessException e) {
            throw e;
        }
        catch (SystemException e) {
            throw e;
        }
        catch (Exception e) {
            throw new SystemException(ErrorConstants.EXECUTE_SERVICE_ERROR,e);
        }
        return executeResult;
    }
}
