package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-04_1
 * 画面名：信用返済注文入力
 * 2024/04/08 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaMarginRepayOrderInputA016RequestDto {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 発注市場 */
    private String orderMarket;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 数量 */
    private String quantity;
    
    /** 期間.期間条件. */
    private String periodTerms;
    
    /** 期間.日付（全角半角）. */
    private String limit;
    
    /** 返済方法 */
    private String repayMethod;
    
    /** 返済順序 */
    private String repaymentOrder;
    
    /** 返済建玉カウント */
    private String repayPositionCount;
    
    /** 返済建玉明細 */
    private List<IfaMarginRepayOrderInputDtoRepayPositionDetail> repayPositionDetail;
    
    /** 注文種別 */
    private String orderKind;
    
    /** 通常/逆指値.執行方法 */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件 */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値） */
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値） */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価 */
    private String price;
    
    /** OCO1.執行方法 */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件 */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価 */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値） */
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値） */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値） */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価 */
    private String oco2Price;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String orderMethod;
    
    /** 確認項目.インサイダー確認 */
    private String checkInsider;
    
    /** 確認項目.SOR確認 */
    private String checkSor;
    
    /** 新規売買区分 */
    private String openTradeKbn;

    /** 弁済期限（算出） */
    private String paymentDeadlineCalculation;
    
}
