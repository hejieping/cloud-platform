package com.cpf.knowledgebase.dao.PO;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jieping on 2018-04-05
 */
@Data
@Entity(name = "rule")
public class RulePO {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 监控规则名称
     */
    private String name;
    /**
     * 监控类型
     */
    private String type;
    /**
     * 规则配置
     */
    @Lob
    private String config;
    /**
     * 持续时间
     */
    private Long time;
    /**
     * 规则创建时间
     */
    @Column(name = "create_time")
    private Date createTime = new Date();
    /**
     * 规则修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;
}
