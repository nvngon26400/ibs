package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

/**
 * 為替取引履歴　A002　DTOレスポンス

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistoryA002DtoResponse {
    
    /** 為替取引履歴リスト. */
    private List<IfaFxTradeHistoryFxTradeHistory> fxTradeHistoryList;
    
}
