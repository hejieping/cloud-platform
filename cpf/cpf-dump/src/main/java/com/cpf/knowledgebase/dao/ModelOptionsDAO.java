package com.cpf.knowledgebase.dao;

import com.cpf.knowledgebase.dao.PO.ModelOptionsPO;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2018-04-09
 */
public interface ModelOptionsDAO extends CrudRepository<ModelOptionsPO, Long> {
    ModelOptionsPO save(ModelOptionsPO modelOptionsPO);
}
