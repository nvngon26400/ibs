package com.sbisec.helios.gw.systemManageMenu.portalNotification.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class IfaPortalNotificationNewRegisterA007ApiRequest {

	/** 表示期間From. */
	@NotEmpty(message = "表示期間From")
	private String displayPeriodFrom;

	/** 表示期間To. */
	@NotEmpty(message = "表示期間To")
	private String displayPeriodTo;

	/** お知らせ内容（全角半角）. */
	@NotEmpty(message = "お知らせ内容")
	@Size(max = 2000, message = "お知らせ内容")
	private String notificationContent;

	/** 重要なお知らせ（半角英数字）. */
	@NotEmpty(message = "重要なお知らせ")
	@Size(min = 1, max = 1, message = "重要なお知らせ")
	private String importantNotification;

	/** 非表示（半角英数字）. */
	@NotEmpty(message = "非表示")
	@Size(min = 1, max = 1, message = "非表示")
	private String nonDisplay;

}
