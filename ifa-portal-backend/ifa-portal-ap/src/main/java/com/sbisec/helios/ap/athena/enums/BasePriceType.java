package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * 基準値段区分
 * 
 * @author xin.li
 * @date: 02/18/2022
 */
public enum BasePriceType {
	GREATER_EQUAL_LESS("GREATER_EQUAL_LESS", "以上～未満"), GREATER_LESS_EQUAL("GREATER_LESS_EQUAL", "超～以下");

	BasePriceType(String id, String name) {
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

	public static BasePriceType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		BasePriceType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
