package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:商品コード
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum ProductCode {
	FOREIGN_STOCK("FOREIGN_STOCK", "外国株式"), FOREIGN_MMF("FOREIGN_MMF", "外貨建MMF"), FOREIGN_BOND("FOREIGN_BOND", "外国債券"),
	FOREIGN_EXCHANGE("FOREIGN_EXCHANGE", "外国為替");

	ProductCode(String id, String name) {
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

	public static ProductCode getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ProductCode[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
