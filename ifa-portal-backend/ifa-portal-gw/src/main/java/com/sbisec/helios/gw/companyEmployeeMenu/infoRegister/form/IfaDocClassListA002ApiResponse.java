package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A002（カテゴリ登録確認） レスポンスパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA002ApiResponse {
    
    /** カテゴリ名（全角半角）. */
    private String category;
}
