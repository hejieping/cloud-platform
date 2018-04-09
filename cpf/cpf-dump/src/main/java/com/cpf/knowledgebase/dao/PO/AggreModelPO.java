package com.cpf.knowledgebase.dao.PO;

import lombok.Data;

import java.util.List;

/**
 * 聚合模型，包括多个算法模型
 * Created by jieping on 2018-04-08
 */
@Data
public class AggreModelPO {
    private Long id;
    private List<ModelPO> models;
}
