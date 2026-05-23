package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA016RequestDto;

import lombok.Data;

/**
 * 国内株式注文入力A016レスポンスDTO
 *
 * @author SCSK
 * 
 */
@Data
public class IfaDomesticStockOrderInputA016ApiResponse {
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
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
    private IfaDomesticStockOrderInputApiResponseComplianceRankCheck complianceRankCheck;
    
    /** 内部者確認メッセージ. */
    private String insiderConfirmMsg;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** ジュニアNISA振替金額（数値(整数)）. */
    private String jrnisaTransferAmount;
    
    /** 見積単価（数値(小数)）. */
    private String quoteUnitPrice;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 手数料/諸費用（数値(整数)）. */
    private String charge;
    
    /** 消費税（数値(整数)）. */
    private String consumptionTax;
    
    /** 讓渡益税（数値(整数)）. */
    private String yieldTax;
    
    /** 精算金額（数値(整数)）. */
    private String settlementAmount;
    
    /** 投資可能枠. */
    private String investableQuote;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** 注文入力市場（全角半角）. */
    private String orderedMarket;
    
    /** リクエスト内容. */
    private IfaDomesticStockOrderInputA016RequestDto requestContents;
    
}
