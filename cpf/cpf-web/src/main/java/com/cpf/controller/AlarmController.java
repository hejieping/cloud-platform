package com.cpf.controller;

import com.cpf.VO.AlarmVO;
import com.cpf.mysql.manager.AlarmManager;
import com.cpf.mysql.manager.DO.AlarmDO;
import com.cpf.service.CallbackResult;
import com.cpf.utils.VODOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jieping
 * @create 2018-04-28 15:25
 * @desc 报警
 **/
@RestController
public class AlarmController {
    @Autowired
    private AlarmManager alarmManager;
    @RequestMapping(value = "/alarms", method = RequestMethod.GET)
    ResponseEntity<Object> modelType(){
        CallbackResult<List<AlarmDO>> result = alarmManager.all();
        if(result.getSuccess()){
            CallbackResult<List<AlarmVO>> VoResult = new CallbackResult<>(VODOConverter.alarmDOS2VOS(result.getResult()),true);
            return  new ResponseEntity<Object>(VoResult,HttpStatus.OK);

        }
        return  new ResponseEntity<Object>(result,HttpStatus.OK);
    }
}
