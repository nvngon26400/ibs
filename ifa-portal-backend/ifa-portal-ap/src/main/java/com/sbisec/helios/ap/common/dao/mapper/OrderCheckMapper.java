package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderCheckMapper {
	public Integer getDepositTypeCount(@Param("customerId") String customerId, @Param("depositType") String depositType)
			throws Exception;
	public Integer getNisaCanBuyCount(@Param("customerId") String customerId) throws Exception;

//	public Integer getUnreadMsgCnt(@Param("butenCode") String butenCode,
//			@Param("accountNumber") Integer accountNumber) throws Exception;
//
//	public Integer getFsAcctCountByAcctId(@Param("acctId") String acctId) throws Exception;
	
	/** 口座のカテゴリー */
	public List<String> getCategoryCdByCustomerId(@Param("customerId") String customerId) throws Exception;
}
