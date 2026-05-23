package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListA008RequestDto {
    
    /** ティッカー選択フラグ. */
    private String tickerSelectFlag;
    
    /** 銘柄コード（ティッカー）. */
    private String brandCodeTicker;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
}
