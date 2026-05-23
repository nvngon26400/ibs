package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 保有商品一覧 A013 レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListA013ApiResponse {
    
    /** 売却不可明細表示区分. */
    private String sellUnableDetailDisplayClassification;
    
    /** 評価額総合計（数値(小数)）. */
    private String getTotalAssessedValueAll;
    
    /** 評価損益総合計（数値(小数)）. */
    private String getTotalProfitAll;
    
    /** 国内株式リスト. */
    private List<IfaHoldingSecurityListApiResponseDomesticStock> domesticStockList;
    
    /** 投資信託リスト. */
    private List<IfaHoldingSecurityListApiResponseInvestmentTrust> investmentTrustList;
    
    /** 国内債券リスト. */
    private List<IfaHoldingSecurityListApiResponseDomesticBonds> domesticBondsList;
    
    /** 外国債券リスト. */
    private List<IfaHoldingSecurityListApiResponseForeignBonds> foreignBondsList;
    
    /** 外国株式リスト. */
    private List<IfaHoldingSecurityListApiResponseForeignStocks> foreignStocksList;
    
    /** 外貨建MMFリスト. */
    private List<IfaHoldingSecurityListApiResponseForeignCurrencyMmf> foreignCurrencyMmfList;
    
    /** その他商品リスト. */
    private List<IfaHoldingSecurityListApiResponseOtherSecurity> otherSecurityList;
    
}
