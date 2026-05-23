package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A003（カテゴリ登録） リクエストパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA003ApiRequest {
    
    /** カテゴリID（数字）. */
    @NotEmpty(message = "カテゴリID")
    @Pattern(regexp = "0-9", message = "カテゴリID")
    @Size(max = 38, message = "カテゴリID")
    private String t9nInfoCat;
    
    /** カテゴリ. */
    @NotEmpty(message = "カテゴリ")
    private String category;
    
}
