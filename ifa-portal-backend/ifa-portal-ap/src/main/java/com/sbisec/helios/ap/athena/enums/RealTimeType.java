package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * リアルタイム種別
 * 
 * @author yunhui.zhao
 * @date 02/12/2022
 */
public enum RealTimeType {
	REAL("REAL", "リアル"), DELAY("DELAY", "ディレイ");

	RealTimeType(String id, String name) {
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

	public static RealTimeType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		RealTimeType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
