package com.sbisec.helios.ap.systemManageMenu.portalNotification.dto;

import lombok.Data;

@Data
public class IfaPortalNotificationUpdateA006RequestDto {

	/** 表示期間From. */
	private String displayPeriodFrom;

	/** 表示期間To. */
	private String displayPeriodTo;

	/** ご連絡内容. */
	private String notificationContent;

}
