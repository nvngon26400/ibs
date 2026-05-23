package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailApiResponseForeignMmfDepositList {

	/** 保有口数（数値(整数)）. */
	private String unitVolume;

	/** 評価額（外貨）（数値(小数)）. */
	private String foreignValuation;

	/** 評価為替レート（数値(小数)）. */
	private String fxValuationRate;

	/** 評価額（円貨）（数値(整数)）. */
	private String valuation;

	/** 評価損益（円貨）（数値(整数)）. */
	private String yenProfitLoss;

	/** 保護／代用区分（全角半角）. */
	private String protectionSubstitutionClass;

}
