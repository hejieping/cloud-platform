package com.cpf.mysql.dao.PO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
/**
 * @author jieping
 * @create 2018-04-07
 * @desc 各个模型的参数选项
 **/
@Data
@Entity(name = "model_options")
public class ModelOptionsPO {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 模型类型
     */
    private String modelType;
    /**
     * 模型的参数（json格式）
     */
    @Lob
    String options;
}
