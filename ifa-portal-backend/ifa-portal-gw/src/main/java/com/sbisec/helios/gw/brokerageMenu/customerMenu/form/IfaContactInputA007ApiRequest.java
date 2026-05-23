package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * アクションID:A007
 * アクション名:カテゴリ選択（大）
 *
 * @author SBI大連 夏
 * @date   2025/08/01
 */

@Data
public class IfaContactInputA007ApiRequest {

    /** 問合せカテゴリコード（大） */
    private String toiawaseDCd;

    /** カテゴリー名称（大） */
    private String toiawaseDMei;
    
}
