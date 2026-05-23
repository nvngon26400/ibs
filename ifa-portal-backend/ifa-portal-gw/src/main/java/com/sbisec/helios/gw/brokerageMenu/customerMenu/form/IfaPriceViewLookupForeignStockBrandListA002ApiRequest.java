package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaPriceViewLookupForeignStockBrandListA002ApiRequest {
    
    /** 検索条件. */
    @NotEmpty(message = "検索条件")
    private String tickerSelectFlag;
    
    /** ティッカーキー. */
    @NotEmpty(message = "ティッカーキー")
    private String brandCodeTicker;
    
    /** 名称キー（全角半角）. */
    @NotEmpty(message = "名称キー")
    @Size(max = 100, message = "名称キー")
    private String brandName;
    
}
