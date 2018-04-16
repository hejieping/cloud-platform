package com.cpf.agentbase.dao.PO;

import lombok.Data;

/**
 * Created by jieping on 2018-04-16
 */
@Data
public class MemPO {
    private Long time;
    private String host;
    private String objectName;
    private Long availableBytes;
    private Double cacheFaultsPersec;
    private Double demandZeroFaultsPersec;
    private Double pageFaultsPersec;
    private Double pagesPersec;
    private Long poolNonpagedBytes;
    private Long poolPagedBytes;
    private Long standbyCacheCoreBytes;
    private Long standbyCacheNormalPriorityBytes;
    private Long standbyCacheReserveBytes;
    private Double transitionFaultsPersec;
}
