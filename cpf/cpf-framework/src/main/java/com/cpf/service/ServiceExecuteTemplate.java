package com.cpf.service;

/**
 * @author jieping
 * @create 2018-04-25 19:56
 * @desc 业务service执行接口
 * @param <T>
 **/
public interface ServiceExecuteTemplate<T> {
    /**
     * 参数检查
     * @return
     */
    CallbackResult<Object> checkParams();

    /**
     * 执行主体代码
     * @return
     * @throws Exception
     */
    CallbackResult<Object> executeAction() throws Exception;
}
