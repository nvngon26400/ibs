package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/26
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupA002RequestDto {

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 決算年リスト. */
    private List<String> settleYearList;
    
    /** 仲介業者除外. */
    private String chkBrokerCodeExclude;
    
}
