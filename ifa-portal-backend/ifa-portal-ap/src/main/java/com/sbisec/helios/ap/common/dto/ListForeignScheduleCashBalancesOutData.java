package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 外貨金銭残高スケジュール取得APIOutDto
 *
 * @author SCSK
 */
@Data
public class ListForeignScheduleCashBalancesOutData {
    
    // エラーコード
    private String errorCode;
    
    // 外貨金銭残高スケジュール
    private ForeignCashBalances foreignCashBalances;
    
}
