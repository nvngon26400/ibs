package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * 保有商品一覧 A001 レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListA001ResponseDto {
    
    /** 売却不可明細表示区分. */
    private String sellUnableDetailDisplayClassification;
    
    /** 評価額総合計（数値(小数)）. */
    private String getTotalAssessedValueAll;
    
    /** 評価損益総合計（数値(小数)）. */
    private String getTotalProfitAll;
    
    /** 国内株式リスト. */
    private List<IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockList;
    
    /** 投資信託リスト. */
    private List<IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustList;
    
    /** 国内債券リスト. */
    private List<IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsList;
    
    /** 外国債券リスト. */
    private List<IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsList;
    
    /** 外国株式リスト. */
    private List<IfaHoldingSecurityListResponseDtoForeignStocks> foreignStocksList;
    
    /** 外貨建MMFリスト. */
    private List<IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfList;
    
    /** ST（セキュリティ・トークン）リスト. */
    private List<IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenList;
    
    /** その他商品リスト. */
    private List<IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityList;
    
}
