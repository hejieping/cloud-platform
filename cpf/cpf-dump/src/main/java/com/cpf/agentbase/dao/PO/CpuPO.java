package com.cpf.agentbase.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "win_cpu")
public class CpuPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "Percent_DPC_Time")
    private Double percentDPCTime;
    @Column(name = "Percent_Idle_Time")
    private Double percentIdleTime;
    @Column(name = "Percent_Interrupt_Time")
    private Double percentInterruptTime;
    @Column(name = "Percent_Privileged_Time")
    private Double percentPrivilegedTime;
    @Column(name = "Percent_Processor_Time")
    private Double percentProcessorTime;
    @Column(name = "Percent_User_Time")
    private Double percentUserTime;
    @Column(name = "host",tag = true)
    private String host;
    @Column(name = "instance",tag = true)
    private String instance;
    @Column(name = "objectname",tag = true)
    private String objectName;
}
