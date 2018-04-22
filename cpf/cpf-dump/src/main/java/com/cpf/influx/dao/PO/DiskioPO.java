package com.cpf.influx.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "win_diskio")
public class DiskioPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "host")
    private String host;
    @Column(name = "instance")
    private String instance;
    @Column(name = "objectname")
    private String objectname;
    @Column(name = "Current_Disk_Queue_Length")
    private Double Current_Disk_Queue_Length;
    @Column(name = "Disk_Read_Bytes_persec")
    private Double Disk_Read_Bytes_persec;
    @Column(name = "Disk_Reads_persec")
    private Double Disk_Reads_persec;
    @Column(name = "Disk_Write_Bytes_persec")
    private Double Disk_Write_Bytes_persec;
    @Column(name = "Disk_Writes_persec")
    private Double Disk_Writes_persec;
    @Column(name = "Percent_Disk_Read_Time")
    private Double Percent_Disk_Read_Time;
    @Column(name = "Percent_Disk_Time")
    private Double Percent_Disk_Time;
    @Column(name = "Percent_Disk_Write_Time")
    private Double Percent_Disk_Write_Time;
}
