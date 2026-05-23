package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * コンプラランクチェック
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputComplianceRankCheck {
    
    /** メッセージ. */
    private String message;
    
    /** チェックボックス文言 */
    private String invitationCheck;
    
    /** コンプラチェック用資金性格. */
    private String fundCharacter;
    
    /** 開始基準確認メッセージ. */
    private String startCriteriaConfirmMsg;
    
}
