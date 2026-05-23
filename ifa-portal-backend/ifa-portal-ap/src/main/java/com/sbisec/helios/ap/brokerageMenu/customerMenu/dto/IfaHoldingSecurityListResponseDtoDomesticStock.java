package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * 保有商品一覧 国内株式情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoDomesticStock {
    
    /** 預り銘柄数. */
    private String numberOfDepositedIssues;
    
    /** 評価額合計（数値(整数)）. */
    private String valuationTotal;
    
    /** 評価損益合計（数値(整数)）. */
    private String getProfitAll;
    
    /** 口座区分. */
    private String depositBalanceAccountTypeName;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 国内株式.預り明細リスト. */
    private List<IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail> depositDetailList;
    
}
