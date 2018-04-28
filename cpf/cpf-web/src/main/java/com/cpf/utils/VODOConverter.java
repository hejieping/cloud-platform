package com.cpf.utils;

import com.alibaba.fastjson.JSON;
import com.cpf.VO.AlarmVO;
import com.cpf.mysql.manager.DO.AlarmDO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author jieping
 * @create 2018-04-28 15:39
 * @desc VO DO转换器
 **/
public class VODOConverter {
    public static AlarmVO alarmDO2VO(AlarmDO alarmDO){
        AlarmVO alarmVO = new AlarmVO();
        alarmVO.setId(alarmDO.getId());
        alarmVO.setAlarmType(alarmDO.getType().getDesc());
        alarmVO.setRuleName(alarmDO.getRuleDO().getName());
        alarmVO.setMonitorType(alarmDO.getMonitorDO().getType());
        alarmVO.setTime(DateUtil.format(alarmDO.getTime()));
        alarmVO.setMonitorData(JSON.toJSONString(alarmDO.getMonitorDO().getData()));

        return alarmVO;
    }
    public static List<AlarmVO> alarmDOS2VOS(List<AlarmDO> alarmDOList){
        List<AlarmVO> list = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(alarmDOList)){
            for(AlarmDO alarmDO : alarmDOList){
                list.add(alarmDO2VO(alarmDO));
            }
        }
        return list;
    }
}
