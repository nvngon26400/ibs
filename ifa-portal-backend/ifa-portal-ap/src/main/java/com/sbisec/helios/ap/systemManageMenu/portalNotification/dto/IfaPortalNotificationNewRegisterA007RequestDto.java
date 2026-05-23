package com.sbisec.helios.ap.systemManageMenu.portalNotification.dto;

import lombok.Data;

@Data
public class IfaPortalNotificationNewRegisterA007RequestDto {

	/** 表示期間From. */
	private String displayPeriodFrom;

	/** 表示期間To. */
	private String displayPeriodTo;

	/** お知らせ内容（全角半角）. */
	private String notificationContent;

	/** 重要なお知らせ（半角英数字）. */
	private String importantNotification;

	/** 非表示（半角英数字）. */
	private String nonDisplay;

}
