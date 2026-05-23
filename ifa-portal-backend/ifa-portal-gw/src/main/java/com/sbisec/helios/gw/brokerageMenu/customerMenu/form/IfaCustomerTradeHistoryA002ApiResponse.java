package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaCustomerTradeHistoryA002ApiResponse {

    /** 取引履歴明細 */
    private List<IfaCustomerTradeHistoryA002TradeHistoryApiResponse> tradeHistoryList;

}
