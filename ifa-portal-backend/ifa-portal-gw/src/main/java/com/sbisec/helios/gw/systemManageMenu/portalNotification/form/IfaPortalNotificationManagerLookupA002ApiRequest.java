package com.sbisec.helios.gw.systemManageMenu.portalNotification.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaPortalNotificationManagerLookupA002ApiRequest {

	/** 検索年月日(From). */
	@NotEmpty(message = "検索年月日(From)")
	private String searchDateYmdFrom;

	/** 検索年月日(To). */
	@NotEmpty(message = "検索年月日(To)")
	private String searchDateYmdTo;

	/** 過去日除外. */
	@NotEmpty(message = "過去日除外")
	private String pastDateExcrude;

}
