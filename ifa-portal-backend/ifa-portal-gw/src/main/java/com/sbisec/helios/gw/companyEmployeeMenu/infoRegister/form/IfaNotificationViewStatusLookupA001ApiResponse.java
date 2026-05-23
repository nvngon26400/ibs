package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;
import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA001ApiResponse {

	/** お知らせリスト. */
	private List<IfaNotificationViewStatusLookupA001ApiResponseNotificationList> notificationList;

	/** お知らせ閲覧状況照会リスト. */
	private List<IfaNotificationViewStatusLookupA001ApiResponseNotificationViewStatusLookupList> notificationViewStatusLookupList;

}
