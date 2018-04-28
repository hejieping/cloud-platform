package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.AlarmPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlarmDAO extends CrudRepository<AlarmPO, Long> {
    AlarmPO save(AlarmPO alarmPO);
    List<AlarmPO> findAll();
}
