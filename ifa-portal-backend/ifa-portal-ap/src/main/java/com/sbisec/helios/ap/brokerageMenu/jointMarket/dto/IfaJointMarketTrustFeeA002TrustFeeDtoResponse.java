package com.sbisec.helios.ap.brokerageMenu.jointMarket.dto;

import lombok.Data;

/**
 * 画面ID：SUB0208_02
 * 画面名：共同店舗　信託報酬
 *
 * @author SBI大連　董
 2024/12/16 新規作成
 */
@Data
public class IfaJointMarketTrustFeeA002TrustFeeDtoResponse {
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 部店. */
    private String buten;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 支店コード */
    private String branchCode;
    
    /** 支店名 */
    private String branchName;
    
    /** 取引コース */
    private String course;
    
    /** 顧客名（漢字） */
    private String customerNameKanji;
    
    /** 顧客名（カナ） */
    private String customerNameKana;
    
    /** 証券種別. */
    private String securityClass;
    
    /** 保有日. */
    private String holdingcDate;
    
    /** 保有月. */
    private String baseMonth;
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 銘柄名 */
    private String brandName;
    
    /** 数量 */
    private String quantity;
    
    /** 参考価額 */
    private String referencePrice;
    
    /** 基準価額 */
    private String price;
    
    /** 評価額 */
    private String valuation;
    
    /** 信託報酬率 */
    private String trustFeeRate;
    
    /** 通貨. */
    private String currency;
    
    /** 為替レート */
    private String fxRate;
    
    /** 信託報酬額 */
    private String trustFeeAmount;
    
    /** 共募報酬率 */
    private String jointRate;
    
    /** 支払額 */
    private String rewardPrice;
    
    /** 営業員コード */
    private String empCode;
    
    /** 営業員名 */
    private String brokerChargeName;
    
    
}
