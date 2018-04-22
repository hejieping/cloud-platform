package com.cpf.mysql.manager.DO;

import com.cpf.mysql.dao.AssetDAO;
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
 * Created by jieping on 2018-04-22
 */
@Component
public class AssetManager extends ServiceTemplate {
    Logger logger = LoggerFactory.getLogger(AssetManager.class);
    @Autowired
    private AssetDAO assetDAO;
    public CallbackResult<List<AssetDO>> all(){
        Object result =execute(logger, "all", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                return CallbackResult.success();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                return new CallbackResult<>(DOPOConverter.assetPOS2DOS(assetDAO.findAll()),true);
            }
        });
        return (CallbackResult<List<AssetDO>>)result;
    }
}
