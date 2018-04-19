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
    private String objectname;
    @Column(name = "Available_Bytes")
    private Long Available_Bytes;
    @Column(name = "Cache_Faults_persec")
    private Double Cache_Faults_persec;
    @Column(name = "Demand_Zero_Faults_persec")
    private Double Demand_Zero_Faults_persec;
    @Column(name = "Page_Faults_persec")
    private Double Page_Faults_persec;
    @Column(name = "Pages_persec")
    private Double Pages_persec;
    @Column(name = "Pool_Nonpaged_Bytes")
    private Long Pool_Nonpaged_Bytes;
    @Column(name = "Pool_Paged_Bytes")
    private Long Pool_Paged_Bytes;
    @Column(name = "Standby_Cache_Core_Bytes")
    private Long Standby_Cache_Core_Bytes;
    @Column(name = "Standby_Cache_Normal_Priority_Bytes")
    private Long Standby_Cache_Normal_Priority_Bytes;
    @Column(name = "Standby_Cache_Reserve_Bytes")
    private Long Standby_Cache_Reserve_Bytes;
    @Column(name = "Transition_Faults_persec")
    private Double Transition_Faults_persec;
}
