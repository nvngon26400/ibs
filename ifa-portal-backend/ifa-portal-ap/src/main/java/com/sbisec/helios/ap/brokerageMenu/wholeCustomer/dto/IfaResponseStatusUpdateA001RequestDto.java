package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

@Data
public class IfaResponseStatusUpdateA001RequestDto {

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

}
