package com.cpf.knowledgebase.dao.PO;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 聚合模型，包括多个算法模型
 * Created by jieping on 2018-04-08
 */
@Data
@Entity(name = "aggre_model")
public class AggreModelPO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String scene;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ModelPO> models;
}
