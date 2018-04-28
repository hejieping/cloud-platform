package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.ModelOptionsPO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jieping
 * @create 2018-04-25 19:56
 **/
public interface ModelOptionsDAO extends CrudRepository<ModelOptionsPO, Long> {
    /**
     * 保存对象
     * @param modelOptionsPO
     * @return 返回保存后的对象，如果为新增，则返回对象带有主键id
     */
    @Override
    ModelOptionsPO save(ModelOptionsPO modelOptionsPO);
    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    ModelOptionsPO getById(Long id);
}
