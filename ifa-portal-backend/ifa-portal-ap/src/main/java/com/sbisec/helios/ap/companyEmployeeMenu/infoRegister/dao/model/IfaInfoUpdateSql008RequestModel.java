package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaInfoUpdateSql008RequestModel {

    /** 機能ID（全角半角）. */
    private String functionId;

    /** カテゴリID（数字）. */
    private String t9nInfoCat;

}
