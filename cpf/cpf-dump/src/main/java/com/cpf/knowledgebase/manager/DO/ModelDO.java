package com.cpf.knowledgebase.manager.DO;

import lombok.Data;

import javax.persistence.OneToOne;

/**
 * Created by jieping on 2018-04-09
 */
@Data
public class ModelDO {
    private Long id;
    private String name;
    /**
     * 模型占决策的比重
     */
    private Long weight;
    /**
     * 模型参数配置
     */
    @OneToOne
    private ModelOptionsDO config;
}