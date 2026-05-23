package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 信用返済注文確認SQL003リクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmSql003RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** API応答.商品区分. */
    private String orderType;
    
    /** API応答.EC受注番号. */
    private String orderNum;
    
    /** API応答.受注日. */
    private String acceptDate;
    
    /** API応答.受注時刻. */
    private String acceptTime;
    
    /** API応答.種別. */
    private String shubetsu;
    
    /** API応答.エラーコード. */
    private String errorCode;
    
    /** API応答.エラーメッセージ. */
    private String errorMessage;
    
    /** API応答.与信チェック用時価. */
    private String estimatePrice;
    
    /** API応答.約定金額（概算）. */
    private String amount;
    
    /** API応答.手数料（概算）. */
    private String commission;
    
    /** API応答.消費税（概算）. */
    private String consumptionTax;
    
    /** API応答.譲渡益税（概算）. */
    private String capitalGainTax;
    
    /** API応答.精算金額（概算）. */
    private String netAmount;
    
    /** API応答.諸経費. */
    private String cost;
    
    /** API応答.約定予定日. */
    private String tradeDate;
    
    /** API応答.受渡予定日. */
    private String settlementDate;
    
    /** API応答.受付有効期限. */
    private String acceptLimit;
    
    /** API応答.手数料区分（採用）. */
    private String comIdR;
    
    /** API応答.注文入力市場. */
    private String orderedMarket;
    
    /** API応答.SOR連携区分. */
    private String sorLinkKbn;
    
    /** API応答.決済可能数量. */
    private String unclosedQuantity;
    
    /** API応答.注文後の決済可能数量. */
    private String unclosedQuantityAfter;
    
    /** API応答.建玉余力. */
    private String marginCapability;
    
    /** API応答.注文後の建玉余力. */
    private String marginCapabilityAfter;
    
    /** API応答.維持率. */
    private String actualGrntRate;
    
    /** API応答.注文後維持率. */
    private String actualGrntRateAfter;
    
    /** 更新者. */
    private String updateUser;
    
    /**
     * エラーフラグ. {@link com.sbisec.helios.ap.common.util.ApiWrapperUtil#isError ApiWrapperUtil.isError}を設定する.
     *
     * @see com.sbisec.helios.ap.common.util.ApiWrapperUtil#isError
     */
    private boolean isError;
}
