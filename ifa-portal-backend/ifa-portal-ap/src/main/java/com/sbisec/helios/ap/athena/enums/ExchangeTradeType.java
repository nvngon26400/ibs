package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:為替取引
 * @author 松田
 */
public enum ExchangeTradeType {
    REAL("REAL", "リアルタイム為替"),
    REGULAR("REGULAR", "定時為替")
	;
    

	ExchangeTradeType(String id, String name) {
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

	public static ExchangeTradeType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ExchangeTradeType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

}
