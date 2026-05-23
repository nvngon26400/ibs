package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:口座分類
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum AccountKind {
	GENERAL("GENERAL", "総合"), JR_NISA("JR_NISA", "ジュニアNISA");

	AccountKind(String id, String name) {
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

	public static AccountKind getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		AccountKind[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
