package com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 *
 * @author SBI大連 夏
 * @date   2025/06/11
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupApi007bRequest {

    /** CSVファイル名 */
    @NotEmpty(message = "CSVファイル名")
    private String csvDownloadFile;
    
    /** 決算年月. */
    private String closingYearMonth;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
}
