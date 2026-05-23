package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

@Data
public class IfaResponseStatusUpdateA006RequestDto {

	/** ステータス区分. */
	private String statusClassification;

	/** 下落率区分（数字）. */
	private String declineRateKbn;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 投信協会コード（半角英数字）. */
	private String investmentTrustAssociationCode;

	/** 基準日（To）. */
	private String standardDateTo;

	/** 対応方法区分. */
	private String responseMethodClassification;

	/** その他内容区分. */
	private String otherContentsClassification;

	/** その他詳細. */
	private String otherDetail;

	/** 顧客対応日. */
	private String customerResponseDate;

	/** 対応完了確認日. */
	private String responseFinishConfirmDate;

	/** 更新日時. */
	private String updateTime;

}
