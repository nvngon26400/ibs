package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.MediateBranch;

import org.springframework.stereotype.Component;

@Component
public interface MediateBranchDao {

	public MediateBranch getMediateBranch(String brokerCode, String brokerBranchCode) throws Exception;
	// public DataList<OfferInfoModel> getOfferInfoList(String brokerBranchCode) throws Exception;

}
