package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description 仕法区分
 * @author mengzhe.li
 * @date 04/05/2022
 */
public enum MethodType {
	SPOT("SPOT", "現物", "0"), CREDIT_NEW("CREDIT_NEW", "信用新規", "10"), CREDIT_REPAY("CREDIT_REPAY", "信用返済", "11");

	MethodType(String id, String name, String ifaCd) {
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

	public static MethodType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		MethodType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
