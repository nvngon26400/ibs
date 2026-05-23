package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注レスポンスコンプラランクチェック
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010ResponseDtoComplianceCheck {
    
    /** メッセージ. */
    private String complianceCheckMsg;
    
    /** チェックボックス文言（半角英数字）. */
    private String chkBoxLabel;
    
}
