package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 国内投信注文入力A010リスポンス
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA010ApiResponse {
    
    /** 種別（全角半角）. */
    private String shubetu;
    
    /** エラーコード（半角英数字）. */
    private String code;
    
    /** エラーメッセージ（全角半角）. */
    private String errMessage;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** コンプラランクチェック. */
    private IfaDomesticMutualFundOrderInputComplianceRankCheck complianceRankCheck;
    
    /** 短期売却確認アラートメッセージ（全角半角）. */
    private String shortTermSellConfirmMsg;
    
    /** 償還前売却確認アラートメッセージ（全角半角）. */
    private String preRedemptionSellConfirmAlertMsg;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 見積単価（数値(小数)）. */
    private String quoteUnitPrice;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 手数料/諸費用（数値(整数)）. */
    private String charge;
    
    /** 注文後のNISA投資可能枠(YYYY年). */
    private String nisaInvestableQuote;
    
    /** 消費税（数値(整数)）. */
    private String consumptionTax;
    
    /** 讓渡益税（数値(整数)）. */
    private String yieldTax;
    
    /** 精算金額（数値(整数)）. */
    private String settlementAmount;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日. */
    private String orderDate;
    
    /** 受注時刻. */
    private String orderDayTime;
    
    /** 受注数量（数値(整数)）. */
    private String orderQuantity;
    
    /** 利用ポイント（数値(整数)）. */
    private String point;
    
    /** リクエスト内容. */
    private IfaDomesticMutualFundOrderInputA010ApiRequest a010ApiRequest;
    
    /** 目論見書チェック区分. */
    private String dispatchId;
}
