package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

/**
 * 外国株式信用建玉サマリ Dto.
 * 
 * @author yunhui.zhao
 * @date 03/11/2022
 */
public class Position implements Serializable {
    
    private static final long serialVersionUID = -181685067076191022L;
    
    public Position() {
    
    }
    
    // 商品コード
    private String productCode;
    
    // 国コード
    private String countryCode;
    
    // 銘柄情報
    private Securities securities;
    
    // 市場情報
    private Market market;
    
    // 株価情報
    private PriceData priceData;
    
    // 売買区分
    private String buySellCode;
    
    // 信用期日
    private String marginCloseLimitType;
    
    // 株取引区分
    private String stockTradeType;
    
    // 預り区分
    private String specificAccountCode;
    
    // 建玉残数量
    private String quantity;
    
    // 決済注文中数量
    private String closingQuantity;
    
    // 決済注文可能数量
    private String closableQuantity;
    
    // 新規建不足数量
    private String initialMarginShortfallQuantity;
    
    // 現在値（円貨）
    private String lastPrice;
    
    // 新規建単価（外貨）
    private String frnPositionPrice;
    
    // 新規建単価（円貨）
    private String positionPrice;
    
    // 新規建代金（外貨）
    private String frnPositionAmount;
    
    // 新規建代金（円貨）
    private String positionAmount;
    
    // 現地新規約定日(yyyy-MM-dd)
    private String frnTradeDate;
    
    // 国内新規約定日(yyyy-MM-dd)
    private String tradeDate;
    
    // 現地新規受渡日(yyyy-MM-dd)
    private String frnValueDate;
    
    // 国内新規受渡日(yyyy-MM-dd)
    private String valueDate;
    
    // 現地決済期日(yyyy-MM-dd)
    private String frnCloseLimitDate;
    
    // 国内決済期日(yyyy-MM-dd)
    private String closeLimitDate;
    
    // 現地返済期限(yyyy-MM-dd)
    private String frnCloseableExpirationDate;
    
    // 新規建手数料（外貨）
    private String frnOpenCommission;
    
    // 新規建手数料（円貨）
    private String openCommission;
    
    // 新規建手数料消費税（外貨）
    private String frnOpenCommissionTax;
    
    // 新規建手数料消費税（円貨）
    private String openCommissionTax;
    
    // 金利（外貨）
    private String frnInterestRate;
    
    // 金利（円貨）
    private String interestRate;
    
    // 順日歩（外貨）
    private String frnPayableInterest;
    
    // 順日歩（円貨）
    private String payableInterest;
    
    // 逆日歩（外貨）
    private String frnReceivableInterest;
    
    // 逆日歩（円貨）
    private String receivableInterest;
    
    // 貸株料（外貨）
    private String frnLendingFee;
    
    // 貸株料（円貨）
    private String lendingFee;
    
    // 管理料（外貨）
    private String frnManagementFee;
    
    // 管理料（円貨）
    private String managementFee;
    
    // 管理料消費税（外貨）
    private String frnManagementFeeTax;
    
    // 管理料消費税（円貨）
    private String managementFeeTax;
    
    // 書換料（外貨）
    private String frnTransferFee;
    
    // 書換料（円貨）
    private String transferFee;
    
    // 書換料消費税（外貨）
    private String frnTransferFeeTax;
    
    // 書換料消費税（円貨）
    private String transferFeeTax;
    
    // 直近値洗評価単価（外貨）
    private String frnLastValuationPrice;
    
    // 直近値洗評価単価（円貨）
    private String lastValuationPrice;
    
    // 直近値洗評価損益（外貨）
    private String frnLastUnrealizedPl;
    
    // 直近値洗評価損益（円貨）
    private String lastUnrealizedPl;
    
    // HYPER料（外貨）
    private String frnHyperFee;
    
    // HYPER料（円貨）
    private String hyperFee;
    
    // 諸経費合計額（外貨）
    private String frnTotalExpenses;
    
    // 諸経費合計額（円貨）
    private String totalExpenses;
    
    // 新規建為替レート
    private String openExchangeRate;
    
    // 増担保規制建玉フラグ
    private Boolean needAdditionalCollateral;
    
    // 【区分値変換後】増担保規制建玉フラグ
    private String convNeedAdditionalCollateral;
    
    // 建玉必要保証金率
    private String positionMarginRate;
    
    // 返却必要保証金率
    private String withdrawableMarginRate;
    
