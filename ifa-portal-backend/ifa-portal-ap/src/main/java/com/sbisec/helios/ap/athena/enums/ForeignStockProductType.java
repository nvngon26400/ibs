package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:商品種別
 * @author shuchen.xin
 * @date: 02/16/2022
 */
public enum ForeignStockProductType {
	ORDER("ORDER", "外国株式（現物）"), MARGIN_ORDER("MARGIN_ORDER", "外国株式（信用）");

	ForeignStockProductType(String id, String name) {
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

	public static ForeignStockProductType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ForeignStockProductType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
