package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:自動買付区分
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum AutoStockOrderType {
	NORMAL_ORDER("NORMAL_ORDER", "通常注文"), RESERVE_ORDER("RESERVE_ORDER", "定期買付");

	AutoStockOrderType(String id, String name) {
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

	public static AutoStockOrderType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		AutoStockOrderType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
