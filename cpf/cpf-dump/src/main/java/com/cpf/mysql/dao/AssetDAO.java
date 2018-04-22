package com.cpf.mysql.dao;

import com.cpf.mysql.dao.PO.AssetPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetDAO extends CrudRepository<AssetPO, String> {
    List<AssetPO> findAll();
}
