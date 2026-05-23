package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_010302-03
 * 画面名：リアル委託保証金率（国内）
 
 * SCSK
 * 2024/07/29 新規作成
 */

@Data
public class IfaRealEntrustDepositRateDomesticA002DtoResponse {

    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;

    /** 当日基準 */
    private IfaRealEntrustDepositRateDomesticDtoResponseDetailT todayBase;

    /** 翌営業日基準 */
    private IfaRealEntrustDepositRateDomesticDtoResponseDetailT nextBusinessDayBase;

    /** 値洗い基準 */
    private IfaRealEntrustDepositRateDomesticDtoResponseDetailT markToBase;

}
