package com.cpf.influx.dao.PO;

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
    private Double Percent_DPC_Time;
    @Column(name = "Percent_Idle_Time")
    private Double Percent_Idle_Time;
    @Column(name = "Percent_Interrupt_Time")
    private Double Percent_Interrupt_Time;
    @Column(name = "Percent_Privileged_Time")
    private Double Percent_Privileged_Time;
    @Column(name = "Percent_Processor_Time")
    private Double Percent_Processor_Time;
    @Column(name = "Percent_User_Time")
    private Double Percent_User_Time;
    @Column(name = "host",tag = true)
    private String host;
    @Column(name = "instance",tag = true)
    private String instance;
    @Column(name = "objectname",tag = true)
    private String objectname;
}
