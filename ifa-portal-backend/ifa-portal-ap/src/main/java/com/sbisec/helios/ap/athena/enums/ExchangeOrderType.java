package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:注文種別表示
 * @author 松田
 */
public enum ExchangeOrderType {
    BUY("BUY", "外貨買"),
	SELL("SELL", "外貨売"),
	TRANSFER("TRANSFER", "振替(FXから)"),
	BOND_PRICE("BOND_PRICE", "外債"),
	REPAYMENT("REPAYMENT", "利金償還金"),
	MMF_PRICE_BUY("MMF_PRICE_BUY", "外貨建MMF円貨決済"),
	MMF_PRICE_SELL("MMF_PRICE_SELL", "外貨建MMF円貨決済（売）"),
	CG_TAX_SELL("CG_TAX_SELL", "譲渡益税充当"),
	;
    

	ExchangeOrderType(String id, String name) {
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

	public static ExchangeOrderType getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		ExchangeOrderType[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

}
