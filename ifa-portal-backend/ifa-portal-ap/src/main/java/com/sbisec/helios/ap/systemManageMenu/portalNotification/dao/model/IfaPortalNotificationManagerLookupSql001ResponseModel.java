package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model;

import lombok.Data;

@Data
public class IfaPortalNotificationManagerLookupSql001ResponseModel {
    
    /** 総件数. */
    private int totalRow;

    /** お知らせID（数字）. */
    private String infoId;

    /** お知らせ（全角半角）. */
    private String infoDetail;

    /** 重要フラグ. */
    private String importantFlg;

    /** 非表示フラグ. */
    private String deleteFlg;

    /** 表示期間FROM. */
    private String displayPeriodFrom;

    /** 表示期間TO. */
    private String displayPeriodTo;

    /** 登録日時. */
    private String createTime;

}
