package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

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
public class IfaCustomerTradeHistoryA002ResponseDto {

    /** 取引履歴明細 */
    private List<IfaCustomerTradeHistoryA002TradeHistoryResponseDto> tradeHistoryList;

}
