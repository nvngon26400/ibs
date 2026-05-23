package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * 保有商品一覧 その他商品情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoOtherSecurity {
    
    /** 預り銘柄数. */
    private String numberOfDepositedIssues;
    
    /** 口座区分. */
    private String depositBalanceAccountTypeName;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 預り明細リスト. */
    private List<IfaHoldingSecurityListResponseDtoDepositDetail> depositDetailList;
    
}
