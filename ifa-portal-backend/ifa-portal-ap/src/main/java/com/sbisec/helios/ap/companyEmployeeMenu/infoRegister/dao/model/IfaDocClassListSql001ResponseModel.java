package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 SQL001（カテゴリ一覧） レスポンスパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListSql001ResponseModel {
    
    /** カテゴリID（数字）. */
    private String t9nInfoCat;
    
    /** カテゴリ名（全角半角）. */
    private String t9nName;
    
}
