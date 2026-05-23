package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageA003ApiResponse {

    /** 自己点検項目リスト */
    private List<SelfAssessmentItem> selfAssessmentItemList;


    /**
     * A003APIレスポンス.自己点検項目
     *
     * @author SCSK 江口
     */
    @Data
    public static class SelfAssessmentItem {

        /** 自己点検項目ID（数字） */
        private String selfCheckItemId;

        /** 登録年月 */
        private String registerDate;

        /** ソート番号 */
        private String sortNumber;

        /** 自己点検項目名 */
        private String selfAssessmentItemName;

        /** 回答（半角英数字） */
        private String answer;

        /** 理由必須フラグ */
        private String reasonRequiredFlag;

    }

}
