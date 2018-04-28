package com.cpf.constants;
/**
 * @author jieping
 * @create 2018-04-25 19:56
 * @desc 错误描述
 **/
public class ErrorDesc {
    /**
     * 错误码
     */
    private  String errorCode;
    /**
     * 错误信息
     */
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
