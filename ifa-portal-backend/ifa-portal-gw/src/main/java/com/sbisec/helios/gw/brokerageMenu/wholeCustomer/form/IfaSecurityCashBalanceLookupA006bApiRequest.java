package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaSecurityCashBalanceLookupA006bApiRequest {
    /** CSVダウンロードファイル */
    private String csvDownloadFile;
    
    /** パターン */
    @NotEmpty(message = "パターン")
    private String pattern;
}
