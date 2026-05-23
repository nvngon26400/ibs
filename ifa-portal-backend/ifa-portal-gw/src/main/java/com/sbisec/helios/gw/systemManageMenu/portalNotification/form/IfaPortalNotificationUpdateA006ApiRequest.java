package com.sbisec.helios.gw.systemManageMenu.portalNotification.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaPortalNotificationUpdateA006ApiRequest {

	/** 表示期間From. */
	@NotEmpty(message = "表示期間From")
	private String displayPeriodFrom;

	/** 表示期間To. */
	@NotEmpty(message = "表示期間To")
	private String displayPeriodTo;

	/** ご連絡内容. */
	@NotEmpty(message = "ご連絡内容")
	private String notificationContent;

}
