package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:信用口座開設判定 ENUM
 * @author shuchen.xin
 * @date: 02/07/2022
 */
public enum MarginAccountType {
	DOMESTIC("DOMESTIC", "国内信用口座"), FOREIGN("FOREIGN", "外国信用口座");

	MarginAccountType(String id, String name) {
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

	public static MarginAccountType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarginAccountType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
