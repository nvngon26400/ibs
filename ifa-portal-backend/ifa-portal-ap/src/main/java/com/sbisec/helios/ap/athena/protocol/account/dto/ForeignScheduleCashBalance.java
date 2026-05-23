package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

/**
 * 外貨金銭残高スケジュールリスト Dto.
 * 
 * @author shuchen.xin
 * @date 01/05/2022
 */
public class ForeignScheduleCashBalance implements Serializable {
    private static final long serialVersionUID = -2006703832124599325L;

    public ForeignScheduleCashBalance() {
    }

    // 預り金
    private String keepCash;
    // 入金額
    private String cashReceipt;
    // 支払額
    private String paymentAmount;
    // 未約定買注文額
    private String fixedOrderAmount;
    // 出金額
    private String amountPayValue;
    // 受取額
    private String receiveAmountValue;
    // 受取額（日計り分）
    private String fixedDayTradeAmount;
    // 振替予定額 （総合口座→ジュニアNISA口座）
    private String transferEstimatedAmount;
    // 残高
    private String remainingBuyPossibleAmount;
    // 振替可能額 （総合口座→ジュニアNISA口座）
    private String transferPossibleAmount;
    // 必要精算額
    private String needSettlementValue;
    // 買付余力
    private String buyPossibleAmount;
    // 買掛金
    private String buyingAmount;
    // 売掛金
    private String sellingAmount;
    // 何営業日後（日数）
    private Integer daysLater;
    // 営業日(yyyy-MM-dd)
    private String businessDate;
    // 出金余力
    private String paymentPossibleAmount;
    // 振替予定額 (信用口座→現物口座)
    private String transferEstimatedMarginAmount;
    
    /**
     * @return 預り金
     */
    public String getKeepCash() {
        return keepCash;
    }

    /**
     * @param 預り金
     */
    public void setKeepCash(String keepCash) {
        this.keepCash = keepCash;
    }

    /**
     * @return 入金額
     */
    public String getCashReceipt() {
        return cashReceipt;
    }

	    /**
     * @param cashReceipt 入金額
	 */
    public void setCashReceipt(String cashReceipt) {
        this.cashReceipt = cashReceipt;
    }

    /**
     * @return 支払額
     */
    public String getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @param paymentAmount 支払額
     */
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * @return 未約定買注文額
     */
    public String getFixedOrderAmount() {
        return fixedOrderAmount;
    }

    /**
     * @param fixedOrderAmount 未約定買注文額
     */
    public void setFixedOrderAmount(String fixedOrderAmount) {
        this.fixedOrderAmount = fixedOrderAmount;
    }

    /**
     * @return 出金額
     */
    public String getAmountPayValue() {
        return amountPayValue;
    }

    /**
     * @param amountPayValue 出金額
     */
    public void setAmountPayValue(String amountPayValue) {
        this.amountPayValue = amountPayValue;
    }

    /**
     * @return 受取額
     */
    public String getReceiveAmountValue() {
        return receiveAmountValue;
    }

    /**
     * @param receiveAmountValue 受取額
     */
    public void setReceiveAmountValue(String receiveAmountValue) {
        this.receiveAmountValue = receiveAmountValue;
    }

    /**
     * @return 受取額（日計り分）
     */
    public String getFixedDayTradeAmount() {
        return fixedDayTradeAmount;
    }

    /**
     * @param fixedDayTradeAmount 受取額（日計り分）
     */
    public void setFixedDayTradeAmount(String fixedDayTradeAmount) {
        this.fixedDayTradeAmount = fixedDayTradeAmount;
    }

    /**
     * @return 振替予定額 （総合口座→ジュニアNISA口座）
     */
    public String getTransferEstimatedAmount() {
        return transferEstimatedAmount;
    }

    /**
     * @param transferEstimatedAmount 振替予定額 （総合口座→ジュニアNISA口座）
     */
    public void setTransferEstimatedAmount(String transferEstimatedAmount) {
        this.transferEstimatedAmount = transferEstimatedAmount;
    }

    /**
     * @return 残高
     */
    public String getRemainingBuyPossibleAmount() {
        return remainingBuyPossibleAmount;
    }

    /**
     * @param remainingBuyPossibleAmount 残高
     */
    public void setRemainingBuyPossibleAmount(String remainingBuyPossibleAmount) {
        this.remainingBuyPossibleAmount = remainingBuyPossibleAmount;
    }

    /**
     * @return 振替可能額 （総合口座→ジュニアNISA口座）
     */
    public String getTransferPossibleAmount() {
        return transferPossibleAmount;
    }

    /**
     * @param transferPossibleAmount 振替可能額 （総合口座→ジュニアNISA口座）
     */
    public void setTransferPossibleAmount(String transferPossibleAmount) {
        this.transferPossibleAmount = transferPossibleAmount;
    }

    /**
     * @return 必要精算額
     */
    public String getNeedSettlementValue() {
        return needSettlementValue;
    }

    /**
     * @param needSettlementValue 必要精算額
     */
    public void setNeedSettlementValue(String needSettlementValue) {
        this.needSettlementValue = needSettlementValue;
    }

    /**
     * @return 買付余力
     */
    public String getBuyPossibleAmount() {
        return buyPossibleAmount;
    }

    /**
     * @param buyPossibleAmount 買付余力
     */
    public void setBuyPossibleAmount(String buyPossibleAmount) {
        this.buyPossibleAmount = buyPossibleAmount;
    }

    /**
     * @return 買掛金
     */
    public String getBuyingAmount() {
        return buyingAmount;
    }

    /**
     * @param buyingAmount 買掛金
     */
    public void setBuyingAmount(String buyingAmount) {
        this.buyingAmount = buyingAmount;
    }

    /**
     * @return 売掛金
     */
    public String getSellingAmount() {
        return sellingAmount;
    }

    /**
     * @param sellingAmount 売掛金
     */
    public void setSellingAmount(String sellingAmount) {
        this.sellingAmount = sellingAmount;
    }

    /**
     * @return 何営業日後（日数）
     */
    public Integer getDaysLater() {
        return daysLater;
    }

    /**
     * @param daysLater 何営業日後（日数）
     */
    public void setDaysLater(Integer daysLater) {
        this.daysLater = daysLater;
    }

    /**
     * @return 営業日(yyyy-MM-dd)
     */
    public String getBusinessDate() {
        return businessDate;
    }

    /**
     * @param businessDate 営業日(yyyy-MM-dd)
     */
    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    /**
     * @return 出金余力
     */
    public String getPaymentPossibleAmount() {
        return paymentPossibleAmount;
    }

    /**
     * @param paymentPossibleAmount 出金余力
     */
    public void setPaymentPossibleAmount(String paymentPossibleAmount) {
        this.paymentPossibleAmount = paymentPossibleAmount;
    }
    
    /**
     * @return 振替予定額
     */
    public String getTransferEstimatedMarginAmount() {
        return transferEstimatedMarginAmount;
    }

    /**
     * @param transferEstimatedMarginAmount 振替予定額
     */
    public void setTransferEstimatedMarginAmount(String transferEstimatedMarginAmount) {
        this.transferEstimatedMarginAmount = transferEstimatedMarginAmount;
    }

}
