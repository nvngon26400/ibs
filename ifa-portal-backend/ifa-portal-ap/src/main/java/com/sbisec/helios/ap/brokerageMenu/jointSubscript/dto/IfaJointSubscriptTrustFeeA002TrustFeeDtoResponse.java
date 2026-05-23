package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import lombok.Data;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
@Data
public class IfaJointSubscriptTrustFeeA002TrustFeeDtoResponse {
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 営業員コード */
    private String empCode;
    
    /** 営業員名 */
    private String brokerChargeName;
    
    /** 部店 */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 取引コース */
    private String course;
    
    /** 顧客名（漢字） */
    private String customerNameKanji;
    
    /** 顧客名（カナ） */
    private String customerNameKana;
    
    /** 証券種別 */
    private String securityClass;
    
    /** 保有日 */
    private String baseDate;
    
    /** 保有月 */
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
    
    /** 通貨 */
    private String currency;
    
    /** 為替レート */
    private String fxRate;
    
    /** 信託報酬額 */
    private String trustFeeAmount;
    
    /** 共募支店コード */
    private String jointBranchCode;
    
    /** 共募支店名 */
    private String jointBranchName;
    
    /** 残手数料  */
    private String feeBalance;
    
    /** 支払額  */
    private String jointRewardPrice;
    
}
