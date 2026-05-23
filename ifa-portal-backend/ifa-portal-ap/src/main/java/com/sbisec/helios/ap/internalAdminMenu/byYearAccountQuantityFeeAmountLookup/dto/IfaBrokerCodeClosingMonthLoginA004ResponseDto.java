package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 *
 * @author SBI大連 夏
 * @date   2025/05/27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaBrokerCodeClosingMonthLoginA004ResponseDto {

    /** 仲介業者名. */
    private String brokerName;
    
    /** 決算月. */
    private String closingMonth;
}
