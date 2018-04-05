package com.cpf.constants;

public class ErrorDesc {
    /**
     * 错误码
     */
    private  String errorCode;
    private  String errorMSG;
    public ErrorDesc(String errorCode,String errorMSG){
        this.errorCode = errorCode;
        this.errorMSG = errorMSG;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMSG() {
        return errorMSG;
    }
}
