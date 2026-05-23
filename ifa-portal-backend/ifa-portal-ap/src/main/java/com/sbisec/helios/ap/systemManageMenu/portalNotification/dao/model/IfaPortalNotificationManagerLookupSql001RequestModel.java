package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model;

import lombok.Data;

@Data
public class IfaPortalNotificationManagerLookupSql001RequestModel {

    /** 検索年月日(From). */
    private String searchDateYmdFrom;

    /** 検索年月日(To). */
    private String searchDateYmdTo;

    /** 過去日除外. */
    private String pastDateExcrude;

}
