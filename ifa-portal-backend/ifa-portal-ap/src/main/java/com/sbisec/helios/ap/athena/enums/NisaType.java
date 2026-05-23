package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:NISA種別
 * @author shuchen.xin
 * @date: 11/16/2021
 */
public enum NisaType {
	NISA("NISA", "NISA"), TSUMITATE_NISA("TSUMITATE_NISA", "つみたてNISA");

	NisaType(String id, String name) {
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

	public static NisaType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		NisaType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
