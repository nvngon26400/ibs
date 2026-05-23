package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 返済選択順序.
 * 
 * @author yunhui.zhao
 * @date 15/02/2022
 */
public enum CloseSelectionSort {
	EVALUATION_PROFIT_DESC("EVALUATION_PROFIT_DESC", "評価益降順", "評価益順", "0"),
	EVALUATION_LOSS_DESC("EVALUATION_LOSS_DESC", "評価損降順", "評価損順", "1"),
	OPEN_DATE_ASC("OPEN_DATE_ASC", "建日昇順", "建日古い順", "2"),
	OPEN_DATE_DESC("OPEN_DATE_DESC", "建日降順", "建日新しい順", "3");

	CloseSelectionSort(String id, String name, String ifaName, String ifaCd) {
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

	public static CloseSelectionSort getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		CloseSelectionSort[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

}
