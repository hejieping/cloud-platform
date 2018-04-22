package com.cpf.mysql.manager.DO;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by jieping on 2018-04-12
 */
@Data
public class AggreModelDO {
    private Long id;
    private String name;
    private String scene;
    private List<ModelDO> models = Lists.newArrayList();
}
