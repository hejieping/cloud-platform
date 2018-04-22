package com.cpf.influx.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "win_system_days")
public class SystemDaysPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "count")
    private Integer count;
    @Column(name = "host")
    private String host;
}
