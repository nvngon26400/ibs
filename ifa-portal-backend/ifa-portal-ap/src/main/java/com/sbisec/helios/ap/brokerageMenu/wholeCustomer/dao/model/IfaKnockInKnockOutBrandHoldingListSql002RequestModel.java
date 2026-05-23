package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB020301_03-02,SUB020301_03-03
 * 画面名：ノックイン銘柄保有一覧,ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaKnockInKnockOutBrandHoldingListSql002RequestModel {
        /** 機能ID（全角半角）. */
    private String functionId;

    /** カテゴリID（数字）. */
    private String t9nInfoCat;
}
