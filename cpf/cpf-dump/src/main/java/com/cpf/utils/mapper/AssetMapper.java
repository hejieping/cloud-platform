package com.cpf.utils.mapper;

import com.cpf.mysql.dao.PO.AssetPO;
import com.cpf.mysql.manager.DO.AssetDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by jieping on 2018-04-22
 */
@Mapper
public interface AssetMapper {
    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);
    AssetDO assetPO2DO(AssetPO assetPO);
    List<AssetDO> assetPOS2DOS(List<AssetPO> assetPOS);
}
