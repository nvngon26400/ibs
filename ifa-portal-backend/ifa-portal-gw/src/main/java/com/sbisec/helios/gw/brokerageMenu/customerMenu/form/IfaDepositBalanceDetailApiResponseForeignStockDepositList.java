package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailApiResponseForeignStockDepositList {

	/** 保有株数（数値(整数)）. */
	private String holdingStock;

	/** 取得単価（数値(整数)）. */
	private String openPrice;

	/** 現在値（数値(小数)）. */
	private String currentPrice;

	/** 取得金額. */
	private String getAmount;

	/** 評価額（数値(小数)）. */
	private String valuation;

	/** 外貨建評価損益（数値(小数)）. */
	private String foreignProfitAndLoss;

	/** 評価為替レート（数値(小数)）. */
	private String fxValuationRate;

	/** 評価額（円貨）（数値(整数)）. */
	private String valuationJpAmount;

	/** 評価損益（円貨）（数値(整数)）. */
	private String yenProfitLoss;

	/** 保護／代用区分（全角半角）. */
	private String protectionSubstitutionClass;

}
