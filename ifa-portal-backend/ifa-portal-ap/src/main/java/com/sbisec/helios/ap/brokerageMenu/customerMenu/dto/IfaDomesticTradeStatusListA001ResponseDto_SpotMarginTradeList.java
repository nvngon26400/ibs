package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList {

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** 銘柄名（全角半角）. */
	private String brandName;

	/** 取引区分. */
	//手修正
	private String tradeClassification;

	/** 預り区分（全角半角）. */
	private String depositType;

	/** 数量（数値(整数)）. */
	private String quantity;

	/** 平均単価（数値(小数)）. */
	private String averagePrice;

	/** 課税額/譲渡益税（数値(整数)）. */
	private String taxAmountCapitalGain;

	/** 手数料/諸経費（数値(整数)）. */
	private String commCost;

	/** 精算金額（数値(整数)）. */
	private String settlementAmount;

	/** 代用適格区分. */
	private String collateralEligibleType;

	/** 日計り（数値(整数)）. */
	private String dayTrade;

	/** 約定日. */
	private String tradeDate;

	/** 受渡日. */
	private String settlementDate;

	/** 弁済期限（全角半角）. */
	private String paymentDeadline;

	/** 信用取引区分（算出）. */
	//手修正
	private String marginTradeClassification;

}
