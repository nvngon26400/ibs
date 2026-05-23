package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import lombok.Data;

@Data
public class IfaOtherFeeDetailSql001ResponseModel {

	/** 総件数. */
	private String totalCount;

	/** 対象年月. */
	private String targetDateYm;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者支店名. */
	private String brokerName;

	/** 扱者コード（半角英数字）. */
	private String dealerNumber;

	/** 連番（全角半角）. */
	private String sequentialNumber;

	/** 報酬金額（数値(小数)）. */
	private String feeAmount;

	/** メッセージ. */
	private String otherFeeMessage;

	/** 報酬金額合計. */
	private String feeAmountTotal;

}
