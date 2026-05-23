package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailApiResponseDomesticStockDepositList {

	/** 保有株数（数値(整数)）. */
	private String holdingStock;

	/** 取得単価/参考単価. */
	private String getPriceReferencePrice;

	/** 現在値（数値(小数)）. */
	private String currentPrice;

	/** 取得金額/参考金額（数値(小数)）. */
	private String acquireAmountReferenceAmount;

	/** 評価額（数値(小数)）. */
	private String valuation;

	/** 評価損益. */
	private String valuationProfitAndLoss;

	/** 預り年月日. */
	private String depositDate;

	/** 保護／代用区分（全角半角）. */
	private String protectionSubstitutionClass;

	/** 入庫理由（全角半角）. */
	private String storageReason;

}
