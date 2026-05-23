package com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/06/05
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupApi004aRequest {

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 決算年リスト. */
    private List<String> settleYearList;
    
    /** 決算月. */
    private String closingMonth;
    
    /** 仲介業者除外. */
    private String chkBrokerCodeExclude;
    
}
