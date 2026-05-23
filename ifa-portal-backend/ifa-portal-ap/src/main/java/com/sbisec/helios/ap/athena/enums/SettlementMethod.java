package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:決済方法
 * @author shuchen.xin
 * @date: 11/01/2021
 */
public enum SettlementMethod {
	// 1:円貨決済,2:外貨決済
	YEN_SETTLEMENT("YEN_SETTLEMENT", "円貨決済", "1"),
	FOREIGN_SETTLEMENT("FOREIGN_SETTLEMENT", "外貨決済", "2");

	SettlementMethod(String id, String name, String ifaCd) {
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

	public static SettlementMethod getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		SettlementMethod[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

	public static SettlementMethod getByIfaCd(String ifaCd) {
		if (ObjectUtils.isEmpty(ifaCd)) {
			return null;
		}

		SettlementMethod[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getIfaCd().equals(ifaCd)) {
				return enums[i];
			}
		}
		return null;
	}
}
