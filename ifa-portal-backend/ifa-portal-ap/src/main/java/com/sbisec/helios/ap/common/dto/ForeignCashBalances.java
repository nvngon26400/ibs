package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 外貨金銭残高スケジュール
 *
 * @author SCSK
 */
@Data
public class ForeignCashBalances {
    
    // 外貨金銭残高スケジュール(全通貨)
    private CurrencyCashBalances currencyCashBalances;
}
