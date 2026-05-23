package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import lombok.Data;

@Data
public class IfaJointSubscriptTradeListA002ApiResponse {

    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 部店. */
    private String buten;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 取引コース. */
    private String customerAttributeName;
    
    /** 顧客名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名(カナ). */
    private String customerNameKana;
    
    /** 共募支店コード（数字）. */
    private String jointBranchCode;
    
    /** 共募支店名（全角半角）. */
    private String jointBranchName;
    
    /** 証券種別. */
    private String securityClass;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 商品ランク（半角英数字）. */
    private String productRank;
    
    /** 取引種別（全角半角）. */
    private String tradeTypeName;
    
    /** 預り区分.*/
    private String depositName;
    
    /** 約定日. */
    private String tradeDate;
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 単価（全角半角）. */
    private String price;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 約定金額（円貨）. */
    private String contractAmountJpyAmount;
    
    /** 手数料（円貨）（数値(小数)）. */
    private String yenFee;
    
    /** 受渡金額（円貨）（数値(小数)）. */
    private String yenDeliveryAmount;
    
    /** 通貨. */
    private String currency;
    
    /** 為替レート（数値(小数)）. */
    private String fxRate;
    
    /** 約定金額（外貨）（数値(小数)）. */
    private String contractAmountForeign;
    
    /** 受渡金額（外貨）（数値(小数)）. */
    private String foreignDeliveryAmount;
    
    /** 残手数料. */
    private String feeBalance;
    
    /** 支払額. */
    private String jointRewardPrice;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 営業員名（全角半角）. */
    private String brokerChargeName;
    
    /** 債券　媒介/非媒介. */
    private String bondIntermediary;
    
    /** 米国株　店頭/委託. */
    private String usStockStoreEntrust;

}
