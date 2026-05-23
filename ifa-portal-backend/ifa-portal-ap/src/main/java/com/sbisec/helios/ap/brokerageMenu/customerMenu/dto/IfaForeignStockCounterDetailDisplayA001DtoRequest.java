package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

@Data
public class IfaForeignStockCounterDetailDisplayA001DtoRequest {

    /** 管理番号（半角英数字）. */
    private String manageNumber;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;

}
