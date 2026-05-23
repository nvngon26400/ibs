package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

public enum SecuritiesType {

	STOCK("STOCK", "個別株式"), ETF("ETF", "ETF"), ADR("ADR", "ADR（米国の場合、ADR香港の場合、HDR韓国の場合、KDR"),
	RED_CHIP("RED_CHIP", "レッドチップ"), H_SHARES("H_SHARES", "H株");

	SecuritiesType(String id, String name) {
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

	public static SecuritiesType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		SecuritiesType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

}
