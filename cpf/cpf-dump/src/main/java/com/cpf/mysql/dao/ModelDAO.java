package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.ModelPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * @author jieping
 * @create 2018-04-25 19:56
 **/
public interface ModelDAO extends CrudRepository<ModelPO, Long> {
    /**
     * 保存对象
     * @param modelPO
     * @return 返回保存后的对象，如果为新增，则返回对象带有主键id
     */
    @Override
    ModelPO save(ModelPO modelPO);

    /**
     * 查询所有对象
     * @return
     */
    @Override
    List<ModelPO> findAll();

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    ModelPO getById(Long id);
    /**
     * 根据id删除对象
     * @param id
     */
    @Override
    void deleteById(Long id);
}
