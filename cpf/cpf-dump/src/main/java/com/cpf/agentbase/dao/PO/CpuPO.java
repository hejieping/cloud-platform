package com.cpf.agentbase.dao.PO;

import lombok.Data;

/**
 * Created by jieping on 2018-04-16
 */
@Data
public class CpuPO {
    private Long time;
    private Double percentDPCTime;
    private Double percentIdleTime;
    private Double percentInterruptTime;
    private Double percentPrivilegedTime;
    private Double percentProcessorTime;
    private Double percentUserTime;
    private String host;
    private String instance;
    private String objectName;

}
