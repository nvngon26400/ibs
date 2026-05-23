package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderConfirmA002ResponseDto {

    /** 最大売枠（株数） */
    private String maxSellLimitQuantity;

    /** 最大買枠（株数） */
    private String maxBuyLimitQuantity;

}
