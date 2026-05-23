package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderConfirmSql001ResponseModel {

    /** 販売状態 */
    private String tradeStatus;

    /** 売枠（株数） */
    private BigDecimal sellTradeCount;

    /** 買枠（株数） */
    private BigDecimal buyTradeCount;

    /** 売取引価格 */
    private String sellTradePrice;

    /** 買取引価格 */
    private String buyTradePrice;

    /** 取引開始時間 */
    private String tradeStartTime;

    /** 取引終了時間 */
    private String tradeEndTime;

}
