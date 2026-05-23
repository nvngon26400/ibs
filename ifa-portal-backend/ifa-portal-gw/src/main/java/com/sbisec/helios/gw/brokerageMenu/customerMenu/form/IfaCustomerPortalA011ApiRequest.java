package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaCustomerPortalA011ApiRequest {

	/** メモ(IFA専用)内容（全角半角）. */
	@NotEmpty(message = "メモ(IFA専用)内容")
	@Size(max = 1500, message = "メモ(IFA専用)内容")
	private String ifaMemoContent;
	
	/** メモ(IFA専用)更新日時. */
	@NotEmpty(message = "メモ(IFA専用)更新日時")
    private String ifaMemoUpdateDateTime;

}
