package com.sbisec.helios.ap.athena.protocol.fstock.order;

/**
 * 余力サービス - 外国株式信用建余力サマリ取得API Response.
 * 
 * @author lie.zhang
 * @date: 11/10/2022
 */
public class GetMarginPowerReferenceResp {
    
    public GetMarginPowerReferenceResp() {
    
    }
    
    // 参考信用建余力
    private String referenceMarginBuyingPower;
    
    // 信用建余力
    private String marginBuyingPower;
    
    // 保護預り有価証券評価額
    private String protectionEvaluationAmount;
    
    // 出金余力
    private String paymentPossibleAmount;
    
    /**
     * @return 参考信用建余力
     */
    public String getReferenceMarginBuyingPower() {
        
        return referenceMarginBuyingPower;
    }
    
    public void setReferenceMarginBuyingPower(String referenceMarginBuyingPower) {
        
        this.referenceMarginBuyingPower = referenceMarginBuyingPower;
    }
    
    /**
     * @return 保護預り有価証券評価額
     */
    public String getProtectionEvaluationAmount() {
        
        return protectionEvaluationAmount;
    }
    
    public void setProtectionEvaluationAmount(String protectionEvaluationAmount) {
        
        this.protectionEvaluationAmount = protectionEvaluationAmount;
    }
    
    /**
     * @return 出金余力
     */
    public String getPaymentPossibleAmount() {
        
        return paymentPossibleAmount;
    }
    
    public void setPaymentPossibleAmount(String paymentPossibleAmount) {
        
        this.paymentPossibleAmount = paymentPossibleAmount;
    }
    
    /**
     * @return 信用建余力
     */
    public String getMarginBuyingPower() {
        
        return marginBuyingPower;
    }
    
    public void setMarginBuyingPower(String marginBuyingPower) {
        
        this.marginBuyingPower = marginBuyingPower;
    }
}
