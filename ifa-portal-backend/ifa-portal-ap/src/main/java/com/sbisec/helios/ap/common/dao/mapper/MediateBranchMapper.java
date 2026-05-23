package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.model.MediateBranch;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// import com.sbibits.horus.ap.counter.model.OfferInfoModel;
@Mapper
public interface MediateBranchMapper {

	public MediateBranch getMediateBranch(@Param("brokerCode") String brokerCode,
			@Param("brokerBranchCode") String brokerBranchCode) throws Exception;

	/**
	 * 店頭取引銘柄マスタ 提供業者
	 *
	 * @param brokerBranchCode 仲介業者支店種別
	 * @return
	 * @throws Exception
	 */
// 	public List<OfferInfoModel> getOfferInfoList(@Param("brokerBranchCode") String brokerBranchCode) throws Exception;

//	public Integer getBrokerCount(@Param("brokerCode") String brokerCode,
//			@Param("brokerBranchCode") String brokerBranchCode) throws Exception;
}
