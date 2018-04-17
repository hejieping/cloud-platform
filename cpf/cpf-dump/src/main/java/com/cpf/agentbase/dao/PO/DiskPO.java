package com.cpf.agentbase.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "win_disk")
public class DiskPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "Current_Disk_Queue_Length")
    private Integer currentDiskQueueLength;
    @Column(name = "time")
    private Long Free_Megabytes;
    @Column(name = "Percent_Disk_Read_Time")
    private Double percentDiskReadTime;
    @Column(name = "Percent_Disk_Time")
    private Double percentDiskTime;
    @Column(name = "Percent_Disk_Write_Time")
    private Double percentDiskWriteTime;
    @Column(name = "Percent_Free_Space")
    private Double percentFreeSpace;
    @Column(name = "Percent_Idle_Time")
    private Double percentIdleTime;
    @Column(name = "host")
    private String host;
    @Column(name = "instance")
    private String instance;
    @Column(name = "objectname")
    private String objectName;
}
