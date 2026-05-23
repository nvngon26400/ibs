package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:通貨ペア
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum CurrencyPair {
	USDJPY("USDJPY", "USD → JPY"), JPYUSD("JPYUSD", "JPY → USD");

	CurrencyPair(String id, String name) {
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

	public static CurrencyPair getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		CurrencyPair[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
