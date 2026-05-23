package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.DepositRateBasis;

/**
 * リアルタイム委託保証金率取得API Response
 *
 * @author xin.li
 * @version 1.0
 * @date 8/12/2022
 */
public class ListDepositRateBasisResp {
    
    public ListDepositRateBasisResp() {
    
    }
    
    // 委託保証金率
    private String marginRate;
    
    // 実質保証金
    private String actualMargin;
    
    // 委託保証金現金
    private String marginCash;
    
    // 代用有価証券評価額合計
    private String totalCollateralValue;
    
    // 建玉評価損益合計
    private String totalEvaluationAmount;
    
    // 決済損益合計
    private String totalClosedProfitLoss;
    
    // 支払諸経費合計
    private String totalExpenses;
    
    // 建代金合計
    private String totalPositionAmount;
    
    // リアルタイム委託保証金率リスト
    private List<DepositRateBasis> depositRateBasis;
    
    /**
     * @return 委託保証金率
     */
    public String getMarginRate() {
        
        return marginRate;
    }
    
    public void setMarginRate(String marginRate) {
        
        this.marginRate = marginRate;
    }
    
    /**
     * @return 実質保証金
     */
    public String getActualMargin() {
        
        return actualMargin;
    }
    
    public void setActualMargin(String actualMargin) {
        
        this.actualMargin = actualMargin;
    }
    
    /**
     * @return 委託保証金現金
     */
    public String getMarginCash() {
        
        return marginCash;
    }
    
    public void setMarginCash(String marginCash) {
        
        this.marginCash = marginCash;
    }
    
    /**
     * @return 代用有価証券評価額合計
     */
    public String getTotalCollateralValue() {
        
        return totalCollateralValue;
    }
    
    public void setTotalCollateralValue(String totalCollateralValue) {
        
        this.totalCollateralValue = totalCollateralValue;
    }
    
    /**
     * @return 建玉評価損益合計
     */
    public String getTotalEvaluationAmount() {
        
        return totalEvaluationAmount;
    }
    
    public void setTotalEvaluationAmount(String totalEvaluationAmount) {
        
        this.totalEvaluationAmount = totalEvaluationAmount;
    }
    
    /**
     * @return 決済損益合計
     */
    public String getTotalClosedProfitLoss() {
        
        return totalClosedProfitLoss;
    }
    
    public void setTotalClosedProfitLoss(String totalClosedProfitLoss) {
        
        this.totalClosedProfitLoss = totalClosedProfitLoss;
    }
    
    /**
     * @return 支払諸経費合計
     */
    public String getTotalExpenses() {
        
        return totalExpenses;
    }
    
    public void setTotalExpenses(String totalExpenses) {
        
        this.totalExpenses = totalExpenses;
    }
    
    /**
     * @return 建代金合計
     */
    public String getTotalPositionAmount() {
        
        return totalPositionAmount;
    }
    
    public void setTotalPositionAmount(String totalPositionAmount) {
        
        this.totalPositionAmount = totalPositionAmount;
    }
    
    /**
     * @return リアルタイム委託保証金率リスト
     */
    public List<DepositRateBasis> getDepositRateBasis() {
        
        return depositRateBasis;
    }
    
    public void setDepositRateBasis(List<DepositRateBasis> depositRateBasis) {
        
        this.depositRateBasis = depositRateBasis;
    }
    
}