    // 現金必要保証金率
    private String cashMarginRate;
    
    // 評価情報
    private EvaluationProfitLoss evaluationProfitLoss;
    
    // 増担保
    private String additionalCollateral;
    
    // 銘柄上場ステータス
    private String listedSecuritiesStatus;
    
    public Securities getSecurities() {
        
        return securities;
    }
    
    public void setSecurities(Securities securities) {
        
        this.securities = securities;
    }
    
    public Market getMarket() {
        
        return market;
    }
    
    public void setMarket(Market market) {
        
        this.market = market;
    }
    
    public PriceData getPriceData() {
        
        return priceData;
    }
    
    public void setPriceData(PriceData priceData) {
        
        this.priceData = priceData;
    }
    
    public String getBuySellCode() {
        
        return buySellCode;
    }
    
    public void setBuySellCode(String buySellCode) {
        
        this.buySellCode = buySellCode;
    }
    
    public String getMarginCloseLimitType() {
        
        return marginCloseLimitType;
    }
    
    public void setMarginCloseLimitType(String marginCloseLimitType) {
        
        this.marginCloseLimitType = marginCloseLimitType;
    }
    
    public String getStockTradeType() {
        
        return stockTradeType;
    }
    
    public void setStockTradeType(String stockTradeType) {
        
        this.stockTradeType = stockTradeType;
    }
    
    public String getSpecificAccountCode() {
        
        return specificAccountCode;
    }
    
    public void setSpecificAccountCode(String specificAccountCode) {
        
        this.specificAccountCode = specificAccountCode;
    }
    
    public String getQuantity() {
        
        return quantity;
    }
    
    public void setQuantity(String quantity) {
        
        this.quantity = quantity;
    }
    
    public String getClosingQuantity() {
        
        return closingQuantity;
    }
    
    public void setClosingQuantity(String closingQuantity) {
        
        this.closingQuantity = closingQuantity;
    }
    
    public String getClosableQuantity() {
        
        return closableQuantity;
    }
    
    public void setClosableQuantity(String closableQuantity) {
        
        this.closableQuantity = closableQuantity;
    }
    
    public String getInitialMarginShortfallQuantity() {
        
        return initialMarginShortfallQuantity;
    }
    
    public void setInitialMarginShortfallQuantity(String initialMarginShortfallQuantity) {
        
        this.initialMarginShortfallQuantity = initialMarginShortfallQuantity;
    }
    
    public String getLastPrice() {
        
        return lastPrice;
    }
    
    public void setLastPrice(String lastPrice) {
        
        this.lastPrice = lastPrice;
    }
    
    public String getFrnPositionPrice() {
        
        return frnPositionPrice;
    }
    
    public void setFrnPositionPrice(String frnPositionPrice) {
        
        this.frnPositionPrice = frnPositionPrice;
    }
    
    public String getPositionPrice() {
        
        return positionPrice;
    }
    
    public void setPositionPrice(String positionPrice) {
        
        this.positionPrice = positionPrice;
    }
    
    public String getFrnPositionAmount() {
        
        return frnPositionAmount;
    }
    
    public void setFrnPositionAmount(String frnPositionAmount) {
        
        this.frnPositionAmount = frnPositionAmount;
    }
    
    public String getPositionAmount() {
        
        return positionAmount;
    }
    
    public void setPositionAmount(String positionAmount) {
        
        this.positionAmount = positionAmount;
    }
    
    public String getFrnTradeDate() {
        
        return frnTradeDate;
    }
    
    public void setFrnTradeDate(String frnTradeDate) {
        
        this.frnTradeDate = frnTradeDate;
    }
    
    public String getTradeDate() {
        
        return tradeDate;
    }
    
    public void setTradeDate(String tradeDate) {
        
        this.tradeDate = tradeDate;
    }
    
    public String getFrnValueDate() {
        
        return frnValueDate;
    }
    
    public void setFrnValueDate(String frnValueDate) {
        
        this.frnValueDate = frnValueDate;
    }
    
    public String getValueDate() {
        
        return valueDate;
    }
    
    public void setValueDate(String valueDate) {
        
        this.valueDate = valueDate;
    }
    
    public String getFrnCloseLimitDate() {
        
        return frnCloseLimitDate;
    }
    
    public void setFrnCloseLimitDate(String frnCloseLimitDate) {
        
        this.frnCloseLimitDate = frnCloseLimitDate;
    }
    
    public String getCloseLimitDate() {
        
        return closeLimitDate;
    }
    
