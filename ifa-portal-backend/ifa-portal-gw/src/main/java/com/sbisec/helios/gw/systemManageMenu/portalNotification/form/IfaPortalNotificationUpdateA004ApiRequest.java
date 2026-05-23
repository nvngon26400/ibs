package com.sbisec.helios.gw.systemManageMenu.portalNotification.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class IfaPortalNotificationUpdateA004ApiRequest {

	/** お知らせID（数字）. */
	@NotEmpty(message = "お知らせID")
	@Pattern(regexp="0-9", message = "お知らせID")
	@Size(max = 10, message = "お知らせID")
	private String notificationId;

}
