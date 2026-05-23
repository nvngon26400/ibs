package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * @Description: 注文ワーニングステータス
 * @author yunhui.zhao
 * @date: 11/01/2022
 */

public enum MarginOrderWarningStatus {
	ORDER_AMOUNT_SOFT_LIMIT("ORDER_AMOUNT_SOFT_LIMIT", "約定代金ソフトリミット上限超過", "1"),
	STOP_ORDER_TRIGGERED("STOP_ORDER_TRIGGERED", "逆指値注文即時発火", "2"),
	ADDITIONAL_COLLATERAL_TRADE("ADDITIONAL_COLLATERAL_TRADE", "増し担保規制銘柄取引", "3");

	MarginOrderWarningStatus(String id, String name, String ifaCd) {
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

	public static MarginOrderWarningStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarginOrderWarningStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
