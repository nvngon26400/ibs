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
public class IfaForeignStockCounterOrderInputA001ApiRequest {

    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;

    /** 取引区分 */
    @NotEmpty(message = "取引区分")
    private String tradeClassification;

    /** 返却用ティッカー選択フラグ（全角半角） */
    @NotEmpty(message = "返却用ティッカー選択フラグ")
    @Size(max = 1, message = "返却用ティッカー選択フラグ")
    private String returnTickerSelectFlag;

    /** 返却用銘柄コード */
    @NotEmpty(message = "返却用銘柄コード")
    private String forReturnBrandCode;

    /** 返却用銘柄名 */
    @NotEmpty(message = "返却用銘柄名")
    private String forReturnBrandName;

}
