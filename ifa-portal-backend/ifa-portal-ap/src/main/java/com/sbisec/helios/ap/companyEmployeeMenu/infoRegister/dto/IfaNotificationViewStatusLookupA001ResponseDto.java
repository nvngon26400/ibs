package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA001ResponseDto {
    
    /** お知らせリスト. */
	private List<IfaNotificationViewStatusLookupA001ResponseDtoNotificationList> notificationList;

	/** お知らせ閲覧状況照会リスト. */
	private List<IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList> notificationViewStatusLookupList;

}
