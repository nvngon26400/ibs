package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * コンプラランクチェック
 *
 * @author SCSK
 * 
 */
@Data
public class IfaDomesticStockOrderInputApiResponseComplianceRankCheck {
    
    /** メッセージ. */
    private String message;
    
    /** チェックボックス文言（全角半角）. */
    private String chkBoxLabel;
    
    /** コンプラチェック用資金性格. */
    private String complianceRankCheck;
    
}
