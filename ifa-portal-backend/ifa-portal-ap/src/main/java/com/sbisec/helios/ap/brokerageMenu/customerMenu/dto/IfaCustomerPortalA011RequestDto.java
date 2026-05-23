package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaCustomerPortalA011RequestDto {

	/** メモ(IFA専用)内容（全角半角）. */
	private String ifaMemoContent;
	
	/** メモ(IFA専用)更新日時. */
	private String ifaMemoUpdateDateTime;

}
