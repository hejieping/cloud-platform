package com.cpf.knowledgebase.dao;

import com.cpf.knowledgebase.dao.PO.ModelOptionsPO;
import org.springframework.data.repository.CrudRepository;

public interface ModelOptionsDAO extends CrudRepository<ModelOptionsPO, Long> {
    ModelOptionsPO save(ModelOptionsPO modelOptionsPO);
    ModelOptionsPO findByModelType(String modelType);
}
