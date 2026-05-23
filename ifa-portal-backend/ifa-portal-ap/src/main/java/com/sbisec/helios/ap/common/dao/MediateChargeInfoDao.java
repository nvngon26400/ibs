package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.MediateChargeInfo;

import org.springframework.stereotype.Component;

@Component
public interface MediateChargeInfoDao {

	public MediateChargeInfo getMediateChargeInfo(String brokerCode
												, String brokerBranchCode
												, String brokerChargeCode) throws Exception;

}
