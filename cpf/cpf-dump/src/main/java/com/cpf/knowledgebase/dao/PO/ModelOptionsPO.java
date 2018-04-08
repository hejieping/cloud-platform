package com.cpf.knowledgebase.dao.PO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

/**
 * 各个模型的参数选项
 * Created by jieping on 2018-04-07
 */
@Data
public class ModelOptionsPO {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 模型类型
     */
    private String modelType;
    /**
     * 模型的参数
     */
    private Map<String,ModelOption> options;
}
