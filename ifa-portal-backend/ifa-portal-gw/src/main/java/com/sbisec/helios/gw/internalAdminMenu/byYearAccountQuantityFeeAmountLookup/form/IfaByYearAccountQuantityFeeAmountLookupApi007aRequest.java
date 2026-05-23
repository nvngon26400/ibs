package com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form;

import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/06/11
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupApi007aRequest {
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 決算年月. */
    private String closingYearMonth;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 媒介可否区分 */
    private String mediateProprietyKBN;
    
}
