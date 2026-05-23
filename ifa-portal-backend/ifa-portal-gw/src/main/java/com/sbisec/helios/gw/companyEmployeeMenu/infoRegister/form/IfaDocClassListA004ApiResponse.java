package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA004ApiRequest.Category;

import lombok.Data;

/**
 * 画面ID：SUB0501_02-01
 * 資料種別一覧 A004（カテゴリ登録確認） レスポンスパラメタ
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Data
public class IfaDocClassListA004ApiResponse {
    
    /** 登録済カテゴリ. */
    @NotEmpty(message = "登録済カテゴリ")
    private List<Category> registerCategoryList;
    
    /**
     * カテゴリ情報
     */
    @Data
    public static class Category {
        
        /** 登録済カテゴリ.カテゴリ. */
        @NotEmpty(message = "登録済カテゴリ.カテゴリ")
        private String name;
        
    }
}
