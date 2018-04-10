package com.cpf.knowledgebase.manager;

import com.cpf.constants.CpfDumpConstants;
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
public class ModelDOManager extends ServiceTemplate {
    @Autowired
    private ModelDAO modelDAO;
    @Autowired
    private ModelOptionsDAO modelD;
    private static Logger logger = LoggerFactory.getLogger(ModelDOManager.class);
    public ModelDO save(ModelDO modelDO){
        ModelPO modelPO = modelDAO.save(DOPOConverter.modelDO2PO(modelDO));
        if(modelPO != null){
            return DOPOConverter.modelPO2DO(modelPO);
        }
        return null;
    }
    public List<ModelDO> all(){
        return DOPOConverter.modelPOs2DOs(modelDAO.findAll());
    }
    public CallbackResult<ModelDO> modifyModel(ModelDO modelDO){
        Object  result = execute(logger, "modifyModel", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                if(modelDO==null || modelDO.getConfig() == null){
                    return CallbackResult.failure();
                }
                return CallbackResult.success();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                if(modelDAO.getById(modelDO.getId()) == null){
                    return new CallbackResult<Object>(null,false,CpfDumpConstants.NOT_FOUND_MODEL);
                }
                ModelPO modelPO = modelDAO.save(DOPOConverter.modelDO2PO(modelDO));
                return new CallbackResult<Object>(modelDO,true);
            }
        });
        return (CallbackResult<ModelDO>)result;
    }
}
