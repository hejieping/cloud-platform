package com.cpf.agentbase.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "perf_counters")
public class PerfCountersPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "host")
    private String host;
    @Column(name = "instance")
    private String instance;
    @Column(name = "objectname")
    private String objectName;
    @Column(name = "Bytes_Received_persec")
    private Double bytesReceivedPersec;
    @Column(name = "Bytes_Sent_persec")
    private Double bytesSentPersec;
    @Column(name = "Packets_Outbound_Discarded")
    private Long packetsOutboundDiscarded;
    @Column(name = "Packets_Outbound_Errors")
    private Long packetsOutboundErrors;
    @Column(name = "Packets_Received_Discarded")
    private Long packetsReceivedDiscarded;
    @Column(name = "Packets_Received_Errors")
    private Long packetsReceivedErrors;
    @Column(name = "Packets_Received_persec")
    private Double packetsReceivedPersec;
    @Column(name = "Packets_Sent_persec")
    private Double packetsSentPersec;
}
