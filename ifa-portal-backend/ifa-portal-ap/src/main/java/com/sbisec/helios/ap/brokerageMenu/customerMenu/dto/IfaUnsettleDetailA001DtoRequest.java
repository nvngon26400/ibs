package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaUnsettleDetailA001DtoRequest {

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** リクエストタイプ. */
	private String requestType;

	/** リクエスト日付区分. */
	private String requestDateClassification;

	/** 受渡日. */
	private String settlementDate;

	/** 取得口座区分. */
	private String accountType;

}
