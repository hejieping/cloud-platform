package com.cpf.mysql.dao.PO;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author jieping
 * @create 2018-04-08
 * @desc 聚合模型，包括多个算法模型
 **/
@Data
@Entity(name = "aggre_model")
public class AggreModelPO {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 使用场景
     */
    private String scene;
    /**
     * 模型集合
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ModelPO> models;
}
