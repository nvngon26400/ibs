package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A004（カテゴリ更新確認） リクエストパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA004RequestDto {
    
    /** 登録済カテゴリ. */
    private List<Category> registerCategoryList;
    
    /**
     * カテゴリ情報
     */
    @Data
    public static class Category {
        
        /** 登録済カテゴリ.カテゴリID. */
        private String infoCat;
        
        /** 登録済カテゴリ.カテゴリ. */
        private String name;
        
    }
}
