package com.sbisec.helios.gw.common.composite.form;

import lombok.Data;

@Data
public class IfaNoticeInfoA002ApiResponseTradeRestrictionList {

	/** 制限番号（全角半角）. */
	private String restrictionCode;

	/** 制限内容（全角半角）. */
	private String restrictionContents;

	/** 確認期限. */
	private String confirmDeadline;

}
