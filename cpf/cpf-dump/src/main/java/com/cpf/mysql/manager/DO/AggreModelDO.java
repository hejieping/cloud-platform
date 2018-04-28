package com.cpf.mysql.manager.DO;

import lombok.Data;

import java.util.List;
/**
 * @author jieping
 * @create 2018-04-12
 **/
@Data
public class AggreModelDO {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 使用场景
     */
    private String scene;
    /**
     * 模型集合
     */
    private List<ModelDO> models;
}
