package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaDomesticStockOrderCorrectInputA010BeforeCorrectPriceDtoResponse {
    
    /** 訂正前価格.通常/逆指値.執行方法 */
    private String sasinariHouhou;
    
    /** 訂正前価格.通常/逆指値.執行条件 */
    private String sasinariJyouken;
    
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値） */
    private String triggerPrice;
    
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン */
    private String stopOrderPriceText2;
    
    /** 訂正前価格.通常/逆指値.執行方法（逆指値） */
    private String gyakusasiHouhou;
    
    /** 訂正前価格.通常/逆指値.注文単価 */
    private String price;
    
    /** 訂正前価格.OCO1.執行方法 */
    private String oco1SasinariHouhou;
    
    /** 訂正前価格.OCO1.執行条件 */
    private String oco1SasinariJyouken;
    
    /** 訂正前価格.OCO1.注文単価 */
    private String oco1Price;
    
    /** 訂正前価格.OCO2.発火条件価格（逆指値） */
    private String oco2TriggerPrice;
    
    /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン */
    private String oco2StopOrderPriceText2;
    
    /** 訂正前価格.OCO2.執行方法（逆指値） */
    private String oco2GyakusasiHouhou;
    
    /** 訂正前価格.OCO2.執行条件（逆指値） */
    private String oco2GyakusasiJyouken;
    
    /** 訂正前価格.OCO2.注文単価 */
    private String oco2Price;
    
    /** 訂正前価格.IFD1.執行方法 */
    private String ifd1SasinariHouhou;
    
    /** 訂正前価格.IFD1.執行条件 */
    private String ifd1SasinariJyouken;
    
    /** 訂正前価格.IFD1.発火条件価格（逆指値） */
    private String ifd1TriggerPrice;
    
    /** 訂正前価格.IFD1.発火条件価格（逆指値）ゾーン */
    private String ifd1StopOrderPriceText2;
    
    /** 訂正前価格.IFD1.執行方法（逆指値） */
    private String ifd1GyakusasiHouhou;
    
    /** 訂正前価格.IFD1.注文単価 */
    private String ifd1Price;
    
    /** 訂正前価格.IFD2.期間.日付 */
    private String ifd2Limit;
    
    /** 訂正前価格.IFD2.執行方法 */
    private String ifd2SasinariHouhou;
    
    /** 訂正前価格.IFD2.執行条件 */
    private String ifd2SasinariJyouken;
    
    /** 訂正前価格.IFD2.発火条件価格（逆指値） */
    private String ifd2TriggerPrice;
    
    /** 訂正前価格.IFD2.発火条件価格（逆指値）ゾーン */
    private String ifd2StopOrderPriceText2;
    
    /** 訂正前価格.IFD2.執行方法（逆指値） */
    private String ifd2GyakusasiHouhou;
    
    /** 訂正前価格.IFD2.注文単価 */
    private String ifd2Price;
    
}
