package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 外国現物取引注文入力注文確認APIレスポンスコンプラチェック情報
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA013ApiResponseComplianceCheck {
    
    /** メッセージID. */
    private String messageId;
    
    /** メッセージ. */
    private String complianceCheckMsg;
    
    /** チェックボックス文言（半角英数字）. */
    private String chkBoxLabel;
    
}
