package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注APIレスポンスコンプラランクチェック
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010ApiResponseComplianceCheck {
    
    /** メッセージ. */
    private String complianceCheckMsg;
    
    /** チェックボックス文言（半角英数字）. */
    private String chkBoxLabel;
    
}
