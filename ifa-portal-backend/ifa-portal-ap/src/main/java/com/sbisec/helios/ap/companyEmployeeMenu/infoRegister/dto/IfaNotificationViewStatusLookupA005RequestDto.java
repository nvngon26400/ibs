package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA005RequestDto {

    /** お知らせリスト.お知らせID */
    private String notificationId;

    /** ログインID */
    private String loginId;

}
