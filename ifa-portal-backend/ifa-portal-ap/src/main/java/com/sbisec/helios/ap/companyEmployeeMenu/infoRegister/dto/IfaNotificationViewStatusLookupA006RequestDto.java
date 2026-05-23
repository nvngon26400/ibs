package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA006RequestDto {

    /** お知らせリスト.お知らせID. */
    private String notificationId;

    /** お知らせリスト.参照範囲. */
    private String corReferenceCondition;

}
