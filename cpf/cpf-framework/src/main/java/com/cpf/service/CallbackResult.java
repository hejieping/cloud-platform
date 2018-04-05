package com.cpf.service;

import com.cpf.constants.ErrorDesc;
import lombok.Data;

/**
 * 业务service执行模板返回结果
 * @param <T>
 */
@Data
public class CallbackResult<T> {
    private Boolean success;
    private String errorCode;
    private T result;
    private String errorDesc;
    private CallbackResult(Boolean success){
        this.success = success;
    }
    public CallbackResult(T result,Boolean success){
        this.result = result;
        this.success = success;
    }
    public CallbackResult(T result, Boolean success, ErrorDesc errorDesc){
        this.result = result;
        this.success = success;
        this.errorCode = errorDesc.getErrorCode();
        this.errorDesc = errorDesc.getErrorMSG();
    }
    public static CallbackResult<Object> failure(){
        CallbackResult<Object> callbackResult = new CallbackResult<Object>(false);
        return callbackResult;
    }
    public static CallbackResult<Object> success(){
        CallbackResult<Object> callbackResult = new CallbackResult<Object>(true);
        return callbackResult;
    }

}
