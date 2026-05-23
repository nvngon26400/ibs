package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 追証ステータス
 * 
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum MarginCallStatus {
	UNOCCURRED("UNOCCURRED", "追証でない"), OCCURRED_DEPOSIT_EXPIRED("OCCURRED_DEPOSIT_EXPIRED", "追証発生（入金期限後）"),
	COVERED_DEPOSIT_EXPIRED("COVERED_DEPOSIT_EXPIRED", "追証解消（入金期限後）"),
	COVERED_FORCED_CLOSE("COVERED_FORCED_CLOSE", "追証解消（強制返済約定）"),
	OCCURRED_DEPOSIT_UNEXPIRED("OCCURRED_DEPOSIT_UNEXPIRED", "追証発生（入金期限前）"),
	OCCURRED_FORCED_CLOSE("OCCURRED_FORCED_CLOSE", "追証発生（強制返済確定）");

	MarginCallStatus(String id, String name) {
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

	public static MarginCallStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarginCallStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
