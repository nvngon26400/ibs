package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:検索マッチ種別
 * @author xin.li
 * @date: 02/14/2022
 */
public enum SearchKeywordMatchType {
	START_WITH("START_WITH", "先頭からマッチ"), CONTAINS("CONTAINS", "含まれる"), END_WITH("END_WITH", "末尾からマッチ");

	SearchKeywordMatchType(String id, String name) {
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

	public static SearchKeywordMatchType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		SearchKeywordMatchType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
