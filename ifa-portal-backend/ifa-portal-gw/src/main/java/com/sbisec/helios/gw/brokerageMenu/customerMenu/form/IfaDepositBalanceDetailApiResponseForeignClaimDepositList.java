package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailApiResponseForeignClaimDepositList {

	/** 保有額面. */
	private String holdingFaceValue;

	/** 利率（数値(小数)）. */
	private String compoundInterest;

	/** 償還日. */
	private String redemptionDate;

	/** 利払日. */
	private String interestPaymentDate;

	/** 参考為替（数値(小数)）. */
	private String exchangeRate;

	/** 買付単価（数値(小数)）. */
	private String unitPrice;

	/** 約定為替（数値(小数)）. */
	private String contractExchange;

	/** 約定金額（数値(整数)）. */
	private String contractAmount;

	/** 預り年月日. */
	private String depositDate;

	/** 評価額（円）. */
	private String valuationJpAmount;

	/** 保護／代用区分（全角半角）. */
	private String protectionSubstitutionClass;

	/** 入庫理由（全角半角）. */
	private String storageReason;

	/** 通貨コード（全角半角）. */
	private String currencyCode;

}
