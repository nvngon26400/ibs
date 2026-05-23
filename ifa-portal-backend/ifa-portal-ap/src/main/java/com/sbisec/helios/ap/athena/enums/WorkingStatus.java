package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:発火状況
 * @author shuchen.xin
 * @date: 11/17/2021
 */
public enum WorkingStatus {
	BEFORE("BEFORE", "発火前"), AFTER("AFTER", "発火後");

	WorkingStatus(String id, String name) {
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

	public static WorkingStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		WorkingStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
