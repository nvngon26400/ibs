package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA005ApiRequest {

	/** お知らせリスト.お知らせID */
	@NotEmpty(message = "お知らせリスト.お知らせID")
	private String notificationId;

	/** ログインID */
	@NotEmpty(message = "ログインID")
	@Size(max = 16, message = "ログインID")
	private String loginId;

}
