package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * @Description 返済建玉指定方法
 * 
 * @author yunhui.zhao
 * @date 15/02/2022
 */
public enum ClosePositionKind {
	COLLECTIVE("COLLECTIVE", "一括指定", "1"), INDIVIDUAL("INDIVIDUAL", "個別指定", "0");

	ClosePositionKind(String id, String name, String ifaCd) {
		this.id = id;
		this.name = name;
		this.ifaCd = ifaCd;
	}

	private String id;
	private String name;
	private String ifaCd;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIfaCd() {
		return ifaCd;
	}

	public static ClosePositionKind getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ClosePositionKind[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
