package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description 注文種別区分
 * @author mengzhe.li
 * @date 04/05/2022
 */
public enum OrderType {
	NEW("NEW", "新規（発注）", "0"), CORRECTION("CORRECTION", "訂正", "1"), CANCELLATION("CANCELLATION", "取消", "2");

	OrderType(String id, String name, String ifaCd) {
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

	public static OrderType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		OrderType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
