package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:期間条件
 * @author shuchen.xin
 * @date: 11/01/2021
 */
public enum OrderLimit {

	TODAY_ORDER("TODAY_ORDER", "当日注文", "当日中"), CARRY_OVER_ORDER("CARRY_OVER_ORDER", "期間指定注文", "期間指定");

	OrderLimit(String id, String name, String ifaNm) {
		this.id = id;
		this.name = name;
		this.ifaNm = ifaNm;
	}

	private String id;
	private String name;
	private String ifaNm;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIfaNm() {
		return ifaNm;
	}

	public static OrderLimit getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		OrderLimit[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
