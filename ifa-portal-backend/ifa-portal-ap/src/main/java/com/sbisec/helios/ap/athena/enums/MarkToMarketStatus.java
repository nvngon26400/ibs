package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 直近値洗い区分
 * 
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum MarkToMarketStatus {
	EST("EST", "概算"), FIX("FIX", "確定");

	MarkToMarketStatus(String id, String name) {
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

	public static MarkToMarketStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarkToMarketStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
