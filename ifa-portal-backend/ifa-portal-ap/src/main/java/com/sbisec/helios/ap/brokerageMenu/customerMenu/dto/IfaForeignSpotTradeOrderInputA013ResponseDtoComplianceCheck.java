package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文入力注文確認レスポンスコンプラチェック情報
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA013ResponseDtoComplianceCheck {
    
    /** メッセージID. */
    private String messageId;
    
    /** メッセージ. */
    private String complianceCheckMsg;
    
    /** チェックボックス文言（半角英数字）. */
    private String chkBoxLabel;
    
}
