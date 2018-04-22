package com.cpf.influx.dao.PO;

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
    private String objectname;
    @Column(name = "Bytes_Received_persec")
    private Double bytesReceivedPersec;
    @Column(name = "Bytes_Sent_persec")
    private Double Bytes_Received_persec;
    @Column(name = "Packets_Outbound_Discarded")
    private Long Packets_Outbound_Discarded;
    @Column(name = "Packets_Outbound_Errors")
    private Long Packets_Outbound_Errors;
    @Column(name = "Packets_Received_Discarded")
    private Long Packets_Received_Discarded;
    @Column(name = "Packets_Received_Errors")
    private Long Packets_Received_Errors;
    @Column(name = "Packets_Received_persec")
    private Double Packets_Received_persec;
    @Column(name = "Packets_Sent_persec")
    private Double Packets_Sent_persec;
}
