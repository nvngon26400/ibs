package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmSql001RequestModel {

	/** IFA注文番号（数字）. */
	private String ifaOrderNo;

	/** IFA注文サブ番号（数字）. */
	private String ifaOrderSubNo;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 取引種別（全角半角）. */
	private String tradeCd;

	/** 数量の指定方法. */
	private String quantityDesignationMethod;

	/** 売却方法. */
	private String saleMethod;

	/** 通貨コード（全角半角）. */
	private String currencyCode;

	/** 通貨名（全角）. */
	private String currency;

	/** 為替注文金額. */
	private String fxOrderAmount;

	/** 為替取引（数値(整数)）. */
	private String fxTrade;

	/** 適用スプレッド（数値(小数)）. */
	private String selectedCurrencyInfo;

	/** 口座分類（為替取引）. */
	private String accountClassFxTrade;

	/** 預り区分（為替取引）. */
	private String depositTypeFxTrade;

	/** 為替取引サービス種別. */
	private String fxTradeServiceClass;

	/** 為替取引注文状況. */
	private String fxTradeOrderStatus;

	/** 為替取引サイクル番号. */
	private String fxTradeCycleNumber;

	/** 為替取引注文イベントID. */
	private String fxTradeOrderEventId;

	/** 注文番号（数字）. */
	private String orderNumber;

	/** 約定日時. */
	private String tradeDateTime;

	/** 受渡日 . */
	private String settlementDate;

	/** 為替レート（数値(小数)）. */
	private String fxRate;

	/** 為替レート日時. */
	private String fxRateDateTime;

	/** 受渡金額（円貨）（数値(小数)）. */
	private String yenDeliveryAmount;

	/** 注文日時. */
	private String orderDate;

	/** 注文種別表示. */
	private String orderTypeDisplay;

	/** APIエラーコード（全角半角）. */
	private String apiErrorCode;

	/** APIステータスコード（数字）. */
	private String aPIStatusCode;

	/** APIメッセージ（全角半角）. */
	private String apiMsg;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者営業員コード（半角英数字）. */
	private String brokerChargeCode;

	/** 作成日時. */
	private String createTime;

	/** 作成者. */
	private String creator;

	/** 更新日時. */
	private String updateTime;

	/** 更新者. */
	private String updateUser;

}
