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
public class IfaForeignStockCounterOrderConfirmA002RequestDto {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 仲介業者コード（数字） */
    private String brokerCode;

    /** 売買区分（全角半角） */
    private String tradeKbn;

}
