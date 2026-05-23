package com.sbisec.helios.ap.athena.protocol.common;

import java.io.Serializable;

/**
 * NISA投資枠 Dto.
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 5/28/2021
 */
public class NisaLimit implements Serializable {
    
    private static final long serialVersionUID = -1740746837097968914L;
    
    public NisaLimit() {
        
    }
    
    // 買付可能枠年(yyyy)
    private Integer nisaBuyYear;
    
    // 買付可能枠
    private String nisaBuyLimitAmount;
    
    // 総合NISA(成長投資枠）買付可能枠
    private String generalNisaGrowthInvestmentBuyLimitAmount;
    
    // 総合NISA(つみたて）買付可能枠
    private String generalNisaTsumitateBuyLimitAmount;
    
    // NISA区分JrNISA場合、設定なし
    private String nisaType;
    
    // NISA投資可能枠利用停止情報
    private String nisaBuyLimitStop;
    
    // 口座分類
    private String accountKind;
    
    /**
     * @return 買付可能枠年(yyyy)
     */
    public Integer getNisaBuyYear() {
        
        return nisaBuyYear;
    }
    
    /**
     * 買付可能枠年(yyyy)
     * 
     * @param nisaBuyYear
     */
    public void setNisaBuyYear(Integer nisaBuyYear) {
        
        this.nisaBuyYear = nisaBuyYear;
    }
    
    /**
     * @return 買付可能枠
     */
    public String getNisaBuyLimitAmount() {
        
        return nisaBuyLimitAmount;
    }
    
    /**
     * 買付可能枠
     * 
     * @param nisaBuyLimitAmount
     */
    public void setNisaBuyLimitAmount(String nisaBuyLimitAmount) {
        
        this.nisaBuyLimitAmount = nisaBuyLimitAmount;
    }
    
    /**
     * @return 総合NISA(成長投資枠）買付可能枠
     */
    public String getGeneralNisaGrowthInvestmentBuyLimitAmount() {
        
        return generalNisaGrowthInvestmentBuyLimitAmount;
    }
    
    /**
     * 総合NISA(成長投資枠）買付可能枠
     *
     * @param generalNisaGrowthInvestmentBuyLimitAmount 総合NISA(成長投資枠）買付可能枠
     */
    public void setGeneralNisaGrowthInvestmentBuyLimitAmount(String generalNisaGrowthInvestmentBuyLimitAmount) {
        
        this.generalNisaGrowthInvestmentBuyLimitAmount = generalNisaGrowthInvestmentBuyLimitAmount;
    }
    
    /**
     * @return 総合NISA(つみたて）買付可能枠
     */
    public String getGeneralNisaTsumitateBuyLimitAmount() {
        
        return generalNisaTsumitateBuyLimitAmount;
    }
    
    /**
     * 総合NISA(つみたて）買付可能枠
     *
     * @param generalNisaTsumitateBuyLimitAmount 総合NISA(つみたて）買付可能枠
     */
    public void setGeneralNisaTsumitateBuyLimitAmount(String generalNisaTsumitateBuyLimitAmount) {
        
        this.generalNisaTsumitateBuyLimitAmount = generalNisaTsumitateBuyLimitAmount;
    }
    
    /**
     * @return NISA区分JrNISA場合、設定なし
     */
    public String getNisaType() {
        
        return nisaType;
    }
    
    /**
     * NISA区分JrNISA場合、設定なし
     * 
     * @param nisaType
     */
    public void setNisaType(String nisaType) {
        
        this.nisaType = nisaType;
    }
    
    /**
     * @return NISA投資可能枠利用停止情報
     */
    public String getNisaBuyLimitStop() {
        
        return nisaBuyLimitStop;
    }
    
    /**
     * NISA投資可能枠利用停止情報
     * 
     * @param nisaBuyLimitStop
     */
    public void setNisaBuyLimitStop(String nisaBuyLimitStop) {
        
        this.nisaBuyLimitStop = nisaBuyLimitStop;
    }
    
    /**
     * 口座分類
     *
     * @return
     */
    public String getAccountKind() {
        
        return accountKind;
    }
    
    /**
     * 口座分類
     * 
     * @param accountKind
     */
    public void setAccountKind(String accountKind) {
        
        this.accountKind = accountKind;
    }
}
