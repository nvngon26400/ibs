package com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form;

import lombok.Data;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 *
 * @author SBI大連 夏
 * @date   2025/05/29
 */

@Data
public class IfaBrokerCodeClosingMonthLoginApi006Request {

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 設定する決算月. */
    private String settingClosingMonth;
    
}
