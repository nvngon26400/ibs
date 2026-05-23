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
public class IfaForeignStockCounterOrderInputA001RequestDto {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 取引区分 */
    private String tradeClassification;

    /** 返却用ティッカー選択フラグ（全角半角） */
    private String returnTickerSelectFlag;

    /** 返却用銘柄コード */
    private String forReturnBrandCode;

    /** 返却用銘柄名 */
    private String forReturnBrandName;

}
