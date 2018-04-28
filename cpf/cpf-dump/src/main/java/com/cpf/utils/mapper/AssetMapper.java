package com.cpf.utils.mapper;

import com.cpf.mysql.dao.PO.AssetPO;
import com.cpf.mysql.manager.DO.AssetDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * @author jieping
 * @create 2018-04-22
 * @desc assert 对象do po互转
 **/
@Mapper
public interface AssetMapper {
    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);

    /**
     * po映射成do
     * @param assetPO
     * @return
     */
    AssetDO assetPO2DO(AssetPO assetPO);

    /**
     * pos映射成dos
     * @param assetPOS
     * @return
     */
    List<AssetDO> assetPOS2DOS(List<AssetPO> assetPOS);
}
