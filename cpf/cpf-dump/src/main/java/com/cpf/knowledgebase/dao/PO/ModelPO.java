package com.cpf.knowledgebase.dao.PO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jieping on 2018-04-08
 */
@Data
public class ModelPO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private ModelOptionsPO config;
}
