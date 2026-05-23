package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 接触履歴用問合せカテゴリリストのモテル
 * 画面ID：SUB0202_0106-XX
 * 画面名：接触履歴XX
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactToiawaseList {

    /** 問合せカテゴリコード */
    private String toiawaseCd;

    /** カテゴリー名称 */
    private String toiawaseMei;
}
