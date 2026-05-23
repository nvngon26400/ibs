package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 取引種別
 * 
 * @author yunhui.zhao
 * @date: 03/03/2022
 */
public enum TradeRecordType {
	TRADE_BUY("TRADE_BUY", "現物買", null, "現物買"),
	TRADE_SELL("TRADE_SELL", "現物売", null, "現物売"),
	MARGIN_TRADE_BUY("MARGIN_TRADE_BUY", "信用新規買", null, "新規買"),
	MARGIN_TRADE_SELL("MARGIN_TRADE_SELL", "信用新規売", null, "新規売"),
	MARGIN_REPAYMENT_SELL("MARGIN_REPAYMENT_SELL", "信用返済売", "1", "返済売"),
	MARGIN_REPAYMENT_BUY("MARGIN_REPAYMENT_BUY", "信用返済買", "3", "返済買"),
	RESERVE_STOCK_ORDER("RESERVE_STOCK_ORDER", "定期", null, "定期");

	TradeRecordType(String id, String name,String ifaCd, String ifaName) {
		this.id = id;
		this.name = name;
		this.ifaCd = ifaCd;
		this.ifaName = ifaName;

	}

	private String id;
	private String name;
	private String ifaCd;
	private String ifaName;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIfaCd() {
		return ifaCd;
	}

	public String getIfaName() {
		return ifaName;
	}

	public static TradeRecordType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		TradeRecordType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}