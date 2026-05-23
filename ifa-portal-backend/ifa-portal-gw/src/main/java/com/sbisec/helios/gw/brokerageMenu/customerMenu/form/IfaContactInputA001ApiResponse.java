package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * レスポンス
 * 画面ID：SUB0202_0106-03
 * 画面名：接触履歴入力
 * アクション：A001 初期化
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputA001ApiResponse {

    /** 問合せカテゴリリスト */
    private List<IfaContactToiawaseList> toiawaseDList;

    /** 処理区分 */
    private String operationType;
}
