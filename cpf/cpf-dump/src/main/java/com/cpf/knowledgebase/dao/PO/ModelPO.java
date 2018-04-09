package com.cpf.knowledgebase.dao.PO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by jieping on 2018-04-08
 */
@Data
@Entity(name = "model")
public class ModelPO {
    @Id
    @GeneratedValue
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
    private ModelOptionsPO config;
}
