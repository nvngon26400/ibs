package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * ステータス
 * 
 * @author shuchen.xin
 * @date: 03/10/2022
 */
public enum MarginCallDeficitHistoryStatus {
	PROGRESSING("PROGRESSING", "発生中"), CANCELED("CANCELED", "解消済");

	MarginCallDeficitHistoryStatus(String id, String name) {
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

	public static MarginCallDeficitHistoryStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarginCallDeficitHistoryStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
