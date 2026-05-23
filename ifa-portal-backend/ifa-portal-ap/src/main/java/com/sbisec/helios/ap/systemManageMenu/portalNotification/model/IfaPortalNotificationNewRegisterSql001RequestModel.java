package com.sbisec.helios.ap.systemManageMenu.portalNotification.model;

import lombok.Data;

@Data
public class IfaPortalNotificationNewRegisterSql001RequestModel {

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

	/** 登録ユーザ. */
    private String createUser;
    
    /** 更新ユーザ. */
    private String updateUser;
}
