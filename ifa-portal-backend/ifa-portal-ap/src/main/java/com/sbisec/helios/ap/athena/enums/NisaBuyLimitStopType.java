package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:NISA投資可能枠利用停止種別
 * @author shuchen.xin
 * @date: 11/16/2021
 */
public enum NisaBuyLimitStopType {
	INITIALIZATION("INITIALIZATION", "初期値（枠利用可）"), NISA_ROLL_OVER("NISA_ROLL_OVER",
			"ROに伴う枠停止"), NISA_ACCOUNT_CHANGE("NISA_ACCOUNT_CHANGE", "勘定変更に伴う枠停止");

	NisaBuyLimitStopType(String id, String name) {
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

	public static NisaBuyLimitStopType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		NisaBuyLimitStopType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
