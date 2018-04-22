package com.cpf.influx.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.util.Date;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "win_system")
public class SystemPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "host")
    private String host;
    @Column(name = "objectname")
    private String objectName;
    @Column(name = "Context_Switches_persec")
    private Double Context_Switches_persec;
    @Column(name = "Processor_Queue_Length")
    private Integer Processor_Queue_Length;
    @Column(name = "System_Calls_persec")
    private Double System_Calls_persec;
    @Column(name = "System_Up_Time")
    private Double System_Up_Time;
    public static void main(String[] args){
        Date date = new Date();
        System.out.println(date.toInstant());
    }
}
