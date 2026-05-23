package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Data
public class IfaInfoRegisterLookupX001RequestDto {
    
    /** お知らせカテゴリリスト. */
    @NotEmpty(message = "お知らせカテゴリリスト")
    private List<IfaInfoRegisterLookupX001RequestDtoNotificationCategoryList> notificationCategoryList;
    
}
