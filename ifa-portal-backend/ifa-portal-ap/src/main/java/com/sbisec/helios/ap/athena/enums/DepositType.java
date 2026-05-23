package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:預り区分タイプ
 * @author xin.li
 * @date: 03/09/2022
 */
public enum DepositType {
	PROTECTION_KEEPING("PROTECTION_KEEPING", "保護預り", "保護", "0"),
	COLLATERAL_SECURITIES("COLLATERAL_SECURITIES", "代用預り", "代用", "1"),
	KEEP_CASH("KEEP_CASH","預り金","","");

	DepositType(String id, String name, String ifaName, String ifaCd) {
		this.id = id;
		this.name = name;
		this.ifaName = ifaName;
		this.ifaCd = ifaCd;
	}

	private String id;
	private String name;
	private String ifaName;
	private String ifaCd;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIfaName() {
		return ifaName;
	}

	public String getIfaCd() {
		return ifaCd;
	}

	public static DepositType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		DepositType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

}
