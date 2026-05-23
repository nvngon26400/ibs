package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaDomesticMutualFundOrderConfirmA001aResponseDto {

	/** EC受注番号（半角英数字）. */
	private String ecOrderNo;

	/** 種別（全角半角）. */
	private String shubetu;

	/** エラーコード（半角英数字）. */
	private String code;

	/** エラーメッセージ（全角半角）. */
	private String errMessage;

	/** 見積単価（数値(小数)）. */
	private String quoteUnitPrice;

	/** 約定金額（数値(整数)）. */
	private String contractAmount;

	/** 手数料/諸費用（数値(整数)）. */
	private String charge;

	/** 注文後のNISA投資可能枠(YYYY年). */
	private String nisaInvestableQuote;

	/** 消費税（数値(整数)）. */
	private String consumptionTax;

	/** 讓渡益税（数値(整数)）. */
	private String yieldTax;

	/** 精算金額（数値(整数)）. */
	private String settlementAmount;

	/** 約定予定日. */
	private String contractDate;

	/** 受渡予定日. */
	private String deliveryDate;

	/** 受注日. */
	private String orderDate;

	/** 受注時刻. */
	private String orderDayTime;

	/** 受注数量（数値(整数)）. */
	private String orderQuantity;

	/** 確定利用ポイント. */
	private String confirmPoint;

	/** リクエスト内容. */
	private IfaDomesticMutualFundOrderConfirmA001aRequestDto a001aRequest;

	/** IFA注文番号（数字）. */
	private String ifaOrderNo;

	/** IFA注文サブ番号（数字）. */
	private String ifaOrderSubNo;

}
