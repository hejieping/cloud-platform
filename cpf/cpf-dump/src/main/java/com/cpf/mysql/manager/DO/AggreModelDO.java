package com.cpf.mysql.manager.DO;

import com.cpf.utils.BeanUtil;
import com.cpf.utils.ValidationUtil;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * @author jieping
 * @create 2018-04-12
 **/
@ToString
public class AggreModelDO {
    @Getter@Setter
    private Long id;
    /**
     * 使用场景
     */
    @Getter@Setter
    private String scene;
    /**
     * 模型集合
     */
    private List<ModelDO> models = Lists.newArrayList();
    public List<ModelDO> getModels(){
        List<ModelDO> copyList = Lists.newArrayList();
        models.forEach(modelDO -> copyList.add(BeanUtil.copy(modelDO)));
        return Collections.unmodifiableList(copyList);
    }
    public void addModels(List<ModelDO> models){
        if(CollectionUtils.isNotEmpty(models)){
            this.models.addAll(models);
        }
    }
    public void addModel(@NotNull ModelDO modelDO){
        if(ValidationUtil.isNotNull(modelDO)){
            models.add(modelDO);
        }
    }

}
