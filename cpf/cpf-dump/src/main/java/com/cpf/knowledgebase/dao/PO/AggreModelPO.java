package com.cpf.knowledgebase.dao.PO;

import javax.persistence.*;
import java.util.List;

/**
 * 聚合模型，包括多个算法模型
 * Created by jieping on 2018-04-08
 */
public class AggreModelPO {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ModelPO> models;
}
