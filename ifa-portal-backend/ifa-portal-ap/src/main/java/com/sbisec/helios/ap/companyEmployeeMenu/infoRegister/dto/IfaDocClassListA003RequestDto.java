package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A003（カテゴリ登録） リクエストパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA003RequestDto {
    
    /** カテゴリID（数字）. */
    private String t9nInfoCat;
    
    /** カテゴリ. */
    private String category;
    
}
