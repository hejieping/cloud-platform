package com.cpf.mysql.manager.DO;

import com.cpf.constants.AlarmTypeEnum;
import com.cpf.influx.manager.DO.MonitorDO;
import lombok.Data;

import java.util.Date;

/**
 * @author jieping
 * @create 2018-04-28 11:01
 * @desc 报警信息
 **/
@Data
public class AlarmDO {
    private Long id;
    private AlarmTypeEnum type;
    private MonitorDO monitorDO;
    private RuleDO ruleDO;
    private Date time;
}
