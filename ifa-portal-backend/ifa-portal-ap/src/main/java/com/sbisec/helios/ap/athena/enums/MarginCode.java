package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 信用取引コード
 * 
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum MarginCode {
	CAN_BUYANDSELL("CAN_BUYANDSELL", "買建・売建可能"), CAN_BUY("CAN_BUY", "買建可能"), CAN_SELL("CAN_SELL", "売建可能");

	MarginCode(String id, String name) {
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

	public static MarginCode getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarginCode[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
