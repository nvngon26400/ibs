package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaMarginNewOrderConfirmSql002RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** 商品区分（全角半角）. */
    private String securityType;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 受注日. */
    private String orderDate;
    
    /** 受注時刻. */
    private String acceptTime;
    
    /** 種別（全角半角）. */
    private String shubetu;
    
    /** エラーコード（半角英数字）. */
    private String code;
    
    /** エラーメッセージ（全角半角）. */
    private String errMessage;
    
    /** 与信チェック用時価（数値(小数)）. */
    private String estimatePrice;
    
    /** 約定金額（概算）（数値(整数)）. */
    private String amount;
    
    /** 手数料（概算）（数値(整数)）. */
    private String commission;
    
    /** 消費税（概算）（数値(整数)）. */
    private String consumptionTax;
    
    /** 譲渡益税（概算）（数値(整数)）. */
    private String capitalGainTax;
    
    /** 精算金額（概算）（数値(整数)）. */
    private String netAmount;
    
    /** 諸経費（数値(整数)）. */
    private String cost;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受付有効期限（全角半角）. */
    private String acceptLimit;
    
    /** DONE 受付有効期限（全角半角）. */
    private String doneAcceptLimit;
    
    /** 手数料区分（採用）（全角半角）. */
    private String comIdR;
    
    /** 売却可能数量（数値(整数)）. */
    private String acPosition;
    
    /** 注文後の売却可能数量（数値(整数)）. */
    private String acPositionAfter;
    
    /** 買付可能金額（数値(整数)）. */
    private String acBalance;
    
    /** 注文後の買付可能金額（数値(整数)）. */
    private String acBalanceAfter;
    
    /** 注文入力市場（全角半角）. */
    private String orderedMarket;
    
    /** 取引不足額（数値(整数)）. */
    private String tradeDeficitAmount;
    
    /** ISA買付可能枠（数値(整数)）. */
    private String isaBuyLimit;
    
    /** ジュニアNISA振替金額（数値(整数)）. */
    private String jrnisaTransferAmount;
    
    /** SOR連携区分（全角半角）. */
    private String sorLinkKbn;
    
    /** 決済可能数量（数値(整数)）. */
    private String unclosedQuantity;
    
    /** 注文後の決済可能数量（数値(整数)）. */
    private String unclosedQuantityAfter;
    
    /** 建玉余力（数値(整数)）. */
    private String marginCapability;
    
    /** 注文後の建玉余力（数値(整数)）. */
    private String marginCapabilityAfter;
    
    /** 維持率. */
    private String domesticMarginActualGrntRate;
    
    /** 注文後維持率（数値(整数)）. */
    private String actualGrntRateAfter;
    
    /** 適用金利（数値(小数)）. */
    private String applicableInterestRate;
    
    /** 適用貸株料（数値(小数)）. */
    private String applicableStockLendingFees;
    
    /** プレミアム料（数値(整数)）. */
    private String premium;
    
    /** Ｔポイント（数値(整数)）. */
    private String tpoint;
    
    /** 利用後のＴポイント（数値(整数)）. */
    private String tpointAfter;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 更新者. */
    private String updateUser;
    
}