    public void setCloseLimitDate(String closeLimitDate) {
        
        this.closeLimitDate = closeLimitDate;
    }
    
    public String getFrnCloseableExpirationDate() {
        
        return frnCloseableExpirationDate;
    }
    
    public void setFrnCloseableExpirationDate(String frnCloseableExpirationDate) {
        
        this.frnCloseableExpirationDate = frnCloseableExpirationDate;
    }
    
    public String getFrnOpenCommission() {
        
        return frnOpenCommission;
    }
    
    public void setFrnOpenCommission(String frnOpenCommission) {
        
        this.frnOpenCommission = frnOpenCommission;
    }
    
    public String getOpenCommission() {
        
        return openCommission;
    }
    
    public void setOpenCommission(String openCommission) {
        
        this.openCommission = openCommission;
    }
    
    public String getFrnOpenCommissionTax() {
        
        return frnOpenCommissionTax;
    }
    
    public void setFrnOpenCommissionTax(String frnOpenCommissionTax) {
        
        this.frnOpenCommissionTax = frnOpenCommissionTax;
    }
    
    public String getOpenCommissionTax() {
        
        return openCommissionTax;
    }
    
    public void setOpenCommissionTax(String openCommissionTax) {
        
        this.openCommissionTax = openCommissionTax;
    }
    
    public String getFrnInterestRate() {
        
        return frnInterestRate;
    }
    
    public void setFrnInterestRate(String frnInterestRate) {
        
        this.frnInterestRate = frnInterestRate;
    }
    
    public String getInterestRate() {
        
        return interestRate;
    }
    
    public void setInterestRate(String interestRate) {
        
        this.interestRate = interestRate;
    }
    
    public String getFrnPayableInterest() {
        
        return frnPayableInterest;
    }
    
    public void setFrnPayableInterest(String frnPayableInterest) {
        
        this.frnPayableInterest = frnPayableInterest;
    }
    
    public String getPayableInterest() {
        
        return payableInterest;
    }
    
    public void setPayableInterest(String payableInterest) {
        
        this.payableInterest = payableInterest;
    }
    
    public String getFrnReceivableInterest() {
        
        return frnReceivableInterest;
    }
    
    public void setFrnReceivableInterest(String frnReceivableInterest) {
        
        this.frnReceivableInterest = frnReceivableInterest;
    }
    
    public String getReceivableInterest() {
        
        return receivableInterest;
    }
    
    public void setReceivableInterest(String receivableInterest) {
        
        this.receivableInterest = receivableInterest;
    }
    
    public String getFrnLendingFee() {
        
        return frnLendingFee;
    }
    
    public void setFrnLendingFee(String frnLendingFee) {
        
        this.frnLendingFee = frnLendingFee;
    }
    
    public String getLendingFee() {
        
        return lendingFee;
    }
    
    public void setLendingFee(String lendingFee) {
        
        this.lendingFee = lendingFee;
    }
    
    public String getFrnManagementFee() {
        
        return frnManagementFee;
    }
    
    public void setFrnManagementFee(String frnManagementFee) {
        
        this.frnManagementFee = frnManagementFee;
    }
    
    public String getManagementFee() {
        
        return managementFee;
    }
    
    public void setManagementFee(String managementFee) {
        
        this.managementFee = managementFee;
    }
    
    public String getFrnManagementFeeTax() {
        
        return frnManagementFeeTax;
    }
    
    public void setFrnManagementFeeTax(String frnManagementFeeTax) {
        
        this.frnManagementFeeTax = frnManagementFeeTax;
    }
    
    public String getManagementFeeTax() {
        
        return managementFeeTax;
    }
    
    public void setManagementFeeTax(String managementFeeTax) {
        
        this.managementFeeTax = managementFeeTax;
    }
    
    public String getFrnTransferFee() {
        
        return frnTransferFee;
    }
    
    public void setFrnTransferFee(String frnTransferFee) {
        
        this.frnTransferFee = frnTransferFee;
    }
    
    public String getTransferFee() {
        
        return transferFee;
    }
    
    public void setTransferFee(String transferFee) {
        
        this.transferFee = transferFee;
    }
    
    public String getFrnTransferFeeTax() {
        
        return frnTransferFeeTax;
    }
    
    public void setFrnTransferFeeTax(String frnTransferFeeTax) {
        
        this.frnTransferFeeTax = frnTransferFeeTax;
    }
    
