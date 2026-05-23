package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model;

import lombok.Data;

@Data
public class IfaPortalNotificationUpdateSql002RequestModel {
    
    /** お知らせID（数字）. */
    private String notificationId;

	/** 表示期間FROM. */
	private String displayPeriodFrom;

	/** 表示期間TO. */
	private String displayPeriodTo;

	/** ご連絡内容. */
	private String notificationContent;

	/** 重要なご連絡. */
	private String importantNotification;

	/** 非表示（半角英数字）. */
	private String nonDisplay;

	/** 更新ユーザ. */
    private String updateUser;
}
