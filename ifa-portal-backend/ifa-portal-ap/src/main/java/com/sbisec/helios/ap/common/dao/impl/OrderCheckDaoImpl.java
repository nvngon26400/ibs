package com.sbisec.helios.ap.common.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.OrderCheckDao;
import com.sbisec.helios.ap.common.dao.mapper.OrderCheckMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderCheckDaoImpl implements OrderCheckDao {
	private static final Logger logger = LoggerFactory.getLogger(OrderCheckDaoImpl.class);
	@Autowired
	private OrderCheckMapper mapper;

	@Override
	public Integer getDepositTypeCount(String customerId, String depositType) throws Exception {
		logger.debug("OrderCheckDaoImpl.getDepositTypeCount: customerId：" + customerId + ",depositType：" + depositType);
		return getDepositTypeCountInternal(customerId, depositType);
	}

	private Integer getDepositTypeCountInternal(String customerId, String depositType) throws Exception {
		return mapper.getDepositTypeCount(customerId, depositType);
	}

//	/**
//	 * 未読の重要なお知らせデータ存在件数を取得処理.
//	 * 
//	 * @param butenCode     部店コード
//	 * @param accountNumber 口座番号
//	 * @return 未読の重要なお知らせデータ存在件数
//	 * @throws Exception 異常
//	 */
//	public Integer checkUnreadMsg(String butenCode, Integer accountNumber) throws Exception {
//		logger.debug("OrderCheckDaoImpl.checkUnreadMsg: butenCode：" + butenCode + ",accountNumber：" + accountNumber);
//
//		return checkUnreadMsgInternal(butenCode, accountNumber);
//	}
//
//	private Integer checkUnreadMsgInternal(String butenCode, Integer accountNumber) throws Exception {
//		logger.debug(
//				"OrderCheckDaoImpl.checkUnreadMsgInternal: butenCode：" + butenCode + ",accountNumber：" + accountNumber);
//
//		// 未読の重要なお知らせデータ存在件数を取得する
//		Integer sqlResult = mapper.getUnreadMsgCnt(butenCode, accountNumber);
//
//		return sqlResult;
//	}

//	public Integer getFsAcctCountByAcctId(String acctId) throws Exception {
//		if (logger.isDebugEnabled()) {
//			logger.debug("OrderCheckDaoImpl.getFsAcctCountByAcctId: acctId：", acctId);
//		}
//
//		return getFsAcctCountByAcctIdInternal(acctId);
//	}
//	
//	private Integer getFsAcctCountByAcctIdInternal(String acctId) throws Exception {
//		if (logger.isDebugEnabled()) {
//			logger.debug("OrderCheckDaoImpl.getFsAcctCountByAcctIdInternal: acctId：", acctId);
//		}
//
//		return mapper.getFsAcctCountByAcctId(acctId);
//	}
	
	/** 口座のカテゴリー */
	@Override
	public List<String> getCategoryCdByCustomerId(String customerId) throws Exception {
		logger.debug(
				"OrderCheckDaoImpl.getCategoryCdByCustomerId:customerId "+customerId);
		return getCategoryCdByCustomerIdInternal(customerId);
	}
	
	private List<String> getCategoryCdByCustomerIdInternal(String customerId) throws Exception {
		logger.debug(
				"OrderCheckDaoImpl.getCategoryCdByCustomerIdInternal:customerId "+customerId);
		List<String> categoryList = mapper.getCategoryCdByCustomerId(customerId);

		return categoryList;

	}

	@Override
	public Integer getNisaCanBuyCount(String customerId) throws Exception {
		logger.debug("OrderCheckDaoImpl.getNisaCanBuyCount: customerId：" + customerId);
		return getNisaCanBuyCountInternal(customerId);
	}
	private Integer getNisaCanBuyCountInternal(String customerId) throws Exception {
		return mapper.getNisaCanBuyCount(customerId);
	}
}
