package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

@Data
public class IfaForeignStockCounterDetailDisplayA001ApiRequest {

    /** 管理番号（半角英数字）. */
    @NotEmpty(message = "管理番号")
    @Size(min = 13, max = 13, message = "管理番号")
    private String manageNumber;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;

}
