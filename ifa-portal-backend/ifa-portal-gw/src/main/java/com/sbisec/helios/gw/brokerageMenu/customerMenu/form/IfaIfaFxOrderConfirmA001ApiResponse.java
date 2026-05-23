package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaIfaFxOrderConfirmA001ApiResponse {

	/** 売買区分（全角半角）. */
	private String tradeKbn;

	/** 注文日時. */
	private String orderDate;

	/** 注文番号（数字）. */
	private String orderNumber;

	/** 数量（数値(整数)）. */
	private String quantity;

	/** 数量の通貨. */
	private String currencyCode;

	/** 数量の通貨名. */
	private String currency;

	/** 約定時の為替レート. */
	private String contractExchangeRate;

	/** 約定時の為替レート日時. */
	private String contractExchangeRateDateTime;

	/** 適用スプレッド（数値(小数)）. */
	private String selectedCurrencyInfo;

	/** 上乗せ区分. */
	private String fxRateAdditionalType;

	/** 上乗せ金額. */
	private String additionalPrice;

	/** 受渡金額（数値(整数)）. */
	private String deliveryAmount;

	/** 約定タイミング. */
	private String tradeTiming;

	/** 受渡日. */
	private String settlementDate;

	/** 注文ワーニングしきい値超過メッセージ（全角半角）. */
	private String orderWarningexceedingThreshold;

	/** 注文ワーニングしきい値（数値(整数)）. */
	private String warningThreshold;

	/** リンク付注意事項URLID. */
	private String リンク付注意事項URLID;

	/** 小数部有効桁数（数値(整数)）. */
	private String decimalLength;

}
