package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaFxTradeOrderLookupA001ApiResponse {
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
    /** 為替注文情報リスト. */
    private List<IfaFxTradeOrderLookupA001ApiResponseFxOrderInfo> fxOrderInfoList;
    
    /** 通貨リスト. */
    private List<IfaFxTradeOrderLookupA001ApiResponseCurrency> currencyList;
    
    /** 為替取引媒介可否リスト. */
    private List<IfaFxTradeOrderLookupA001ApiResponseFxTradeMediateValue> fxTradeMediateValueList;
    
}
