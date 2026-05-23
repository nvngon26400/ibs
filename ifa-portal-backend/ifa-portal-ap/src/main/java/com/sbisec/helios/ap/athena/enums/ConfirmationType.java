package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description 取引前確認区分
 * @author mengzhe.li
 * @date 04/05/2022
 */
public enum ConfirmationType {
	UN_CONFIRMED("UN_CONFIRMED", "未確認", "0"), CONFIRMATION("CONFIRMATION", "確認済", "1");

	ConfirmationType(String id, String name, String ifaCd) {
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

	public static ConfirmationType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ConfirmationType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
