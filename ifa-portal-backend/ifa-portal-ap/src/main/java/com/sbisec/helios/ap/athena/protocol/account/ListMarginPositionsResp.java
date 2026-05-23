package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.Position;
import com.sbisec.helios.ap.athena.protocol.common.Page;

/**
 * 外国株式信用建玉明細一覧取得 Response
 * 
 * @author shuchen.xin
 * @date 03/10/2022
 */
public class ListMarginPositionsResp {
    
    public ListMarginPositionsResp() {
    
    }
    
    // 買建玉総額（外貨）
    private String frnBuyPositionSummary;
    
    // 売建玉総額（外貨）
    private String frnSellPositionSummary;
    
    // 総資産額（外貨）
    private String frnPositionSummary;
    
    // 買建玉総額（円貨）
    private String buyPositionSummary;
    
    // 売建玉総額（円貨）
    private String sellPositionSummary;
    
    // 総資産額（円貨）
    private String positionSummary;
    
    // 買建玉評価損益合計（外貨）
    private String frnBuyPositionEvaluationProfitLossSummary;
    
    // 売建玉評価損益合計（外貨）
    private String frnSellPositionEvaluationProfitLossSummary;
    
    // 評価損益合計（外貨）
    private String frnPositionEvaluationProfitLossSummary;
    
    // 買建玉評価損益合計（円貨）
    private String buyPositionEvaluationProfitLossSummary;
    
    // 売建玉評価損益合計（円貨）
    private String sellPositionEvaluationProfitLossSummary;
    
    // 評価損益合計（円貨）
    private String positionEvaluationProfitLossSummary;
    
    // 買建玉評価損益率（外貨）
    private String frnBuyPositionEvaluationProfitLossPercent;
    
    // 売建玉評価損益率（外貨）
    private String frnSellPositionEvaluationProfitLossPercent;
    
    // 総建玉評価損益率（外貨）
    private String frnPositionEvaluationProfitLossPercent;
    
    // 買建玉評価損益率（円貨）
    private String buyPositionEvaluationProfitLossPercent;
    
    // 売建玉評価損益率（円貨）
    private String sellPositionEvaluationProfitLossPercent;
    
    // 総建玉評価損益率（円貨）
    private String positionEvaluationProfitLossPercent;
    
    // 外国株式信用建玉明細一覧
    private List<Position> positions;
    
    // ページング
    private Page page;
    
    /**
     * @return 買建玉総額（外貨）
     */
    public String getFrnBuyPositionSummary() {
        
        return frnBuyPositionSummary;
    }
    
    /**
     * @param frnBuyPositionSummary the frnBuyPositionSummary to set
     */
    public void setFrnBuyPositionSummary(String frnBuyPositionSummary) {
        
        this.frnBuyPositionSummary = frnBuyPositionSummary;
    }
    
    /**
     * @return 売建玉総額（外貨）
     */
    public String getFrnSellPositionSummary() {
        
        return frnSellPositionSummary;
    }
    
    /**
     * @param frnSellPositionSummary the frnSellPositionSummary to set
     */
    public void setFrnSellPositionSummary(String frnSellPositionSummary) {
        
        this.frnSellPositionSummary = frnSellPositionSummary;
    }
    
    /**
     * @return 総資産額（外貨）
     */
    public String getFrnPositionSummary() {
        
        return frnPositionSummary;
    }
    
    /**
     * @param frnPositionSummary the frnPositionSummary to set
     */
    public void setFrnPositionSummary(String frnPositionSummary) {
        
        this.frnPositionSummary = frnPositionSummary;
    }
    
    /**
     * @return 買建玉総額（円貨）
     */
    public String getBuyPositionSummary() {
        
        return buyPositionSummary;
    }
    
    /**
     * @param buyPositionSummary the buyPositionSummary to set
     */
    public void setBuyPositionSummary(String buyPositionSummary) {
        
        this.buyPositionSummary = buyPositionSummary;
    }
    
    /**
     * @return 売建玉総額（円貨）
     */
    public String getSellPositionSummary() {
        
        return sellPositionSummary;
    }
    
    /**
     * @param sellPositionSummary the sellPositionSummary to set
     */
    public void setSellPositionSummary(String sellPositionSummary) {
        
        this.sellPositionSummary = sellPositionSummary;
    }
    
    /**
     * @return 総資産額（円貨）
     */
    public String getPositionSummary() {
        
        return positionSummary;
    }
    
    /**
     * @param positionSummary the positionSummary to set
     */
    public void setPositionSummary(String positionSummary) {
        
        this.positionSummary = positionSummary;
    }
    
