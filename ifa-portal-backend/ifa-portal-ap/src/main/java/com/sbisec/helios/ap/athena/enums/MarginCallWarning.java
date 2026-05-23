package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 追証ワーニング
 * 
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum MarginCallWarning {
	RED("RED", "当日の預託率<30%(RED)"), YEL("YEL", "30%≦当日の預託率<40%(Yellow)"), GRN("GRN", "40%≦当日の預託率(Green)");

	MarginCallWarning(String id, String name) {
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

	public static MarginCallWarning getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarginCallWarning[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
