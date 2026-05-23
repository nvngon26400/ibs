package com.sbisec.helios.ap.common.dao;

import java.util.List;


public interface OrderCheckDao {
	public Integer getDepositTypeCount(String customerId, String depositType) throws Exception;

	public Integer getNisaCanBuyCount(String customerId) throws Exception;

	// public Integer checkUnreadMsg(String butenCode, Integer accountNumber) throws Exception;

	// public Integer getFsAcctCountByAcctId(String acctId) throws Exception;
	
	/** 口座のカテゴリー */
	public List<String> getCategoryCdByCustomerId(String customerId) throws Exception;
}
