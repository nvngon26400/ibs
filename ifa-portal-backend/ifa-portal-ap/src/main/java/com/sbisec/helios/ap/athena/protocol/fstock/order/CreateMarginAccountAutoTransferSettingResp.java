package com.sbisec.helios.ap.athena.protocol.fstock.order;

/**
 * 口座情報サービス - 信用口座自動振替設定登録API Response.
 * 
 * @author xin.huang
 */
public class CreateMarginAccountAutoTransferSettingResp {
    
    public CreateMarginAccountAutoTransferSettingResp() {
    
    }
    
    // 建余力不足 自動振替設定(米ドル)
    private Boolean marginBuyingPowerShortfallCash;
    
    // 建余力不足 自動振替設定(米国株式)
    private Boolean marginBuyingPowerShortfallSecurities;
    
    // 保証金不足 自動振替設定(米ドル)
    private Boolean marginShortfallCash;
    
    // 保証金不足 自動振替設定(米国株式)
    private Boolean marginShortfallSecurities;
    
    // 現物買付時 株式自動振替先設定
    private String depositType;
    
    // 【区分値変換後】建余力不足 自動振替設定(米ドル)
    private String convMarginBuyingPowerShortfallCash;
    
    // 【区分値変換後】建余力不足 自動振替設定(米国株式)
    private String convMarginBuyingPowerShortfallSecurities;
    
    // 【区分値変換後】保証金不足 自動振替設定(米ドル)
    private String convMarginShortfallCash;
    
    // 【区分値変換後】保証金不足 自動振替設定(米国株式)
    private String convMarginShortfallSecurities;
    
    public Boolean getMarginBuyingPowerShortfallCash() {
        
        return marginBuyingPowerShortfallCash;
    }
    
    public void setMarginBuyingPowerShortfallCash(Boolean marginBuyingPowerShortfallCash) {
        
        this.marginBuyingPowerShortfallCash = marginBuyingPowerShortfallCash;
    }
    
    public Boolean getMarginBuyingPowerShortfallSecurities() {
        
        return marginBuyingPowerShortfallSecurities;
    }
    
    public void setMarginBuyingPowerShortfallSecurities(Boolean marginBuyingPowerShortfallSecurities) {
        
        this.marginBuyingPowerShortfallSecurities = marginBuyingPowerShortfallSecurities;
    }
    
    public Boolean getMarginShortfallCash() {
        
        return marginShortfallCash;
    }
    
    public void setMarginShortfallCash(Boolean marginShortfallCash) {
        
        this.marginShortfallCash = marginShortfallCash;
    }
    
    public Boolean getMarginShortfallSecurities() {
        
        return marginShortfallSecurities;
    }
    
    public void setMarginShortfallSecurities(Boolean marginShortfallSecurities) {
        
        this.marginShortfallSecurities = marginShortfallSecurities;
    }
    
    public String getDepositType() {
        
        return depositType;
    }
    
    public void setDepositType(String depositType) {
        
        this.depositType = depositType;
    }
    
    public String getConvMarginBuyingPowerShortfallCash() {
        
        return convMarginBuyingPowerShortfallCash;
    }
    
    public void setConvMarginBuyingPowerShortfallCash(String convMarginBuyingPowerShortfallCash) {
        
        this.convMarginBuyingPowerShortfallCash = convMarginBuyingPowerShortfallCash;
    }
    
    public String getConvMarginBuyingPowerShortfallSecurities() {
        
        return convMarginBuyingPowerShortfallSecurities;
    }
    
    public void setConvMarginBuyingPowerShortfallSecurities(String convMarginBuyingPowerShortfallSecurities) {
        
        this.convMarginBuyingPowerShortfallSecurities = convMarginBuyingPowerShortfallSecurities;
    }
    
    public String getConvMarginShortfallCash() {
        
        return convMarginShortfallCash;
    }
    
    public void setConvMarginShortfallCash(String convMarginShortfallCash) {
        
        this.convMarginShortfallCash = convMarginShortfallCash;
    }
    
    public String getConvMarginShortfallSecurities() {
        
        return convMarginShortfallSecurities;
    }
    
    public void setConvMarginShortfallSecurities(String convMarginShortfallSecurities) {
        
        this.convMarginShortfallSecurities = convMarginShortfallSecurities;
    }
    
}
