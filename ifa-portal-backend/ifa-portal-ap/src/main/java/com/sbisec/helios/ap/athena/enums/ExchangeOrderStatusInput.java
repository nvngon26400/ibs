package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:注文状況
 * @author 松田
 */
public enum ExchangeOrderStatusInput {
    COMPLETED("COMPLETED", "約定済"), 
    ORDERING("ORDERING", "注文中"),
    CANCELLED("CANCELLED", "取消済")
    ;

	ExchangeOrderStatusInput(String id, String name) {
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

	public static ExchangeOrderStatusInput getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ExchangeOrderStatusInput[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
