package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

/**
 * 商品保有証券資産情報 Dto.
 *
 * @author xiu.yan
 * @date 02/15/2022
 */
public class SecuritiesBalances implements Serializable {
    
    private static final long serialVersionUID = -1273653296495010680L;
    
    public SecuritiesBalances() {
    
    }
    
    // 国コード
    private String countryCode;
    
    // 通貨コード
    private String currencyCode;
    
    // 預り区分
    private String specificAccountCode;
    
    // 銘柄情報
    private Securities securities;
    
    // 市場情報
    private Market market;
    
    // 保有株数
    private String securitiesQuantity;
    
    // 売却注文中
    private String sellFixedOrderQuantity;
    
    // 取得（参考）単価（外貨）
    private String frnAcquisitionPrice;
    
    // 取得（参考）単価（円貨）
    private String acquisitionPrice;
    
    // 取得金額（外貨）
    private String frnAcquisitionAmount;
    
    // 取得金額（円貨）
    private String acquisitionAmount;
    
    // 取得為替レート
    private String acquisitionExchangeRate;
    
    // 現在値（円換算額）
    private String yenLastPrice;
    
    // 純資産価格
    private String principalPrice;
    
    // 株価情報
    private PriceData stockPrice;
    
    // 評価損益
    private EvaluationProfitLoss evaluationProfitLoss;
    
    // 注意銘柄判定
    private Boolean attentionSecurities;
    
    // 保護区分
    private String depositType;
    
    // 銘柄上場ステータス
    private String listedSecuritiesStatus;
    
    /**
     * @return 国コード
     */
    public String getCountryCode() {
        
        return countryCode;
    }
    
    /**
     * @param countryCode 国コード
     */
    public void setCountryCode(String countryCode) {
        
        this.countryCode = countryCode;
    }
    
    /**
     * @return 通貨コード
     */
    public String getCurrencyCode() {
        
        return currencyCode;
    }
    
    /**
     * @param currencyCode 通貨コード
     */
    public void setCurrencyCode(String currencyCode) {
        
        this.currencyCode = currencyCode;
    }
    
    /**
     * @return 預り区分
     */
    public String getSpecificAccountCode() {
        
        return specificAccountCode;
    }
    
    /**
     * @param specificAccountCode 預り区分
     */
    public void setSpecificAccountCode(String specificAccountCode) {
        
        this.specificAccountCode = specificAccountCode;
    }
    
    /**
     * @return 銘柄情報
     */
    public Securities getSecurities() {
        
        return securities;
    }
    
    /**
     * @param securities 銘柄情報
     */
    public void setSecurities(Securities securities) {
        
        this.securities = securities;
    }
    
    /**
     * @return 市場情報
     */
    public Market getMarket() {
        
        return market;
    }
    
    /**
     * @param market 市場情報
     */
    public void setMarket(Market market) {
        
        this.market = market;
    }
    
    /**
     * @return 保有株数
     */
    public String getSecuritiesQuantity() {
        
        return securitiesQuantity;
    }
    
    /**
     * @param securitiesQuantity 保有株数
     */
    public void setSecuritiesQuantity(String securitiesQuantity) {
        
        this.securitiesQuantity = securitiesQuantity;
    }
    
    /**
     * @return 売却注文中
     */
    public String getSellFixedOrderQuantity() {
        
        return sellFixedOrderQuantity;
    }
    
    /**
     * @param sellFixedOrderQuantity 売却注文中
     */
    public void setSellFixedOrderQuantity(String sellFixedOrderQuantity) {
        
        this.sellFixedOrderQuantity = sellFixedOrderQuantity;
    }
    
    /**
     * @return 取得（参考）単価（外貨）
     */
    public String getFrnAcquisitionPrice() {
        
        return frnAcquisitionPrice;
    }
    
    /**
     * @param frnAcquisitionPrice 取得（参考）単価（外貨）
     */
    public void setFrnAcquisitionPrice(String frnAcquisitionPrice) {
        
        this.frnAcquisitionPrice = frnAcquisitionPrice;
    }
    
