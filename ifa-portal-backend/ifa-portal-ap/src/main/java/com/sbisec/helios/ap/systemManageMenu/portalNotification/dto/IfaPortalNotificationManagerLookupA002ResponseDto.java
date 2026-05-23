package com.sbisec.helios.ap.systemManageMenu.portalNotification.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaPortalNotificationManagerLookupA002ResponseDto {

	/** ポータルお知らせリスト. */
	private List<IfaPortalNotificationManagerLookupPortalNotificationList> portalNotificationList;

}
