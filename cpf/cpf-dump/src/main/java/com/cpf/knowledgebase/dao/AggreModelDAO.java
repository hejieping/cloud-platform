package com.cpf.knowledgebase.dao;

import com.cpf.knowledgebase.dao.PO.AggreModelPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AggreModelDAO extends CrudRepository<AggreModelPO, Long> {
    AggreModelPO save(AggreModelPO aggreModelPO);
    List<AggreModelPO> findAll();
    void deleteById(Long id);
}
