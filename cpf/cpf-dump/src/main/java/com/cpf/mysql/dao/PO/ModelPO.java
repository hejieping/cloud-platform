package com.cpf.mysql.dao.PO;

import lombok.Data;

import javax.persistence.*;

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
    private Long weight = 0L;
    /**
     * 模型参数配置
     */
    @OneToOne(cascade = CascadeType.ALL)
    private ModelOptionsPO config;
}
