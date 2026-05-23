package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0505_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaInfoNewRegisterA001ApiResponse {
    
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
