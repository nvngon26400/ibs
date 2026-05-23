package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A002（カテゴリ登録確認） リクエストパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA002ApiRequest {
    
    /** カテゴリ. */
    @NotEmpty(message = "カテゴリ")
    private String category;
    
}
