package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
public class IfaInfoUpdateA001ApiResponseNotificationCategoryList {

    /** カテゴリID（数字）. */
    private String t9nInfoCat;

    /** カテゴリ名（全角半角）. */
    private String t9nName;

    /** 必須フラグ（数字）. */
    private String requiredFlag;

}
