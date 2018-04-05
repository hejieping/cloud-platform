package com.cpf.exception;

import com.cpf.constants.ErrorDesc;

/**
 * 系统异常
 */
public class SystemException extends RuntimeException{
    private String errorCode;
    public SystemException(ErrorDesc errorDesc){
        super(errorDesc.getErrorMSG());
        this.errorCode = errorDesc.getErrorCode();
    }
    public SystemException(ErrorDesc errorDesc,Throwable cause){
        super(errorDesc.getErrorMSG(),cause);
        this.errorCode = errorDesc.getErrorCode();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
