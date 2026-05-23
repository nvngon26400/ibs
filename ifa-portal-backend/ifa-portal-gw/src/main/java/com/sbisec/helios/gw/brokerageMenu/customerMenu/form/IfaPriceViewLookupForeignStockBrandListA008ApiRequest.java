package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListA008ApiRequest {
    
    /** ティッカー選択フラグ. */
    @NotEmpty(message = "ティッカー選択フラグ")
    private String tickerSelectFlag;
    
    /** 銘柄コード（ティッカー）. */
    @NotEmpty(message = "銘柄コード（ティッカー）")
    private String brandCodeTicker;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
}
