package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 証券振替区分
 * 
 * @author shuchen.xin
 * @date 06/04/2021
 */
public enum SecuritiesTransferType {
	SECURITIES_WITHDRAWAL("SECURITIES_WITHDRAWAL", "代用預り→保護預り", "0"),
	SECURITIES_DEPOSIT("SECURITIES_DEPOSIT", "保護預り→代用預り", "1");

	SecuritiesTransferType(String id, String name, String ifaCd) {
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

	public void setIfaCd(String ifaCd) {
		this.ifaCd = ifaCd;
	}

	public static SecuritiesTransferType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		SecuritiesTransferType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

}
