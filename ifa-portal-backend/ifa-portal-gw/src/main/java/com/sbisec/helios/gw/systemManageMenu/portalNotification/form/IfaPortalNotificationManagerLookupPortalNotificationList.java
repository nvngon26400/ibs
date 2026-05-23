package com.sbisec.helios.gw.systemManageMenu.portalNotification.form;

import lombok.Data;

@Data
public class IfaPortalNotificationManagerLookupPortalNotificationList {

    /** お知らせID（数字）. */
    private String notificationId;

    /** お知らせ（全角半角）. */
    private String notification;

    /** 重要フラグ. */
    private String importantFlag;

    /** 非表示フラグ. */
    private String nonDisplayFlag;

    /** 表示期間FROM. */
    private String displayPeriodFrom;

    /** 表示期間TO. */
    private String displayPeriodTo;

    /** 登録日時. */
    private String registerDayTime;

}
