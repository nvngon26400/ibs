package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class IfaNotificationViewStatusLookupA001ApiRequest {
	
	/** お知らせリスト.お知らせID. */
	@NotEmpty(message = "お知らせリスト.お知らせID")
	private String notificationId;

	/** お知らせリスト.参照範囲. */
	@NotEmpty(message = "お知らせリスト.参照範囲")
	private String corReferenceCondition;

	/** お知らせリスト.タイトル. */
	@NotEmpty(message = "お知らせリスト.タイトル")
	private String title;

	/** お知らせリスト.登録日時. */
	@NotEmpty(message = "お知らせリスト.登録日時")
	private String registerDayTime;
}
