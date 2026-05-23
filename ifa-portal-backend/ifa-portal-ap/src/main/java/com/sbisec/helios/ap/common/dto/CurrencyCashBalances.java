package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 外貨金銭残高スケジュール(全通貨)
 *
 * @author SCSK
 */
@Data
public class CurrencyCashBalances {
    
    // 外貨金銭残高スケジュールリスト
    private ForeignScheduleCashBalances foreignScheduleCashBalances;
}
