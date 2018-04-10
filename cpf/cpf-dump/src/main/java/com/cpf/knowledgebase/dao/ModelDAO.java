package com.cpf.knowledgebase.dao;

import com.cpf.knowledgebase.dao.PO.ModelPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelDAO extends CrudRepository<ModelPO, Long> {
    ModelPO save(ModelPO modelPO);
    List<ModelPO> findAll();
    ModelPO getById(Long id);
}
