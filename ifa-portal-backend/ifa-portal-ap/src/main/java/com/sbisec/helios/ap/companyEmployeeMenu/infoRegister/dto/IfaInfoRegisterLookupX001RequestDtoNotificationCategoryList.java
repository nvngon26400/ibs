package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Data
public class IfaInfoRegisterLookupX001RequestDtoNotificationCategoryList {
    
    /** カテゴリID（数字）. */
    private String t9nInfoCat;
    
    /** 必須フラグ（数字）. */
    private String requiredFlag;
    
}
