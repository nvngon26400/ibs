package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaIfaFxOrderConfirmSql001RequestModel {

	/** IFA注文番号         （数字）. */
	private String ifaOrderNo;

	/** 部店コード           （半角英数字）. */
	private String butenCode;

	/** 口座番号             （数字）. */
	private String accountNumber;

	/** 取引種別             （全角半角）. */
	private String tradeCd;

	/** 数量の指定方法       . */
	private String quantityDesignationMethod;

	/** 売却方法             . */
	private String saleMethod;

	/** 通貨コード           （全角半角）. */
	private String currencyCode;

	/** 通貨名               （全角）. */
	private String currency;

	/** 為替注文金額         . */
	private String fxOrderAmount;

	/** 適用スプレッド       （数値(小数)）. */
	private String selectedCurrencyInfo;

	/** 口座分類（為替取引） . */
	private String accountClass;

	/** 仲介業者コード       （数字）. */
	private String brokerCode;

	/** 仲介業者営業員コード （半角英数字）. */
	private String brokerChargeCode;

	/** ユーザーID           （全角半角）. */
	private String userId;

}
