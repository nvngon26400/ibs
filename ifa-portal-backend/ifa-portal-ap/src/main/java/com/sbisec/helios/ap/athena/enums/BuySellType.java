package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 建区分
 * 
 * @author yunhui.zhao
 * @date: 23/03/2021
 */
public enum BuySellType {
	SELL("SELL", "売建"), BUY("BUY", "買建");

	BuySellType(String id, String name) {
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

	public static BuySellType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		BuySellType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
