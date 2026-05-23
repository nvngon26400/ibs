package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model;

import lombok.Data;

@Data
public class IfaPortalNotificationUpdateSql001ResponseModel {

	/** 表示期間FROM. */
	private String displayPeriodFrom;

	/** 表示期間TO. */
	private String displayPeriodTo;

	/** お知らせ（全角半角）. */
	private String infoDetail;

	/** 重要フラグ. */
	private String importantFlg;

	/** 非表示フラグ. */
	private String deleteFlg;

}
