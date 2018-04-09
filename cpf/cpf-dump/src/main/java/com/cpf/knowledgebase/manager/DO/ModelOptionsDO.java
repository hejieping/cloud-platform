package com.cpf.knowledgebase.manager.DO;

import com.cpf.constants.OptionTypeEnum;
import lombok.Data;

import java.util.Map;

/**
 * Created by jieping on 2018-04-09
 */
@Data
public class ModelOptionsDO {
    private Long id;
    /**
     * 模型类型
     */
    private OptionTypeEnum modelType;
    /**
     * 模型的参数（json格式）
     */
    Map<String,ModelOptionDO> options;
}
