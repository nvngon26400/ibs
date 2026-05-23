package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaJointSubscriptSecurityCashBalanceLookupA004bApiRequest {
    /** CSVダウンロードファイル */
    private String csvDownloadFile;
    
    /** パターン */
    @NotEmpty(message = "パターン")
    private String pattern;
}
