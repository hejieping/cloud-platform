package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.AggreModelPO;
import com.cpf.mysql.dao.PO.ModelPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * @author jieping
 * @create 2018-04-25 19:56
 **/
public interface AggreModelDAO extends CrudRepository<AggreModelPO, Long> {
    /**
     * 保存对象
     * @param aggreModelPO
     * @return 返回保存后的对象，如果为新增，则返回对象带有主键id
     */
    @Override
    AggreModelPO save(AggreModelPO aggreModelPO);

    /**
     * 查询所有对象
     * @return
     */
    @Override
    List<AggreModelPO> findAll();

    /**
     * 根据id删除对象
     * @param id
     */
    @Override
    void deleteById(Long id);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    AggreModelPO getById(Long id);

    /**
     * 根据model查询对象
     * @param modelPO
     * @return
     */
    AggreModelPO findByModelsIsContaining(ModelPO modelPO);
}
