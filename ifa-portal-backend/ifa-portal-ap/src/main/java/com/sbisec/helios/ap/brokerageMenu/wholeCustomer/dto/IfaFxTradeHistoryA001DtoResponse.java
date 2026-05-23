package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

/**
 * 為替取引履歴　A001　DTOレスポンス

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistoryA001DtoResponse {
    
    /** 通貨コードリスト. */
    private List<IfaFxTradeHistoryCurrencyCode> currencyCodeList;
    
    /** 為替取引履歴コメント. */
    private String fxTradeHistoryComment;
    
}
