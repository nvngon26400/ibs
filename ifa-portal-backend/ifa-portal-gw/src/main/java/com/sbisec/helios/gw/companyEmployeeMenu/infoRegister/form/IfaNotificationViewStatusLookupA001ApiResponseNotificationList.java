package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA001ApiResponseNotificationList {

	/** お知らせID（数字）. */
	private String notificationId;

	/** 参照範囲（数字）. */
	private String corReferenceCondition;

	/** タイトル（全角半角）. */
	private String title;

	/** 登録日時. */
	private String registerDayTime;

}
