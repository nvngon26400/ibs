package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaFxTradeOrderLookupA003ApiResponse {
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
    /** 為替注文情報リスト. */
    private List<IfaFxTradeOrderLookupA003ApiResponseFxOrderInfo> fxOrderInfoList;
    
    /** 為替取引媒介可否リスト. */
    private List<IfaFxTradeOrderLookupA003ApiResponseFxTradeMediateValue> fxTradeMediateValueList;
    
}