    public String getTransferFeeTax() {
        
        return transferFeeTax;
    }
    
    public void setTransferFeeTax(String transferFeeTax) {
        
        this.transferFeeTax = transferFeeTax;
    }
    
    public String getFrnLastValuationPrice() {
        
        return frnLastValuationPrice;
    }
    
    public void setFrnLastValuationPrice(String frnLastValuationPrice) {
        
        this.frnLastValuationPrice = frnLastValuationPrice;
    }
    
    public String getLastValuationPrice() {
        
        return lastValuationPrice;
    }
    
    public void setLastValuationPrice(String lastValuationPrice) {
        
        this.lastValuationPrice = lastValuationPrice;
    }
    
    public String getFrnLastUnrealizedPl() {
        
        return frnLastUnrealizedPl;
    }
    
    public void setFrnLastUnrealizedPl(String frnLastUnrealizedPl) {
        
        this.frnLastUnrealizedPl = frnLastUnrealizedPl;
    }
    
    public String getLastUnrealizedPl() {
        
        return lastUnrealizedPl;
    }
    
    public void setLastUnrealizedPl(String lastUnrealizedPl) {
        
        this.lastUnrealizedPl = lastUnrealizedPl;
    }
    
    public String getFrnHyperFee() {
        
        return frnHyperFee;
    }
    
    public void setFrnHyperFee(String frnHyperFee) {
        
        this.frnHyperFee = frnHyperFee;
    }
    
    public String getHyperFee() {
        
        return hyperFee;
    }
    
    public void setHyperFee(String hyperFee) {
        
        this.hyperFee = hyperFee;
    }
    
    public String getFrnTotalExpenses() {
        
        return frnTotalExpenses;
    }
    
    public void setFrnTotalExpenses(String frnTotalExpenses) {
        
        this.frnTotalExpenses = frnTotalExpenses;
    }
    
    public String getTotalExpenses() {
        
        return totalExpenses;
    }
    
    public void setTotalExpenses(String totalExpenses) {
        
        this.totalExpenses = totalExpenses;
    }
    
    public String getOpenExchangeRate() {
        
        return openExchangeRate;
    }
    
    public void setOpenExchangeRate(String openExchangeRate) {
        
        this.openExchangeRate = openExchangeRate;
    }
    
    public Boolean getNeedAdditionalCollateral() {
        
        return needAdditionalCollateral;
    }
    
    public void setNeedAdditionalCollateral(Boolean needAdditionalCollateral) {
        
        this.needAdditionalCollateral = needAdditionalCollateral;
    }
    
    public String getConvNeedAdditionalCollateral() {
        
        return convNeedAdditionalCollateral;
    }
    
    public void setConvNeedAdditionalCollateral(String convNeedAdditionalCollateral) {
        
        this.convNeedAdditionalCollateral = convNeedAdditionalCollateral;
    }
    
    public String getPositionMarginRate() {
        
        return positionMarginRate;
    }
    
    public void setPositionMarginRate(String positionMarginRate) {
        
        this.positionMarginRate = positionMarginRate;
    }
    
    public String getWithdrawableMarginRate() {
        
        return withdrawableMarginRate;
    }
    
    public void setWithdrawableMarginRate(String withdrawableMarginRate) {
        
        this.withdrawableMarginRate = withdrawableMarginRate;
    }
    
    public String getCashMarginRate() {
        
        return cashMarginRate;
    }
    
    public void setCashMarginRate(String cashMarginRate) {
        
        this.cashMarginRate = cashMarginRate;
    }
    
    public EvaluationProfitLoss getEvaluationProfitLoss() {
        
        return evaluationProfitLoss;
    }
    
    public void setEvaluationProfitLoss(EvaluationProfitLoss evaluationProfitLoss) {
        
        this.evaluationProfitLoss = evaluationProfitLoss;
    }
    
    public String getAdditionalCollateral() {
        
        return additionalCollateral;
    }
    
    public void setAdditionalCollateral(String additionalCollateral) {
        
        this.additionalCollateral = additionalCollateral;
    }
    
    public String getProductCode() {
        
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        
        this.productCode = productCode;
    }
    
    public String getCountryCode() {
        
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        
        this.countryCode = countryCode;
    }
    
    public String getListedSecuritiesStatus() {
        
        return listedSecuritiesStatus;
    }
    
    public void setListedSecuritiesStatus(String listedSecuritiesStatus) {
        
        this.listedSecuritiesStatus = listedSecuritiesStatus;
    }
}
