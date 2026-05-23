package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 投資信託 預り明細情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** ファンド名（全角半角）. */
    private String fundName;
    
    /** 保有口数（数値(整数)）. */
    private String unitVolume;
    
    /** 売却注文中（数値(整数)）. */
    private String sellingVolume;
    
    /** 取得単価/参考単価. */
    private String acquirePriceReferencePrice;
    
    /** 基準価額. */
    private String basePrice8;
    
    /** 取得金額/参考金額（数値(小数)）. */
    private String acquireAmountReferenceAmount;
    
    /** 評価額（数値(小数)）. */
    private String valuation;
    
    /** 評価損益. */
    private String mutualFundListProfitLossTotal;
    
    /** 個別元本（数値(小数)）. */
    private String individualPrincipal;
    
    /** 買付表示区分. */
    private String buyDisplayClassification;
    
    /** 売却表示区分. */
    private String saleDisplayClassification;
    
    /** 分配金受取方法. */
    private String distributionReceiveMethod;
    
    /** 分配金受取方法変更表示区分. */
    private String distributionreceiveMethodchangedisplayclassification;
    
    /** 非特定預り区分. */
    private String depositBalanceListSpecificDepositType;
    
    /** 回数（数値(整数)）. */
    private String times;
    
    /** 号1（全角半角）. */
    private String issue1;
    
    /** 号2（全角半角）. */
    private String issue2;
    
    /** 商品区分（全角半角）. */
    private String securityType;
    
    /** 国内外国区分（全角半角）. */
    private String kokunaiGaiKbn;
    
    /** 商品種別１（全角半角）. */
    private String securityClass1;
    
    /** 商品種別2（全角半角）. */
    private String securityClass2;
    
    /** 会社ｺｰﾄﾞ（数字）. */
    private String companyCode;
    
    /** 権利区分（全角半角）. */
    private String rightType;
    
    /** 新旧区分（全角半角）. */
    private String newOldType;
    
    /** 上場国ｺｰﾄﾞ（全角半角）. */
    private String listedCountryCode;
    
    /** 協会コード（全角半角）. */
    private String kyoukaiCd;
    
    /** 積立表示区分. */
    private String accumulationLink;
    
}
