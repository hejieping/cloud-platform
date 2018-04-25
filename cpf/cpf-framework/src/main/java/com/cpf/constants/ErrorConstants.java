package com.cpf.constants;

/**
 * 错误码
 */
public class ErrorConstants {
    public static final ErrorDesc SYSTEM_RRROR = new ErrorDesc("SYSTEM_RRROR","系统错误");
    public static final ErrorDesc PARAMS_INVALID = new ErrorDesc("PARAMS_INVALID","非法参数");
    public static final ErrorDesc CHECK_PARAM_ERROR = new ErrorDesc("CHECK_PARAM_ERROR","参数校验失败");
    public static final ErrorDesc EXECUTE_SERVICE_ERROR = new ErrorDesc("EXECUTE_SERVICE_ERROR","service执行异常");
    public static final ErrorDesc FILE_NOT_FOUND = new ErrorDesc("FILE_NOT_FOUND","文件未找到");
}
