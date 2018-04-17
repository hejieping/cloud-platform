package com.cpf.agentbase.dao.PO;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by jieping on 2018-04-16
 */
@Data
@Measurement(name = "win_mem")
public class MemPO {
    @Column(name = "time")
    private Long time;
    @Column(name = "host")
    private String host;
    @Column(name = "objectname")
    private String objectName;
    @Column(name = "Available_Bytes")
    private Long availableBytes;
    @Column(name = "Cache_Faults_persec")
    private Double cacheFaultsPersec;
    @Column(name = "Demand_Zero_Faults_persec")
    private Double demandZeroFaultsPersec;
    @Column(name = "Page_Faults_persec")
    private Double pageFaultsPersec;
    @Column(name = "Pages_persec")
    private Double pagesPersec;
    @Column(name = "Pool_Nonpaged_Bytes")
    private Long poolNonpagedBytes;
    @Column(name = "Pool_Paged_Bytes")
    private Long poolPagedBytes;
    @Column(name = "Standby_Cache_Core_Bytes")
    private Long standbyCacheCoreBytes;
    @Column(name = "Standby_Cache_Normal_Priority_Bytes")
    private Long standbyCacheNormalPriorityBytes;
    @Column(name = "Standby_Cache_Reserve_Bytes")
    private Long standbyCacheReserveBytes;
    @Column(name = "Transition_Faults_persec")
    private Double transitionFaultsPersec;
}
