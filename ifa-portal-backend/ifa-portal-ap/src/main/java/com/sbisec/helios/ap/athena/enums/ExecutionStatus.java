package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:約定状況
 * @author shuchen.xin
 * @date: 11/17/2021
 */
public enum ExecutionStatus {
	NON_EXECUTION("NON_EXECUTION", "未約定"), PARTIAL_EXECUTION("PARTIAL_EXECUTION",
			"一部約定"), FINAL_EXECUTION("FINAL_EXECUTION", "全部約定");

	ExecutionStatus(String id, String name) {
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

	public static ExecutionStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ExecutionStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
