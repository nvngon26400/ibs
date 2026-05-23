package com.sbisec.helios.gw.systemManageMenu.portalNotification.form;

import lombok.Data;

@Data
public class IfaPortalNotificationUpdateA004ApiResponse {

    /** 表示期間From. */
    private String displayPeriodFrom;

    /** 表示期間To. */
    private String displayPeriodTo;

    /** ご連絡内容. */
    private String notificationContent;

    /** 重要なご連絡. */
    private String importantNotification;

    /** 非表示（半角英数字）. */
    private String nonDisplay;

}
