package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * サービス用リクエストモデル
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 * A001 初期化
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */

@Data
public class IfaContactCorrectHistoryA001RequestDto {

    /** IFA問合せNO */
    private String ifaToiawaseNo;
    
    /** 登録グループ */
    private String tourokuGroup;
    
}
