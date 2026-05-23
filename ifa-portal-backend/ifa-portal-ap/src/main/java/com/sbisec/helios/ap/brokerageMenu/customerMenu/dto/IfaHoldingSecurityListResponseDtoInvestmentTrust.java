package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * 保有商品一覧 投資信託情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoInvestmentTrust {
    
    /** 預り銘柄数. */
    private String numberOfDepositedIssues;
    
    /** 評価額合計（数値(整数)）. */
    private String valuationTotal;
    
    /** 評価損益合計（数値(整数)）. */
    private String getProfitAll;
    
    /** コース. */
    private String course;
    
    /** 分配金受取方法区分（全角半角）. */
    private String reinvest;
    
    /** 口座区分. */
    private String depositBalanceAccountTypeName;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 預り明細リスト. */
    private List<IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail> depositDetailList;
    
}
