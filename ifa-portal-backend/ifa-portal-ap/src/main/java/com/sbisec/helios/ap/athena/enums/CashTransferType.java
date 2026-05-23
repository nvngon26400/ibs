package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 金銭振替区分
 * 
 * @author shuchen.xin
 * @date 06/04/2021
 */
public enum CashTransferType {

	/**
	 * 0.保証金→預り金 1.預り金→保証金
	 */
	CASH_WITHDRAWAL("CASH_WITHDRAWAL", "信用取引保証金→預り金", "0"), CASH_DEPOSIT("CASH_DEPOSIT", "預り金→信用取引保証金", "1");

	CashTransferType(String id, String name, String ifaCd) {
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

	public static CashTransferType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		CashTransferType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
