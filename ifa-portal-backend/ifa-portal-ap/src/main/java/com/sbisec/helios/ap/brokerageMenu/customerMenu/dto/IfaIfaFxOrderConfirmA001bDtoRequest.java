package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaIfaFxOrderConfirmA001bDtoRequest {

	/** 通貨コード（全角半角）. */
	private String currencyCode;

	/** 売買区分（全角半角）. */
	private String tradeKbn;

	/** 数量（数値(整数)）. */
	private String quantity;

	/** 適用スプレッド（数値(小数)）. */
	private String selectedCurrencyInfo;

	/** 上乗せ区分. */
	private String fxRateAdditionalType;

	/** 上乗せ金額. */
	private String additionalPrice;

	/** 売却方法. */
	private String saleMethod;

	/** アラート内容確認.注文金額上限超過. */
	private String exceedingOrderAmountLimit;

	/** 注文ワーニングしきい値超過メッセージ（全角半角）. */
	private String orderWarningexceedingThreshold;

	/** 注文ワーニングしきい値（数値(整数)）. */
	private String warningThreshold;

	/** 数量の指定方法. */
	private String quantityDesignationMethod;

	/** 通貨名（全角）. */
	private String currency;

	/** 口座分類（為替取引）. */
	private String accountClass;

	/** リンク付注意事項URLID. */
	private String addLinkAttention;

	/** 小数部有効桁数. */
    private String decimalLength;
    
	/** IFA注文番号（数字）. */
	private String ifaOrderNo;

}
