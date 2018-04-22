package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.AggreModelPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AggreModelDAO extends CrudRepository<AggreModelPO, Long> {
    AggreModelPO save(AggreModelPO aggreModelPO);
    List<AggreModelPO> findAll();
    void deleteById(Long id);
    AggreModelPO getById(Long id);
}
