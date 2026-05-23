package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaIfaFxOrderConfirmSql002RequestModel {

	/** 注文発注エラーフラグ  . */
	private Boolean orderPlacementErrorFlag;

	/** IFA注文番号         （数字）. */
	private String ifaOrderNo;

	/** 注文番号（数字）. */
	private String orderNumber;

	/** 約定日時. */
	private String tradeDateTime;

	/** 受渡日 . */
	private String settlementDate;

	/** 為替レート（数値(小数)）. */
	private String fxRate;

	/** 為替レート日時. */
	private String ExchangeRateDateTime;

	/** 受渡金額（円貨）（数値(小数)）. */
	private String yenDeliveryAmount;

	/** 注文日時. */
	private String orderDate;

	/** 注文種別表示. */
	private String orderKindDisplay;

	/** APIエラーコード（全角半角）. */
	private String apiErrorCode;

	/** APIステータスコード（数字）. */
	private String apiStatusCode;

	/** APIメッセージ（全角半角）. */
	private String apiMsg;

	/** ユーザーID           （全角半角）. */
	private String userId;

}
