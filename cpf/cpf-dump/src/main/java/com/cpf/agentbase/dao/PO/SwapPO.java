package com.cpf.agentbase.dao.PO;

import lombok.Data;

/**
 * Created by jieping on 2018-04-16
 */
@Data
public class SwapPO {
    private Long time;
    private String host;
    private String instance;
    private String objectName;
    private Double percentUsage;
}
