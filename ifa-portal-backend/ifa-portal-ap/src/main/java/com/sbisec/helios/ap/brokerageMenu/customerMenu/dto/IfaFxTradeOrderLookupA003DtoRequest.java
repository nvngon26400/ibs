package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * A003リクエスト
 *
 * @author SCSK
 */

@Data
public class IfaFxTradeOrderLookupA003DtoRequest {
    
    /** 絞込期間from. */
    private String refinementPeriodFrom;
    
    /** 絞込期間to. */
    private String refinementPeriodTo;
    
    /** 注文状況. */
    private String orderStatus;
    
    /** 通貨コード. */
    private String currencyCode;
    
    /** 売買区分. */
    private String tradeKbn;
    
}
