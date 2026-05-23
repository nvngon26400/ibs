package com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/06/05
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupApi004bRequest {

    /** CSVファイル名 */
    @NotEmpty(message = "CSVファイル名")
    private String csvDownloadFile;
    
}
