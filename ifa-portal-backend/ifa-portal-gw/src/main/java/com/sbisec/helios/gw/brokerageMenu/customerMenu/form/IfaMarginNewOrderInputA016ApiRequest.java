package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaMarginNewOrderInputA016ApiRequest {
    
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
