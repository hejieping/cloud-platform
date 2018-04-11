package com.cpf.knowledgebase.manager;

import com.cpf.constants.CpfDumpConstants;
import com.cpf.exception.BusinessException;
import com.cpf.knowledgebase.dao.ModelDAO;
import com.cpf.knowledgebase.dao.ModelOptionsDAO;
import com.cpf.knowledgebase.dao.PO.ModelPO;
import com.cpf.knowledgebase.manager.DO.ModelDO;
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
 * Created by jieping on 2018-04-05
 */
@Component
public class ModelManager extends ServiceTemplate {
    @Autowired
    private ModelDAO modelDAO;
    @Autowired
    private ModelOptionsDAO modelD;
    private static Logger logger = LoggerFactory.getLogger(ModelManager.class);
    public CallbackResult<ModelDO> addModel(ModelDO modelDO){
        Object  result = execute(logger, "modifyModel", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(modelDO==null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                ModelPO modelPO = modelDAO.save(DOPOConverter.modelDO2PO(modelDO));
                return new CallbackResult<Object>(DOPOConverter.modelPO2DO(modelPO),true);
            }
        });
        return (CallbackResult<ModelDO>)result;
    }
    public CallbackResult<List<ModelDO>> all(){
        Object  result = execute(logger, "modifyModel", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                return new CallbackResult<Object>(DOPOConverter.modelPOs2DOs(modelDAO.findAll()),true);
            }
        });
        return (CallbackResult<List<ModelDO>>)result;
    }
    public CallbackResult<ModelDO> modifyModel(ModelDO modelDO){
        Object  result = execute(logger, "modifyModel", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(modelDO==null || modelDO.getConfig() == null){
                    return CallbackResult.failure();
                }
                if(modelDAO.getById(modelDO.getId()) == null){
                    throw new BusinessException(CpfDumpConstants.NOT_FOUND_MODEL);
                }
                return CallbackResult.success();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                ModelPO modelPO = modelDAO.save(DOPOConverter.modelDO2PO(modelDO));
                return new CallbackResult<Object>(DOPOConverter.modelPO2DO(modelPO),true);
            }
        });
        return (CallbackResult<ModelDO>)result;
    }
    public CallbackResult<Object> delete(Long id){
        Object  result = execute(logger, "modifyModel", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(id==null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }
            @Override
            public CallbackResult<Object> executeAction() {
                modelDAO.deleteById(id);
                return CallbackResult.success();
            }
        });
        return (CallbackResult<Object>)result;
    }
}
