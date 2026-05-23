package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import lombok.Data;

@Data
public class IfaSbiWrapManageFeeA003RequestDto {

	/** 登録日From. */
	private String registeredDateFrom;

	/** 登録日To. */
	private String registeredDateTo;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 仲介業者除外（半角英数字）. */
	private String chkBrokerCodeExclude;

}
