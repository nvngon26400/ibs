package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * A003Dtoレスポンス
 *
 * @author SCSK
 */

@Data
public class IfaFxTradeOrderLookupA003DtoResponse {
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
    /** 為替注文情報リスト. */
    private List<IfaFxTradeOrderLookupA003DtoResponseFxOrderInfo> fxOrderInfoList;
    
    /** 為替取引媒介可否リスト. */
    private List<IfaFxTradeOrderLookupA003DtoResponseFxTradeMediatePropriety> fxTradeMediateProprietyList;
    
}
