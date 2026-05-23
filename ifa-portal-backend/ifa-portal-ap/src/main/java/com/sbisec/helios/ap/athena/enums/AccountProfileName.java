package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:アカウントプロファイル名
 * @author xiu.yan
 * @date: 01/15/2022
 */
public enum AccountProfileName {
	ACCOUNT_MODE("ACCOUNT_MODE", "アカウントモード"), TRADE_FSTOCK_US_PERMISSION("TRADE_FSTOCK_US_PERMISSION", "外貨建口座取引開設状況"),
	TRADE_FXBOND_PERMISSION("TRADE_FXBOND_PERMISSION", "外国債券取引可否");

	AccountProfileName(String id, String name) {
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

	public static AccountProfileName getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		AccountProfileName[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
