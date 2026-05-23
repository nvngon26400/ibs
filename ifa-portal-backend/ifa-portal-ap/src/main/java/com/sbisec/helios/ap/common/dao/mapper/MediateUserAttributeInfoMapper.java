package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.model.MediateUserAttributeInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MediateUserAttributeInfoMapper {

	public MediateUserAttributeInfo getMediateUserAttributeInfo(@Param("butenCode") String butenCode, @Param("accountNumber") String accountNumber) throws Exception;

}
