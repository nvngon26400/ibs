package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 株取引区分
 * 
 * @author shuchen.xin
 * @date: 02/07/2022
 */
public enum StockTradeType {
	// 0.現物 10.信用新規 11.信用返済
	STOCK("STOCK", "現物", "0"),
	MARGIN_OPEN("MARGIN_OPEN", "信用新規", "10"),
	MARGIN_CLOSE("MARGIN_CLOSE", "信用返済", "11");

	StockTradeType(String id, String name, String ifaCd) {
		this.id = id;
		this.name = name;
		this.ifaCd = ifaCd;
	}

	private String id;
	private String name;
	private String ifaCd;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIfaCd() {
		return ifaCd;
	}

	public static StockTradeType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		StockTradeType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
