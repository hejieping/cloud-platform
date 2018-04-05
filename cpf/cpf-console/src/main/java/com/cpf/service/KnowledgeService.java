package com.cpf.service;

import com.cpf.knowledgebase.dao.KnowledgeRuleDAO;
import com.cpf.knowledgebase.dao.PO.KnowledgeRulePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jieping on 2018-04-05
 */
@Service
public class KnowledgeService {
    @Autowired
    private KnowledgeRuleDAO knowledgeRuleDAO;
    public Boolean save(KnowledgeRulePO knowledgeRulePO){
        KnowledgeRulePO result =  knowledgeRuleDAO.save(knowledgeRulePO);
        if(result != null){
            return true;
        }
        return false;
    }
}
