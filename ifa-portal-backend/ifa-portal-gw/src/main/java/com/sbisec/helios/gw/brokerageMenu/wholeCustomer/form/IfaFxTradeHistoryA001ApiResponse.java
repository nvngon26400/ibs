package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;
import lombok.Data;

/**
 * 為替取引履歴　A001　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistoryA001ApiResponse {
    
    /** 通貨コードリスト. */
    private List<IfaFxTradeHistoryCurrencyCode> currencyCodeList;
    
    /** 為替取引履歴コメント. */
    private String fxTradeHistoryComment;
    
}
