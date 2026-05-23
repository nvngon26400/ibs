package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A002（カテゴリ更新確認） レスポンスパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA002ResponseDto {
    
    /** カテゴリ名（全角半角）. */
    private String category;    
}
