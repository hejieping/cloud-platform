package com.cpf.service;

/**
 * 业务service执行接口
 * @param <T>
 */
public interface ServiceExecuteTemplate<T> {
    CallbackResult<Object> checkParams();
    CallbackResult<Object> executeAction();
}
