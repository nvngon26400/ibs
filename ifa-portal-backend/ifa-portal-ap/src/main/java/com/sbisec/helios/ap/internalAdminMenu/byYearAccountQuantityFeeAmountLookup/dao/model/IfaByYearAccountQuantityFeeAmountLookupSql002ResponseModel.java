package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/22
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel {
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 決算月. */
    private String closingMonth;

}
