package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:取引内容
 * @author xiu.yan
 * @date: 01/15/2022
 */
public enum TradeLimit {
	FOREIGN_STOCK_TRADE("FOREIGN_STOCK_TRADE", "外国株式取引"), BUY_EXCHANGE_TRADE("BUY_EXCHANGE_TRADE", "為替取引買い"),
	SELL_EXCHANGE_TRADE("SELL_EXCHANGE_TRADE", "為替取引売り");

	TradeLimit(String id, String name) {
		this.id = id;
		this.name = name;
	}

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static TradeLimit getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		TradeLimit[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
