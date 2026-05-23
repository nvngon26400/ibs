package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

@Data
public class IfaResponseStatusUpdateA001ResponseDto {

	/** 下落率区分（数字）. */
	private String declineRateKbn;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 投信協会コード（半角英数字）. */
	private String investmentTrustAssociationCode;

	/** 基準日. */
	private String standardDate;

	/** 投信銘柄名（全角半角）. */
	private String mutualFundBrandName;

	/** 基準価額（数値(整数)）. */
	private String price;

	/** 前日比（数値(小数)）. */
	private String diff;

	/** 下落率（数値(小数)）. */
	private String rateOfDecline;

	/** 顧客名（全角半角）. */
	private String customerName;

	/** ステータス区分. */
	private String statusClassification;

	/** 対応方法区分. */
	private String responseMethodClassification;

	/** その他内容区分. */
	private String otherContentsClassification;

	/** その他詳細. */
	private String otherDetail;

	/** 顧客対応日. */
	private String customerResponseDate;

}
