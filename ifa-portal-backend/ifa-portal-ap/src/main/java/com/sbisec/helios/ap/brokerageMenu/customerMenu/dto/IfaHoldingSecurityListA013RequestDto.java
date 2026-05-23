package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 A013 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListA013RequestDto {
    
    /** 口座区分. */
    private String accountClassification;
    
    /** 商品選択（全角半角）. */
    private String securitySelect;
    
}
