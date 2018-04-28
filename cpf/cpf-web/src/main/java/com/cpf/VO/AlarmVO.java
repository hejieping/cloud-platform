package com.cpf.VO;

import lombok.Data;

/**
 * @author jieping
 * @create 2018-04-28 15:35
 * @desc 报警信息前端展示对象
 **/
@Data
public class AlarmVO {
    private Long id;
    private String alarmType;
    private String monitorType;
    private String ruleName;
    private String monitorData;
    private String time;
}
