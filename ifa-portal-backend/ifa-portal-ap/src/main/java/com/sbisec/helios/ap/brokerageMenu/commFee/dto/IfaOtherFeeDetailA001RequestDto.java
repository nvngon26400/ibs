package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import lombok.Data;

@Data
public class IfaOtherFeeDetailA001RequestDto {

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 対象年月. */
	private String targetDateYm;

}
