package com.cpf.knowledgebase.dao.PO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by jieping on 2018-04-05
 */
@Data
@Entity(name = "knowledge_rule")
public class KnowledgeRulePO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "create_time")
    private Date createTime = new Date();
    @Column(name = "modify_time")
    private Date modifyTime;
}
