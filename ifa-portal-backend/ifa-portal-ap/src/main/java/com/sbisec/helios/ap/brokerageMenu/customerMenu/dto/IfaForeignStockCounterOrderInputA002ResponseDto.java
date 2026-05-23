package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderInputA002ResponseDto {

    /** 最大売枠（株数） */
    private String maxSellLimitQuantity;

    /** 最大買枠（株数） */
    private String maxBuyLimitQuantity;

    /** 特定買付余力（外貨） */
    private String specificBuyingPowerForeign;

    /** 一般買付余力（外貨） */
    private String generalBuyingPowerForeign;

    /** 特定注文可能数量 */
    private String specificOrderAbleQuantity;

    /** 一般注文可能数量 */
    private String generalOrderAbleQuantity;

}
