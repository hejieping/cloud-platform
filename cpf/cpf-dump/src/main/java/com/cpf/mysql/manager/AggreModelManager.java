package com.cpf.mysql.manager;

import com.cpf.mysql.dao.AggreModelDAO;
import com.cpf.mysql.dao.PO.AggreModelPO;
import com.cpf.mysql.dao.PO.ModelPO;
import com.cpf.mysql.manager.DO.AggreModelDO;
import com.cpf.mysql.manager.DO.ModelDO;
import com.cpf.service.CallbackResult;
import com.cpf.service.ServiceExecuteTemplate;
import com.cpf.service.ServiceTemplate;
import com.cpf.utils.DOPOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jieping on 2018-04-12
 */
@Component
public class AggreModelManager extends ServiceTemplate {
    @Autowired
    private AggreModelDAO aggreModelDAO;
    private static Logger logger = LoggerFactory.getLogger(AggreModelManager.class);
    public CallbackResult<AggreModelDO> save(AggreModelDO aggreModelDO){
        Object  result = execute(logger, "save", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(aggreModelDO == null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                AggreModelPO result = aggreModelDAO.save(DOPOConverter.aggreModelDO2PO(aggreModelDO));
                return new CallbackResult<Object>(DOPOConverter.aggreModelPO2DO(result),true);
            }
        });
        return (CallbackResult<AggreModelDO>)result;
    }
    public CallbackResult<ModelDO> addModel(AggreModelDO aggreModelDO,ModelDO modelDO){
        Object  result = execute(logger, "addModel", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(aggreModelDO == null || modelDO == null || modelDO.getId() != null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                ModelPO modelPO = DOPOConverter.modelDO2PO(modelDO);
                AggreModelPO aggreModelPO = DOPOConverter.aggreModelDO2PO(aggreModelDO);
                aggreModelPO.getModels().add(modelPO);
                aggreModelDAO.save(aggreModelPO);
                return new CallbackResult<Object>(DOPOConverter.modelPO2DO(modelPO),true);
            }
        });
        return (CallbackResult<ModelDO>)result;
    }
    public CallbackResult<List<AggreModelDO>> all(){
        Object  result = execute(logger, "all", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                List<AggreModelPO> result = aggreModelDAO.findAll();
                return new CallbackResult<Object>(DOPOConverter.aggreModelPOS2DOS(result),true);
            }
        });
        return (CallbackResult<List<AggreModelDO>>)result;
    }
    public CallbackResult<Object> delete(Long id){
        Object  result = execute(logger, "delete", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(id == null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                aggreModelDAO.deleteById(id);
                return CallbackResult.success();
            }
        });
        return (CallbackResult<Object>)result;
    }
    public CallbackResult<AggreModelDO> getById(Long id){
        Object  result = execute(logger, "getById", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(id == null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                return new CallbackResult<Object>(DOPOConverter.aggreModelPO2DO(aggreModelDAO.getById(id)),true);
            }
        });
        return (CallbackResult<AggreModelDO>)result;
    }
}
