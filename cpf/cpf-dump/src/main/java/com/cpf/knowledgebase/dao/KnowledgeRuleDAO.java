package com.cpf.knowledgebase.dao;

import com.cpf.knowledgebase.dao.PO.KnowledgeRulePO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2018-04-05
 */
public interface KnowledgeRuleDAO extends CrudRepository<KnowledgeRulePO, Long> {
    KnowledgeRulePO save(KnowledgeRulePO knowledgeRulePO);
    List<KnowledgeRulePO> findAllById(Long Id);
}
