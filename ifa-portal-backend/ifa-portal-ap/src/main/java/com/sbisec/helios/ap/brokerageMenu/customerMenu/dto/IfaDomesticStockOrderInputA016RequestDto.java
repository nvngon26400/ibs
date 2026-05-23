package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内株式注文入力A016リクエストDTO
 *
 * @author SCSK
 * 
 */
@Data
public class IfaDomesticStockOrderInputA016RequestDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場（全角）. */
    private String market;
    
    /** 口座. */
    private String account;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 期間.期間条件 */
    private String periodTerms;
    
    /** 期間.日付（全角半角）. */
    private String limit;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 注文種別（全角半角）. */
    private String orderKind;
    
    /** 通常/逆指値.執行方法（全角半角）. */
    private String normalPriceLimitReverseSasinariHouhou;
    
    /** 通常/逆指値.執行条件（全角半角）. */
    private String normalPriceLimitReverseSasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
    private String normalPriceLimitReverseTriggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値）（全角半角）. */
    private String normalPriceLimitReverseGyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)）. */
    private String normalPriceLimitReversePrice;
    
    /** OCO1.執行方法（全角半角）. */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件（全角半角）. */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)）. */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値）（全角半角）. */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（全角半角）. */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)）. */
    private String oco2Price;
    
    /** IFD1.執行方法（全角半角）. */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件（全角半角）. */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(整数)）. */
    private String ifd1TriggerPrice;
    
    /** IFD1.執行方法（逆指値）（全角半角）. */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(整数)）. */
    private String ifd1Price;
    
    /** IFD2.期間.期間条件. */
    private String ifd2PeriodTerms;
    
    /** IFD2.期間.日付（全角半角）. */
    private String ifd2Limit;
    
    /** IFD2.執行方法（全角半角）. */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件（全角半角）. */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(整数)）. */
    private String ifd2TriggerPrice;
    
    /** IFD2.執行方法（逆指値）（全角半角）. */
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数値(整数)）. */
    private String ifd2Price;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    private String confirmItemInsider;
    
    /** 確認項目.SOR確認（全角半角）. */
    private String confirmItemSor;
    
}
