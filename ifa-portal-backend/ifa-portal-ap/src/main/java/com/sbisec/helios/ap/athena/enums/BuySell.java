package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 売買区分
 * 
 * @author shuchen.xin
 * @date: 11/01/2021
 */
public enum BuySell {
	// 3:買付;1:売却
	SELL("SELL", "売付", "1"),
	BUY("BUY", "買付", "3");

	BuySell(String id, String name, String ifaCd) {
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

	public static BuySell getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		BuySell[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

	public static BuySell getByIfaCd(String ifaCd) {
		if (ObjectUtils.isEmpty(ifaCd)) {
			return null;
		}

		BuySell[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getIfaCd().equals(ifaCd)) {
				return enums[i];
			}
		}

		return null;
	}
}
