package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA001ResponseDtoNotificationList {

    /** お知らせID（数字）. */
    private String notificationId;

    /** 参照範囲（数字）. */
    private String corReferenceCondition;

    /** タイトル（全角半角）. */
    private String title;

    /** 登録日時. */
    private String registerDayTime;
}
