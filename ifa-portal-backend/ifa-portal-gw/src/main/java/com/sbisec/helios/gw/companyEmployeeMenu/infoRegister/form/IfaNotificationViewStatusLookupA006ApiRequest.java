package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA006ApiRequest {

    /** CSVファイル名 */
    private String csvDownloadFile;
    
    /** お知らせリスト.お知らせID. */
    @NotEmpty(message = "お知らせリスト.お知らせID")
    private String notificationId;

    /** お知らせリスト.参照範囲. */
    @NotEmpty(message = "お知らせリスト.参照範囲")
    private String corReferenceCondition;

}
