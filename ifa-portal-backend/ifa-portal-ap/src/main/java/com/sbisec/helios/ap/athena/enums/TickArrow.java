package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:ティック矢印
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum TickArrow {
	NONE("NONE", "変化なし"), UP("UP", "アップ"), DOWN("DOWN", "ダウン");

	TickArrow(String id, String name) {
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

	public static TickArrow getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		TickArrow[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
