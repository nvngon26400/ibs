package com.sbisec.helios.gw.systemManageMenu.portalNotification.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaPortalNotificationUpdateA007ApiRequest {

	/** お知らせID（数字）. */
	@NotEmpty(message = "お知らせID")
	@Pattern(regexp="0-9", message = "お知らせID")
	@Size(max = 10, message = "お知らせID")
	private String notificationId;

	/** 表示期間From. */
	@NotEmpty(message = "表示期間From")
	private String displayPeriodFrom;

	/** 表示期間To. */
	@NotEmpty(message = "表示期間To")
	private String displayPeriodTo;

	/** ご連絡内容. */
	@NotEmpty(message = "ご連絡内容")
	private String notificationContent;

	/** 重要なご連絡. */
	@NotEmpty(message = "重要なご連絡")
	private String importantNotification;

	/** 非表示（半角英数字）. */
	@NotEmpty(message = "非表示")
	@Size(min = 1, max = 1, message = "非表示")
	private String nonDisplay;

}
