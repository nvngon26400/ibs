package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;
import lombok.Data;

/**
 * 為替取引履歴　A002　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistoryA002ApiResponse {
    
    /** 為替取引履歴リスト. */
    private List<IfaFxTradeHistoryFxTradeHistory> fxTradeHistoryList;
    
}
