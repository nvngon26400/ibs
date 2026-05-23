package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 赤残ステータス
 * 
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum DeficitBalanceStatus {
	UNRESOLVED("UNRESOLVED", "未解消"), RESOLVING("RESOLVING", "赤残付替中"), SOLVED_AFTER_DEPOSIT("SOLVED_AFTER_DEPOSIT", "入金による解除（赤残付け替え不要）"),
	SOLVED("SOLVED", "赤残付替後の解除"), UNRESOLVED_AFTER_REPLACEMENT("UNRESOLVED_AFTER_REPLACEMENT", "赤残付替後に解除できず");

	DeficitBalanceStatus(String id, String name) {
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

	public static DeficitBalanceStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		DeficitBalanceStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
