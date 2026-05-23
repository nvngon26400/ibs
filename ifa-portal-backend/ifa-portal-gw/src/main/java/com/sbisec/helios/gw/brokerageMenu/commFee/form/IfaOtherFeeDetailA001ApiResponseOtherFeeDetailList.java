package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import lombok.Data;

@Data
public class IfaOtherFeeDetailA001ApiResponseOtherFeeDetailList {

	/** 仲介業者名（全角半角）. */
	private String brokerName;

	/** 対象年月. */
	private String targetDateYm;

	/** 報酬金額合計. */
	private String feeAmountTotal;

	/** 扱者コード（半角英数字）. */
	private String dealerNumber;

	/** 連番（全角半角）. */
	private String sequentialNumber;

	/** 報酬金額（数値(小数)）. */
	private String feeAmount;

	/** メッセージ. */
	private String otherFeeMessage;

}
