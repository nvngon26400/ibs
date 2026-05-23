package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA001RequestDto {

    /** お知らせリスト.お知らせID. */
    private String notificationId;

    /** お知らせリスト.参照範囲. */
    private String corReferenceCondition;

    /** お知らせリスト.タイトル. */
    private String title;

    /** お知らせリスト.登録日時. */
    private String registerDayTime;

}
