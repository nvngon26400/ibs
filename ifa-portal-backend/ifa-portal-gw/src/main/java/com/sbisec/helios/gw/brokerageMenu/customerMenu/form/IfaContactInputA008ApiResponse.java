package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * アクションID:A008
 * アクション名:カテゴリ選択（中）
 *
 * @author SBI大連 夏
 * @date   2025/08/01
 */

@Data
public class IfaContactInputA008ApiResponse {
    
    /** 問合せカテゴリリスト */
    private List<IfaContactToiawaseList> toiawaseSList;

}
