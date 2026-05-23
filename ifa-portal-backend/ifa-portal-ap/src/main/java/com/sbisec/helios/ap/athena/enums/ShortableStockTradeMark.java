package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description 売建受注枠
 * @author mengzhe.li
 * @date: 03/14/2022
 */
public enum ShortableStockTradeMark {
	SUFFICIENT("SUFFICIENT", "◎余裕あり", "○在庫あり"), JR_NISA("LOW", "▲残りわずか", "○在庫あり"), UNTRADABLE("UNTRADABLE", "×受付不可", "×在庫なし");

	ShortableStockTradeMark(String id, String name, String stockName) {
		this.id = id;
		this.name = name;
		this.stockName = stockName;
	}

	/** ID */
	private String id;
	/** 名称 */
	private String name;
	/** 在庫状況 */
	private String stockName;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStockName() {
		return stockName;
	}

	public static ShortableStockTradeMark getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ShortableStockTradeMark[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
