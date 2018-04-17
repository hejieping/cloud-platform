package com.cpf.agentbase.dao.PO;

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
    private String objectName;
    @Column(name = "Current_Disk_Queue_Length")
    private Double currentDiskQueueLength;
    @Column(name = "Disk_Read_Bytes_persec")
    private Double diskReadBytesPersec;
    @Column(name = "Disk_Reads_persec")
    private Double diskReadsPersec;
    @Column(name = "Disk_Write_Bytes_persec")
    private Double diskWriteBytesPersec;
    @Column(name = "Disk_Writes_persec")
    private Double diskWritesPersec;
    @Column(name = "Percent_Disk_Read_Time")
    private Double percentDiskReadTime;
    @Column(name = "Percent_Disk_Time")
    private Double percentDiskTime;
    @Column(name = "Percent_Disk_Write_Time")
    private Double percentDiskWriteTime;
}
