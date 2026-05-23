package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A003（カテゴリ登録） レスポンスパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA003ApiResponse {
    
    /** お知らせカテゴリリスト. */
    private List<InfoCategory> infoCategoryList;
    
    /**
     * お知らせカテゴリ
     */
    @Data
    public static class InfoCategory {
        
        /** カテゴリID（数字）. */
        private String t9nInfoCat;
        
        /** カテゴリ名（全角半角）. */
        private String t9nName;
        
    }
}
