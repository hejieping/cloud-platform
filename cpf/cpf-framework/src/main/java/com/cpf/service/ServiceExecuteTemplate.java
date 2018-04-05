package com.cpf.service;

/**
 * service执行接口
 * @param <T>
 */
public interface ServiceExecuteTemplate<T> {
    CallbackResult<Object> checkParams();
    CallbackResult<Object> executeAction();
}
