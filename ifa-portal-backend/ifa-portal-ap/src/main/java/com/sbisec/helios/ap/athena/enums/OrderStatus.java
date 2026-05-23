package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 注文状況
 * 
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum OrderStatus {
	ORDERING("ORDERING", "注文中"), CORRECTING("CORRECTING", "訂正中"), CANCELING("CANCELING", "取消中"),
	EXPIRED("EXPIRED", "失効済"), CANCELLED("CANCELLED", "取消済"), WAITING("WAITING", "待機中"), COMPLETED("COMPLETED", "完了");

	OrderStatus(String id, String name) {
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

	public static OrderStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		OrderStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
