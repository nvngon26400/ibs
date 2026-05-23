package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.account.dto.EvaluationProfitLoss;
import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

/**
 * 余力サービス - 外国株式信用建玉明細取得API Response.
 * 
 * @author mengzhe.li
 * @date: 03/09/2022
 */
public class GetMarginPositionResp {
    
    public GetMarginPositionResp() {
    
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
    
    // 数量
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
    
    // 現地新規約定日
    private String frnTradeDate;
    
    // 国内新規約定日
    private String tradeDate;
    
    // 現地新規受渡日
    private String frnValueDate;
    
    // 国内新規受渡日
    private String valueDate;
    
    // 現地決済期日
    private String frnCloseLimitDate;
    
    // 国内決済期日
    private String closeLimitDate;
    
    // 現地返済期限
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
    private String needAdditionalCollateral;
    
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
    
    /**
     * @return 銘柄情報
     */
    public Securities getSecurities() {
        
        return securities;
    }
    
    public void setSecurities(Securities securities) {
        
        this.securities = securities;
    }
    
    /**
     * @return 市場情報
     */
    public Market getMarket() {
        
        return market;
    }
    
    public void setMarket(Market market) {
        
        this.market = market;
    }
    
    /**
     * @return 株価情報
     */
    public PriceData getPriceData() {
        
        return priceData;
    }
    
    public void setPriceData(PriceData priceData) {
        
        this.priceData = priceData;
    }
    
    /**
     * @return 売買区分
     */
    public String getBuySellCode() {
        
        return buySellCode;
    }
    
    public void setBuySellCode(String buySellCode) {
        
        this.buySellCode = buySellCode;
    }
    
    /**
     * @return 信用期日
     */
    public String getMarginCloseLimitType() {
        
        return marginCloseLimitType;
    }
    
    public void setMarginCloseLimitType(String marginCloseLimitType) {
        
        this.marginCloseLimitType = marginCloseLimitType;
    }
    
    /**
     * @return 株取引区分
     */
    public String getStockTradeType() {
        
        return stockTradeType;
    }
    
    public void setStockTradeType(String stockTradeType) {
        
        this.stockTradeType = stockTradeType;
    }
    
    /**
     * @return 預り区分
     */
    public String getSpecificAccountCode() {
        
        return specificAccountCode;
    }
    
    public void setSpecificAccountCode(String specificAccountCode) {
        
        this.specificAccountCode = specificAccountCode;
    }
    
    /**
     * @return 数量
     */
    public String getQuantity() {
        
        return quantity;
    }
    
    public void setQuantity(String quantity) {
        
        this.quantity = quantity;
    }
    
    /**
     * @return 決済注文中数量
     */
    public String getClosingQuantity() {
        
        return closingQuantity;
    }
    
    public void setClosingQuantity(String closingQuantity) {
        
        this.closingQuantity = closingQuantity;
    }
    
    /**
     * @return 決済注文可能数量
     */
    public String getClosableQuantity() {
        
        return closableQuantity;
    }
    
    public void setClosableQuantity(String closableQuantity) {
        
        this.closableQuantity = closableQuantity;
    }
    
    /**
     * @return 新規建不足数量
     */
    public String getInitialMarginShortfallQuantity() {
        
        return initialMarginShortfallQuantity;
    }
    
    public void setInitialMarginShortfallQuantity(String initialMarginShortfallQuantity) {
        
        this.initialMarginShortfallQuantity = initialMarginShortfallQuantity;
    }
    
    /**
     * @return 現在値（円貨）
     */
    public String getLastPrice() {
        
        return lastPrice;
    }
    
    public void setLastPrice(String lastPrice) {
        
        this.lastPrice = lastPrice;
    }
    
    /**
     * @return 新規建単価（外貨）
     */
    public String getFrnPositionPrice() {
        
        return frnPositionPrice;
    }
    
    public void setFrnPositionPrice(String frnPositionPrice) {
        
        this.frnPositionPrice = frnPositionPrice;
    }
    
    /**
     * @return 新規建単価（円貨）
     */
    public String getPositionPrice() {
        
        return positionPrice;
    }
    
    public void setPositionPrice(String positionPrice) {
        
        this.positionPrice = positionPrice;
    }
    
    /**
     * @return 新規建代金（外貨）
     */
    public String getFrnPositionAmount() {
        
        return frnPositionAmount;
    }
    
    public void setFrnPositionAmount(String frnPositionAmount) {
        
        this.frnPositionAmount = frnPositionAmount;
    }
    
    /**
     * @return 新規建代金（円貨）
     */
    public String getPositionAmount() {
        
        return positionAmount;
    }
    
    public void setPositionAmount(String positionAmount) {
        
        this.positionAmount = positionAmount;
    }
    
    /**
     * @return 現地新規約定日
     */
    public String getFrnTradeDate() {
        
        return frnTradeDate;
    }
    
    public void setFrnTradeDate(String frnTradeDate) {
        
        this.frnTradeDate = frnTradeDate;
    }
    
    /**
     * @return 国内新規約定日
     */
    public String getTradeDate() {
        
        return tradeDate;
    }
    
    public void setTradeDate(String tradeDate) {
        
        this.tradeDate = tradeDate;
    }
    
    /**
     * @return 現地新規受渡日
     */
    public String getFrnValueDate() {
        
        return frnValueDate;
    }
    
    public void setFrnValueDate(String frnValueDate) {
        
        this.frnValueDate = frnValueDate;
    }
    
    /**
     * @return 国内新規受渡日
     */
    public String getValueDate() {
        
        return valueDate;
    }
    
    public void setValueDate(String valueDate) {
        
        this.valueDate = valueDate;
    }
    
    /**
     * @return 現地決済期日
     */
    public String getFrnCloseLimitDate() {
        
        return frnCloseLimitDate;
    }
    
    public void setFrnCloseLimitDate(String frnCloseLimitDate) {
        
        this.frnCloseLimitDate = frnCloseLimitDate;
    }
    
    /**
     * @return 国内決済期日
     */
    public String getCloseLimitDate() {
        
        return closeLimitDate;
    }
    
    public void setCloseLimitDate(String closeLimitDate) {
        
        this.closeLimitDate = closeLimitDate;
    }
    
    /**
     * @return 現地返済期限
     */
    public String getFrnCloseableExpirationDate() {
        
        return frnCloseableExpirationDate;
    }
    
    public void setFrnCloseableExpirationDate(String frnCloseableExpirationDate) {
        
        this.frnCloseableExpirationDate = frnCloseableExpirationDate;
    }
    
    /**
     * @return 新規建手数料（外貨）
     */
    public String getFrnOpenCommission() {
        
        return frnOpenCommission;
    }
    
    public void setFrnOpenCommission(String frnOpenCommission) {
        
        this.frnOpenCommission = frnOpenCommission;
    }
    
    /**
     * @return 新規建手数料（円貨）
     */
    public String getOpenCommission() {
        
        return openCommission;
    }
    
    public void setOpenCommission(String openCommission) {
        
        this.openCommission = openCommission;
    }
    
    /**
     * @return 新規建手数料消費税（外貨）
     */
    public String getFrnOpenCommissionTax() {
        
        return frnOpenCommissionTax;
    }
    
    public void setFrnOpenCommissionTax(String frnOpenCommissionTax) {
        
        this.frnOpenCommissionTax = frnOpenCommissionTax;
    }
    
    /**
     * @return 新規建手数料消費税（円貨）
     */
    public String getOpenCommissionTax() {
        
        return openCommissionTax;
    }
    
    public void setOpenCommissionTax(String openCommissionTax) {
        
        this.openCommissionTax = openCommissionTax;
    }
    
    /**
     * @return 金利（外貨）
     */
    public String getFrnInterestRate() {
        
        return frnInterestRate;
    }
    
    public void setFrnInterestRate(String frnInterestRate) {
        
        this.frnInterestRate = frnInterestRate;
    }
    
    /**
     * @return 金利（円貨）
     */
    public String getInterestRate() {
        
        return interestRate;
    }
    
    public void setInterestRate(String interestRate) {
        
        this.interestRate = interestRate;
    }
    
    /**
     * @return 順日歩（外貨）
     */
    public String getFrnPayableInterest() {
        
        return frnPayableInterest;
    }
    
    public void setFrnPayableInterest(String frnPayableInterest) {
        
        this.frnPayableInterest = frnPayableInterest;
    }
    
    /**
     * @return 順日歩（円貨）
     */
    public String getPayableInterest() {
        
        return payableInterest;
    }
    
    public void setPayableInterest(String payableInterest) {
        
        this.payableInterest = payableInterest;
    }
    
    /**
     * @return 逆日歩（外貨）
     */
    public String getFrnReceivableInterest() {
        
        return frnReceivableInterest;
    }
    
    public void setFrnReceivableInterest(String frnReceivableInterest) {
        
        this.frnReceivableInterest = frnReceivableInterest;
    }
    
    /**
     * @return 逆日歩（円貨）
     */
    public String getReceivableInterest() {
        
        return receivableInterest;
    }
    
    public void setReceivableInterest(String receivableInterest) {
        
        this.receivableInterest = receivableInterest;
    }
    
    /**
     * @return 貸株料（外貨）
     */
    public String getFrnLendingFee() {
        
        return frnLendingFee;
    }
    
    public void setFrnLendingFee(String frnLendingFee) {
        
        this.frnLendingFee = frnLendingFee;
    }
    
    /**
     * @return 貸株料（円貨）
     */
    public String getLendingFee() {
        
        return lendingFee;
    }
    
    public void setLendingFee(String lendingFee) {
        
        this.lendingFee = lendingFee;
    }
    
    /**
     * @return 管理料（外貨）
     */
    public String getFrnManagementFee() {
        
        return frnManagementFee;
    }
    
    public void setFrnManagementFee(String frnManagementFee) {
        
        this.frnManagementFee = frnManagementFee;
    }
    
    /**
     * @return 管理料（円貨）
     */
    public String getManagementFee() {
        
        return managementFee;
    }
    
    public void setManagementFee(String managementFee) {
        
        this.managementFee = managementFee;
    }
    
    /**
     * @return 管理料消費税（外貨）
     */
    public String getFrnManagementFeeTax() {
        
        return frnManagementFeeTax;
    }
    
    public void setFrnManagementFeeTax(String frnManagementFeeTax) {
        
        this.frnManagementFeeTax = frnManagementFeeTax;
    }
    
    /**
     * @return 管理料消費税（円貨）
     */
    public String getManagementFeeTax() {
        
        return managementFeeTax;
    }
    
    public void setManagementFeeTax(String managementFeeTax) {
        
        this.managementFeeTax = managementFeeTax;
    }
    
    /**
     * @return 書換料（外貨）
     */
    public String getFrnTransferFee() {
        
        return frnTransferFee;
    }
    
    public void setFrnTransferFee(String frnTransferFee) {
        
        this.frnTransferFee = frnTransferFee;
    }
    
    /**
     * @return 書換料（円貨）
     */
    public String getTransferFee() {
        
        return transferFee;
    }
    
    public void setTransferFee(String transferFee) {
        
        this.transferFee = transferFee;
    }
    
    /**
     * @return 書換料消費税（外貨）
     */
    public String getFrnTransferFeeTax() {
        
        return frnTransferFeeTax;
    }
    
    public void setFrnTransferFeeTax(String frnTransferFeeTax) {
        
        this.frnTransferFeeTax = frnTransferFeeTax;
    }
    
    /**
     * @return 書換料消費税（円貨）
     */
    public String getTransferFeeTax() {
        
        return transferFeeTax;
    }
    
    public void setTransferFeeTax(String transferFeeTax) {
        
        this.transferFeeTax = transferFeeTax;
    }
    
    /**
     * @return 直近値洗評価単価（外貨）
     */
    public String getFrnLastValuationPrice() {
        
        return frnLastValuationPrice;
    }
    
    public void setFrnLastValuationPrice(String frnLastValuationPrice) {
        
        this.frnLastValuationPrice = frnLastValuationPrice;
    }
    
    /**
     * @return 直近値洗評価単価（円貨）
     */
    public String getLastValuationPrice() {
        
        return lastValuationPrice;
    }
    
    public void setLastValuationPrice(String lastValuationPrice) {
        
        this.lastValuationPrice = lastValuationPrice;
    }
    
    /**
     * @return 直近値洗評価損益（外貨）
     */
    public String getFrnLastUnrealizedPl() {
        
        return frnLastUnrealizedPl;
    }
    
    public void setFrnLastUnrealizedPl(String frnLastUnrealizedPl) {
        
        this.frnLastUnrealizedPl = frnLastUnrealizedPl;
    }
    
    /**
     * @return 直近値洗評価損益（円貨）
     */
    public String getLastUnrealizedPl() {
        
        return lastUnrealizedPl;
    }
    
    public void setLastUnrealizedPl(String lastUnrealizedPl) {
        
        this.lastUnrealizedPl = lastUnrealizedPl;
    }
    
    /**
     * @return HYPER料（外貨）
     */
    public String getFrnHyperFee() {
        
        return frnHyperFee;
    }
    
    public void setFrnHyperFee(String frnHyperFee) {
        
        this.frnHyperFee = frnHyperFee;
    }
    
    /**
     * @return HYPER料（円貨）
     */
    public String getHyperFee() {
        
        return hyperFee;
    }
    
    public void setHyperFee(String hyperFee) {
        
        this.hyperFee = hyperFee;
    }
    
    /**
     * @return 諸経費合計額（外貨）
     */
    public String getFrnTotalExpenses() {
        
        return frnTotalExpenses;
    }
    
    public void setFrnTotalExpenses(String frnTotalExpenses) {
        
        this.frnTotalExpenses = frnTotalExpenses;
    }
    
    /**
     * @return 諸経費合計額（円貨）
     */
    public String getTotalExpenses() {
        
        return totalExpenses;
    }
    
    public void setTotalExpenses(String totalExpenses) {
        
        this.totalExpenses = totalExpenses;
    }
    
    /**
     * @return 新規建為替レート
     */
    public String getOpenExchangeRate() {
        
        return openExchangeRate;
    }
    
    public void setOpenExchangeRate(String openExchangeRate) {
        
        this.openExchangeRate = openExchangeRate;
    }
    
    /**
     * @return 増担保規制建玉フラグ
     */
    public String getNeedAdditionalCollateral() {
        
        return needAdditionalCollateral;
    }
    
    public void setNeedAdditionalCollateral(String needAdditionalCollateral) {
        
        this.needAdditionalCollateral = needAdditionalCollateral;
    }
    
    /**
     * @return 建玉必要保証金率
     */
    public String getPositionMarginRate() {
        
        return positionMarginRate;
    }
    
    public void setPositionMarginRate(String positionMarginRate) {
        
        this.positionMarginRate = positionMarginRate;
    }
    
    /**
     * @return 返却必要保証金率
     */
    public String getWithdrawableMarginRate() {
        
        return withdrawableMarginRate;
    }
    
    public void setWithdrawableMarginRate(String withdrawableMarginRate) {
        
        this.withdrawableMarginRate = withdrawableMarginRate;
    }
    
    /**
     * @return 現金必要保証金率
     */
    public String getCashMarginRate() {
        
        return cashMarginRate;
    }
    
    public void setCashMarginRate(String cashMarginRate) {
        
        this.cashMarginRate = cashMarginRate;
    }
    
    /**
     * @return 評価情報
     */
    public EvaluationProfitLoss getEvaluationProfitLoss() {
        
        return evaluationProfitLoss;
    }
    
    public void setEvaluationProfitLoss(EvaluationProfitLoss evaluationProfitLoss) {
        
        this.evaluationProfitLoss = evaluationProfitLoss;
    }
    
    /**
     * @return 増担保
     */
    public String getAdditionalCollateral() {
        
        return additionalCollateral;
    }
    
    public void setAdditionalCollateral(String additionalCollateral) {
        
        this.additionalCollateral = additionalCollateral;
    }
    
    /**
     * @return 商品コード
     */
    public String getProductCode() {
        
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        
        this.productCode = productCode;
    }
    
    /**
     * @return 国コード
     */
    public String getCountryCode() {
        
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        
        this.countryCode = countryCode;
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
    
}
