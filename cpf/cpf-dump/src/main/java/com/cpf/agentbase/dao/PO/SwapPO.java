package com.cpf.agentbase.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "win_swap")
public class SwapPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "host")
    private String host;
    @Column(name = "instance")
    private String instance;
    @Column(name = "objectname")
    private String objectName;
    @Column(name = "Percent_Usage")
    private Double percentUsage;
}
