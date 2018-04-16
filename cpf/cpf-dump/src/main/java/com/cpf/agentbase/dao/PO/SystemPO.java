package com.cpf.agentbase.dao.PO;

import lombok.Data;

/**
 * Created by jieping on 2018-04-16
 */
@Data
public class SystemPO {
    private Long time;
    private String host;
    private String objectName;
    private Double contextSwitchesPersec;
    private Integer processorQueueLength;
    private Double systemCallsPersec;
    private Double systemUpTime;
}
