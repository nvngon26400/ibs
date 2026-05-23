package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:信用期日
 * @author mengzhe.li
 * @date: 02/10/2022
 */
public enum MarginCloseLimitType {
	NO_LIMIT("NO_LIMIT", "無期限", "0"), SIX_MONTHS("SIX_MONTHS", "6カ月", "1");

	MarginCloseLimitType(String id, String name, String ifaCd) {
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

	public static MarginCloseLimitType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MarginCloseLimitType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

	// 返済専用
	public static String getNameForRepay(String buySellCode) {
		// 固定値を設定する
		if (BuySell.BUY.getId().equals(buySellCode)) {
			return SIX_MONTHS.getName();
		} else {
			return NO_LIMIT.getName();
		}

	}

	// 信用専用
	public static String getNameForCredit(String buySellCode) {
		// 固定値を設定する
		if (BuySell.BUY.getId().equals(buySellCode)) {
			return NO_LIMIT.getName();
		} else {
			return SIX_MONTHS.getName();
		}

	}
}
