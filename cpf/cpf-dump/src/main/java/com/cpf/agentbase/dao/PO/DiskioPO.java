package com.cpf.agentbase.dao.PO;

import lombok.Data;

/**
 * Created by jieping on 2018-04-16
 */
@Data
public class DiskioPO {
    private Long time;
    private String host;
    private String instance;
    private String objectName;
    private Double durrentDiskQueueLength;
    private Double diskReadBytesPersec;
    private Double diskReadsPersec;
    private Double diskWriteBytesPersec;
    private Double diskWritesPersec;
    private Double percentDiskReadTime;
    private Double percentDiskTime;
    private Double percentDiskWriteTime;
}
