package com.cpf.agentbase.dao.PO;

import lombok.Data;

/**
 * Created by jieping on 2018-04-16
 */
@Data
public class DiskPO {
    private Long time;
    private Integer currentDiskQueueLength;
    private Long freeMegabytes;
    private Double percentDiskReadTime;
    private Double percentDiskTime;
    private Double percentDiskWriteTime;
    private Double percentFreeSpace;
    private Double percentIdleTime;
    private String host;
    private String instance;
    private String objectname;
}
