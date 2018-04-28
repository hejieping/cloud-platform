package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.AlarmPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * @author jieping
 * @create 2018-04-25 19:56
 **/
public interface AlarmDAO extends CrudRepository<AlarmPO, Long> {
    /**
     * 保存对象
     * @param alarmPO
     * @return 返回保存后的对象，如果为新增，则返回对象带有主键id
     */
    @Override
    AlarmPO save(AlarmPO alarmPO);
    /**
     * 查询所有对象
     * @return
     */
    @Override
    List<AlarmPO> findAll();
}
