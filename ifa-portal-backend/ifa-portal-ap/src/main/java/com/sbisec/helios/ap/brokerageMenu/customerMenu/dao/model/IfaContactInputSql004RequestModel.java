package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL004用リクエストモデル
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * 2025/07/28 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputSql004RequestModel {
    /** 問合せ区分 */
    private String toiawaseKbn;
    /** IFA専用フラグ */
    private String ifaSenyouFlg;
    /** 削除フラグ */
    private String sakujoFlg;
    /** 問合せカテゴリ(大分類) */
    private String toiawaseD;
    /** 問合せカテゴリ(中分類) */
    private String toiawaseT;
}
