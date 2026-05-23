package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IfaInfoNewRegisterSql001ResponseModel {
    
    /** カテゴリID */
    private String t9nInfoCat;
    
    /** カテゴリ名 */
    private String t9nName;
    
    /** 必須フラグ */
    private String t9nRequiredFlg;
}
