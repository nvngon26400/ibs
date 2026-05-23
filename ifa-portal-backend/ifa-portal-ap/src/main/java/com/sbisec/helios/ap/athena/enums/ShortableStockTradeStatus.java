package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * @Description 銘柄信用売建可能
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 */
public enum ShortableStockTradeStatus {

	TRADABLE("TRADABLE", "取引可能"), UNTRADABLE("UNTRADABLE", "取引不可");

	ShortableStockTradeStatus(String id, String name) {
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

	public static ShortableStockTradeStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ShortableStockTradeStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
