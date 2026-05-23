package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;

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
public class IfaInfoRegisterLookupA001ResponseDto {
    
    /** お知らせカテゴリリスト. */
    private List<IfaInfoRegisterLookupA001ResponseDtoNotificationCategoryList> notificationCategoryList;
    
    /** お知らせリスト. */
    private List<IfaInfoRegisterLookupResponseDtoNotificationList> notificationList;
    
}
