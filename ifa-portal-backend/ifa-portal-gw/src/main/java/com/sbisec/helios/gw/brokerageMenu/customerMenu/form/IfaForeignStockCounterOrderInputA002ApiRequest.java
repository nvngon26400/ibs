package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderInputA002ApiRequest {

    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;

    /** 売買区分（全角半角） */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;

}
