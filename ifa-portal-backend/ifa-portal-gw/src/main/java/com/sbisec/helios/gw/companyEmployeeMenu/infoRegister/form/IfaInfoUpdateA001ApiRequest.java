package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
public class IfaInfoUpdateA001ApiRequest {

    /** お知らせID（数字）. */
    @NotEmpty(message = "お知らせID")
    @Pattern(regexp = "0-9", message = "お知らせID")
    @Size(max = 10, message = "お知らせID")
    private String notificationId;

}
