package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.model.MediateChargeInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MediateChargeInfoMapper {

	public MediateChargeInfo getMediateChargeInfo(@Param("brokerCode") String brokerCode
												, @Param("brokerBranchCode") String brokerBranchCode
												, @Param("brokerChargeCode") String brokerChargeCode) throws Exception;

}
