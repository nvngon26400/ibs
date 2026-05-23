package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:価格条件
 * @author shuchen.xin
 * @date: 11/01/2021
 */
public enum OrderPriceKind {
	// 1.指値 2.成行 3.逆指値（指値）4.逆指値（成行）
	LIMIT("LIMIT", "指値", "1"),
	MARKET("MARKET", "成行", "2"), 
	STOP_PRICE_LIMIT("STOP_PRICE_LIMIT", "逆指値/指値", "3"),
	STOP_PRICE_MARKET("STOP_PRICE_MARKET", "逆指値/成行", "4"), 
	TRAILING_STOP_RANGE("TRAILING_STOP_RANGE", "トレールストップ（成行/値幅）", null),
	TRAILING_STOP_PERCENT("TRAILING_STOP_PERCENT", "トレールストップ（成行/％）", null);

	OrderPriceKind(String id, String name, String ifaCd) {
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

	public static OrderPriceKind getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		OrderPriceKind[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
	
	public static OrderPriceKind getByIfaCd(String ifaCd) {
		if (ObjectUtils.isEmpty(ifaCd)) {
			return null;
		}

		OrderPriceKind[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getIfaCd().equals(ifaCd)) {
				return enums[i];
			}
		}

		return null;
	}

}
