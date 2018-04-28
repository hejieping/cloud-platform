package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.AssetPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * @author jieping
 * @create 2018-04-25 19:56
 **/
public interface AssetDAO extends CrudRepository<AssetPO, String> {
    /**
     * 查询所有对象
     * @return
     */
    @Override
    List<AssetPO> findAll();
}
