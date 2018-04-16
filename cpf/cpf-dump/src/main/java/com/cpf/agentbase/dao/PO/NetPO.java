package com.cpf.agentbase.dao.PO;

import lombok.Data;

/**
 * Created by jieping on 2018-04-16
 */
@Data
public class NetPO {
    private Long time;
    private String host;
    private String instance;
    private String objectName;
    private Double bytesReceivedPersec;
    private Double bytesSentPersec;
    private Long packetsOutboundDiscarded;
    private Long packetsOutboundErrors;
    private Long packetsReceivedDiscarded;
    private Long packetsReceivedErrors;
    private Double packetsReceivedPersec;
    private Double packetsSentPersec;

}
