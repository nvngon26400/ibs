package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_010302-03
 * 画面名：リアル委託保証金率（国内）
 
 * SCSK
 * 2024/07/29 新規作成
 */

@Data
public class IfaRealEntrustDepositRateDomesticX001ApiRequest {
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
}
