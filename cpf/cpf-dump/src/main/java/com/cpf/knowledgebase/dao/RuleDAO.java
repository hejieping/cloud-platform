package com.cpf.knowledgebase.dao;

import com.cpf.knowledgebase.dao.PO.RulePO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2018-04-05
 */
public interface RuleDAO extends CrudRepository<RulePO, Long> {
    RulePO save(RulePO rulePO);
    RulePO getById(Long id);
    List<RulePO> findAll();
}
