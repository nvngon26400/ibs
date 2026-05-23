package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Data
@JsonSerialize
public class IfaInfoRegisterLookupA001ResponseDtoNotificationCategoryList {
    
    /** カテゴリID（数字）. */
    private String t9nInfoCat;
    
    /** カテゴリ名（全角半角）. */
    private String t9nName;
    
    /** 必須フラグ（数字）. */
    private String requiredFlag;
    
}