    /**
     * @return 買建玉評価損益合計（外貨）
     */
    public String getFrnBuyPositionEvaluationProfitLossSummary() {
        
        return frnBuyPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @param frnBuyPositionEvaluationProfitLossSummary the
     * frnBuyPositionEvaluationProfitLossSummary to set
     */
    public void setFrnBuyPositionEvaluationProfitLossSummary(String frnBuyPositionEvaluationProfitLossSummary) {
        
        this.frnBuyPositionEvaluationProfitLossSummary = frnBuyPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @return 売建玉評価損益合計（外貨）
     */
    public String getFrnSellPositionEvaluationProfitLossSummary() {
        
        return frnSellPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @param frnSellPositionEvaluationProfitLossSummary the
     * frnSellPositionEvaluationProfitLossSummary to set
     */
    public void setFrnSellPositionEvaluationProfitLossSummary(String frnSellPositionEvaluationProfitLossSummary) {
        
        this.frnSellPositionEvaluationProfitLossSummary = frnSellPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @return 評価損益合計（外貨）
     */
    public String getFrnPositionEvaluationProfitLossSummary() {
        
        return frnPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @param frnPositionEvaluationProfitLossSummary the
     * frnPositionEvaluationProfitLossSummary to set
     */
    public void setFrnPositionEvaluationProfitLossSummary(String frnPositionEvaluationProfitLossSummary) {
        
        this.frnPositionEvaluationProfitLossSummary = frnPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @return 買建玉評価損益合計（円貨）
     */
    public String getBuyPositionEvaluationProfitLossSummary() {
        
        return buyPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @param buyPositionEvaluationProfitLossSummary the
     * buyPositionEvaluationProfitLossSummary to set
     */
    public void setBuyPositionEvaluationProfitLossSummary(String buyPositionEvaluationProfitLossSummary) {
        
        this.buyPositionEvaluationProfitLossSummary = buyPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @return 売建玉評価損益合計（円貨）
     */
    public String getSellPositionEvaluationProfitLossSummary() {
        
        return sellPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @param sellPositionEvaluationProfitLossSummary the
     * sellPositionEvaluationProfitLossSummary to set
     */
    public void setSellPositionEvaluationProfitLossSummary(String sellPositionEvaluationProfitLossSummary) {
        
        this.sellPositionEvaluationProfitLossSummary = sellPositionEvaluationProfitLossSummary;
    }
    
    /**
     * @return 評価損益合計（円貨）
     */
    public String getPositionEvaluationProfitLossSummary() {
        
        return positionEvaluationProfitLossSummary;
    }
    
    /**
     * @param positionEvaluationProfitLossSummary the
     * positionEvaluationProfitLossSummary to set
     */
    public void setPositionEvaluationProfitLossSummary(String positionEvaluationProfitLossSummary) {
        
        this.positionEvaluationProfitLossSummary = positionEvaluationProfitLossSummary;
    }
    
    /**
     * @return 外国株式信用建玉明細一覧
     */
    public List<Position> getPositions() {
        
        return positions;
    }
    
    /**
     * @param positions the positions to set
     */
    public void setPositions(List<Position> positions) {
        
        this.positions = positions;
    }
    
    /**
     * @return 買建玉評価損益率（外貨）
     */
    public String getFrnBuyPositionEvaluationProfitLossPercent() {
        
        return frnBuyPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @param frnBuyPositionEvaluationProfitLossPercent the frnBuyPositionEvaluationProfitLossPercent to set
     */
    public void setFrnBuyPositionEvaluationProfitLossPercent(String frnBuyPositionEvaluationProfitLossPercent) {
        
        this.frnBuyPositionEvaluationProfitLossPercent = frnBuyPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @return 売建玉評価損益率（外貨）
     */
    public String getFrnSellPositionEvaluationProfitLossPercent() {
        
        return frnSellPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @param frnSellPositionEvaluationProfitLossPercent the frnSellPositionEvaluationProfitLossPercent to set
     */
    public void setFrnSellPositionEvaluationProfitLossPercent(String frnSellPositionEvaluationProfitLossPercent) {
        
        this.frnSellPositionEvaluationProfitLossPercent = frnSellPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @return 総建玉評価損益率（外貨）
     */
    public String getFrnPositionEvaluationProfitLossPercent() {
        
        return frnPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @param frnPositionEvaluationProfitLossPercent the frnPositionEvaluationProfitLossPercent to set
     */
    public void setFrnPositionEvaluationProfitLossPercent(String frnPositionEvaluationProfitLossPercent) {
        
        this.frnPositionEvaluationProfitLossPercent = frnPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @return 買建玉評価損益率（円貨）
     */
    public String getBuyPositionEvaluationProfitLossPercent() {
        
        return buyPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @param buyPositionEvaluationProfitLossPercent the buyPositionEvaluationProfitLossPercent to set
     */
    public void setBuyPositionEvaluationProfitLossPercent(String buyPositionEvaluationProfitLossPercent) {
        
        this.buyPositionEvaluationProfitLossPercent = buyPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @return 売建玉評価損益率（円貨）
     */
    public String getSellPositionEvaluationProfitLossPercent() {
        
        return sellPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @param sellPositionEvaluationProfitLossPercent the sellPositionEvaluationProfitLossPercent to set
     */
    public void setSellPositionEvaluationProfitLossPercent(String sellPositionEvaluationProfitLossPercent) {
        
        this.sellPositionEvaluationProfitLossPercent = sellPositionEvaluationProfitLossPercent;
    }
    
    /**
     * @return 総建玉評価損益率（円貨）
     */
    public String getPositionEvaluationProfitLossPercent() {
        
        return positionEvaluationProfitLossPercent;
    }
    
    /**
     * @param positionEvaluationProfitLossPercent the positionEvaluationProfitLossPercent to set
     */
    public void setPositionEvaluationProfitLossPercent(String positionEvaluationProfitLossPercent) {
        
        this.positionEvaluationProfitLossPercent = positionEvaluationProfitLossPercent;
    }
    
    /**
     * @return page ページ
     */
    public Page getPage() {
        
        return page;
    }
    
    /**
     * @param page ページ
     */
    public void setPage(Page page) {
        
        this.page = page;
    }
}
