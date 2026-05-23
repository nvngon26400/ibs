package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 画面ID：SUB0501_01-02_1
* 画面名：情報新規登録A001応答
*
* @author SCSK
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaInfoNewRegisterA001ResponseDto {
    
    /** 新お知らせカテゴリ */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewNotificationCategory {
        
        /** カテゴリID */
        private String t9nInfoCat;
        
        /** カテゴリ名 */
        private String t9nName;
        
        /** 必須フラグ */
        private String requiredFlag;
    }
    
    /** 新お知らせカテゴリリスト */
    private List<NewNotificationCategory> newNotificationCategoryList;
    
}