    /**
     * @return 取得（参考）単価（円貨）
     */
    public String getAcquisitionPrice() {
        
        return acquisitionPrice;
    }
    
    /**
     * @param acquisitionPrice 取得（参考）単価（円貨）
     */
    public void setAcquisitionPrice(String acquisitionPrice) {
        
        this.acquisitionPrice = acquisitionPrice;
    }
    
    /**
     * @return 取得金額（外貨）
     */
    public String getFrnAcquisitionAmount() {
        
        return frnAcquisitionAmount;
    }
    
    /**
     * @param receiveAmountValue 取得金額（外貨）
     */
    public void setFrnAcquisitionAmount(String frnAcquisitionAmount) {
        
        this.frnAcquisitionAmount = frnAcquisitionAmount;
    }
    
    /**
     * @return 取得金額（円貨）
     */
    public String getAcquisitionAmount() {
        
        return acquisitionAmount;
    }
    
    /**
     * @param acquisitionAmount 取得金額（円貨）
     */
    public void setAcquisitionAmount(String acquisitionAmount) {
        
        this.acquisitionAmount = acquisitionAmount;
    }
    
    /**
     * @return 取得為替レート
     */
    public String getAcquisitionExchangeRate() {
        
        return acquisitionExchangeRate;
    }
    
    /**
     * @param acquisitionExchangeRate 取得為替レート
     */
    public void setAcquisitionExchangeRate(String acquisitionExchangeRate) {
        
        this.acquisitionExchangeRate = acquisitionExchangeRate;
    }
    
    /**
     * @return 現在値（円換算額）
     */
    public String getYenLastPrice() {
        
        return yenLastPrice;
    }
    
    /**
     * @param yenLastPrice 現在値（円換算額）
     */
    public void setYenLastPrice(String yenLastPrice) {
        
        this.yenLastPrice = yenLastPrice;
    }
    
    /**
     * @return 純資産価格
     */
    public String getPrincipalPrice() {
        
        return principalPrice;
    }
    
    /**
     * @param settleBkBalance 純資産価格
     */
    public void setPrincipalPrice(String principalPrice) {
        
        this.principalPrice = principalPrice;
    }
    
    /**
     * @return 株価情報
     */
    public PriceData getStockPrice() {
        
        return stockPrice;
    }
    
    /**
     * @param stockPrice 株価情報
     */
    public void setStockPrice(PriceData stockPrice) {
        
        this.stockPrice = stockPrice;
    }
    
    /**
     * @return 評価損益
     */
    public EvaluationProfitLoss getEvaluationProfitLoss() {
        
        return evaluationProfitLoss;
    }
    
    /**
     * @param evaluationProfitLoss 評価損益
     */
    public void setEvaluationProfitLoss(EvaluationProfitLoss evaluationProfitLoss) {
        
        this.evaluationProfitLoss = evaluationProfitLoss;
    }
    
    /**
     * @return 注意銘柄判定
     */
    public Boolean getAttentionSecurities() {
        
        return attentionSecurities;
    }
    
    /**
     * @param attentionSecurities 注意銘柄判定
     */
    public void setAttentionSecurities(Boolean attentionSecurities) {
        
        this.attentionSecurities = attentionSecurities;
    }
    
    /**
     * @return 銘柄上場ステータス
     */
    public String getListedSecuritiesStatus() {
        
        return listedSecuritiesStatus;
    }
    
    public void setListedSecuritiesStatus(String listedSecuritiesStatus) {
        
        this.listedSecuritiesStatus = listedSecuritiesStatus;
    }
    
    /**
     * @return 保護区分
     */
    public String getDepositType() {
        
        return depositType;
    }
    
    /**
     * @param depositType 保護区分
     */
    public void setDepositType(String depositType) {
        
        this.depositType = depositType;
    }
}
