package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Data
public class IfaInfoRegisterLookupA001ApiResponse {
    
    /** お知らせカテゴリリスト. */
    private List<IfaInfoRegisterLookupA001ApiResponseNotificationCategoryList> notificationCategoryList;
    
    /** お知らせリスト. */
    private List<IfaInfoRegisterLookupA001ApiResponseNotificationList> notificationList;
    
}
