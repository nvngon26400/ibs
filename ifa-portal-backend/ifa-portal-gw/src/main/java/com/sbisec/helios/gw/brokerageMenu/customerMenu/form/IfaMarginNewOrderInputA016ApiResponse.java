package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaMarginNewOrderInputA016ApiResponse {
    
    /** 種別. */
    private String shubetu;
    
    /** エラーコード. */
    private String code;
    
    /** エラーメッセージ. */
    private String errMessage;
    
    /** 注意情報アラート. */
    private List<String> noticeInfoAlert;
    
    /** お知らせアラート. */
    private List<String> noticeAlert;
    
    /** 内部者確認メッセージ. */
    private List<String> insiderErrorMsg;
    
    /** 空売り規制抵触メッセージ. */
    private List<String> shortSellingRegulationConflictMessage;
    
    /** 銘柄名. */
    private String brandName;
    
    /** 取引注意情報（銘柄）メッセージ. */
    private List<String> tradeNoticeInfoBrandMsg;
    
    /** プレミアム空売り区分. */
    private String premiumShortSaleCcategory;
    
    /** 見積単価. */
    private String quoteUnitPrice;
    
    /** 約定金額. */
    private String contractAmount;
    
    /** 手数料/諸費用. */
    private String charge;
    
    /** 消費税. */
    private String consumptionTax;
    
    /** 適用金利. */
    private String applicableInterestRate;
    
    /** 適用貸株料. */
    private String applicableStockLendingFees;
    
    /** 精算金額. */
    private String settlementAmount;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** 注文入力市場. */
    private String orderedMarket;
    
    //リクエスト
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場. */
    private String market;
    
    /** 取引種別. */
    private String tradeCd;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 期間.期間条件. */
    private String periodRadio;
    
    /** 期間.日付. */
    private String limit;
    
    /** 注文種別. */
    private String orderKind;
    
    /** 通常/逆指値.執行方法. */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件. */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数字）. */
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値）. */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数字）. */
    private String price;
    
    /** 信用取引区分. */
    private String marginTradeTypeText;
    
    /** OCO1.執行方法. */
    private String oco1OrderExecuteMethodList;
    
    /** OCO1.執行条件. */
    private String oco1LimitExecutionConditionList;
    
    /** OCO1.注文単価（数字）. */
    private String oco1DomesticLimitPrice;
    
    /** OCO2.発火条件価格（逆指値）（数字）. */
    private String oco2DomesticStopOrderPrice;
    
    /** OCO2.執行方法（逆指値）. */
    private String oco2StopOrderExecuteMethodList;
    
    /** OCO2.執行条件（逆指値）. */
    private String oco2StopOrderMarketExecutionConditionList;
    
    /** OCO2.注文単価（数字）. */
    private String oco2DomesticLimitPrice;
    
    /** IFD1.執行方法. */
    private String ifd1OrderExecuteMethodList;
    
    /** IFD1.執行条件. */
    private String ifd1LimitExecutionConditionList;
    
    /** IFD1.発火条件価格（逆指値）（数字）. */
    private String ifd1DomesticStopOrderPrice;
    
    /** IFD1.執行方法（逆指値）. */
    private String ifd1StopOrderExecuteMethodList;
    
    /** IFD1.注文単価（数字）. */
    private String ifd1DomesticLimitPrice;
    
    /** IFD2.期間.期間条件. */
    private String ifd2PeriodRadio;
    
    /** IFD2.期間.日付. */
    private String ifd2Limit;
    
    /** IFD2.執行方法. */
    private String ifd2OrderExecuteMethodList;
    
    /** IFD2.執行条件. */
    private String ifd2LimitExecutionConditionList;
    
    /** IFD2.発火条件価格（逆指値）（数字）. */
    private String ifd2DomesticStopOrderPrice;
    
    /** IFD2.執行方法（逆指値）. */
    private String ifd2StopOrderExecuteMethodList;
    
    /** IFD2.注文単価（数字）. */
    private String ifd2DomesticLimitPrice;
    
    /** 勧誘区分. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 確認項目.インサイダー確認. */
    private String checkInsider;
    
    /** 確認項目.SOR確認. */
    private String checkSor;
}
