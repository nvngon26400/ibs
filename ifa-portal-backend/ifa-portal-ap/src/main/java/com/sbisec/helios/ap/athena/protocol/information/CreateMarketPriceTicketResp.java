package com.sbisec.helios.ap.athena.protocol.information;

/**
 * @Description マーケット価格情報サービス - マーケット価格取得用チケット登録API Response.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
public class CreateMarketPriceTicketResp {
    
    public CreateMarketPriceTicketResp() {
    
    }
    
    // 国コード
    private String countryCode;
    
    // 株価チケット
    private String priceTicket;
    
    // リアルタイム種別
    private String realTimeType;
    
    // ディレイ（分）
    private int minutesOfDelay;
    
    /**
     * @return countryCode 国コード
     */
    public String getCountryCode() {
        
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        
        this.countryCode = countryCode;
    }
    
    /**
     * @return priceTicket 株価チケット
     */
    public String getPriceTicket() {
        
        return priceTicket;
    }
    
    public void setPriceTicket(String priceTicket) {
        
        this.priceTicket = priceTicket;
    }
    
    /**
     * @return realTimeType リアルタイム種別
     */
    public String getRealTimeType() {
        
        return realTimeType;
    }
    
    public void setRealTimeType(String realTimeType) {
        
        this.realTimeType = realTimeType;
    }
    
    /**
     * @return minutesOfDelay ディレイ（分）
     */
    public int getMinutesOfDelay() {
        
        return minutesOfDelay;
    }
    
    public void setMinutesOfDelay(int minutesOfDelay) {
        
        this.minutesOfDelay = minutesOfDelay;
    }
}
