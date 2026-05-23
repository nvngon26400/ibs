package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:注文日基準種別
 * @author shuchen.xin
 * @date: 02/16/2022
 */
public enum OrderDateType {
	FRN_TRADE_DATE("FRN_TRADE_DATE", "現地約定日基準"), ORDER_INPUT_DATE("ORDER_INPUT_DATE", "国内注文日基準");

	OrderDateType(String id, String name) {
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

	public static OrderDateType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		OrderDateType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
