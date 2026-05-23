package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

/**
 * 精算予定一覧表（日本） Dto.
 * 
 * @author shuchen.xin
 * @date 01/05/2022
 */
public class ScheduleCashBalance implements Serializable {
	private static final long serialVersionUID = -3437005073548061854L;

	public ScheduleCashBalance() {
	}

	// 営業日(yyyy-MM-dd)
	private String businessDate;
	// MRF・預り金
	private String accountCash;
	// 保証金現金
	private String securityCash;
	// 預り金
	private String keepCash;
	// 入金額
	private String cashReceipt;
	// 支払額
	private String paymentAmount;
	// 未約定信用決済損
	private String unexecPayBgLoss;
	// 未約定買注文額
	private String fixedOrderAmount;
	// 出金・振替指示額
	private String cashPayment;
	// 受取額
	private String receiveAmountValue;
	// 受取額（日計り分）
	private String fixedDayTradeAmount;
	// SBIハイブリッド預金精算額
	private String settlementBk;
	// 残高（当社）
	private String settleEtBalance;
	// 残高（SBIハイブリッド預金）
	private String settleBkBalance;
	// SBIハイブリッド預金拘束額
	private String settleBkBalanceCashHold;
	// 振替予定額（総合口座→ジュニアNISA口座）
	private String transferEstimatedAmount;
	// 振替可能額（総合口座→ジュニアNISA口座）
	private String transferPossibleAmount;
	// 必要精算額
	private String needSettlementValue;
	// 買付余力
	private String buyPossibleAmount;

	/**
	 * @return 営業日(yyyy - MM - dd)
	 */
	public String getBusinessDate() {
		return businessDate;
	}

	/**
	 * 営業日(yyyy - MM - dd)
	 * 
	 * @param businessDate
	 */
	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	/**
	 * @return MRF・預り金
	 */
	public String getAccountCash() {
		return accountCash;
	}

	/**
	 * MRF・預り金
	 * 
	 * @param accountCash
	 */
	public void setAccountCash(String accountCash) {
		this.accountCash = accountCash;
	}

	/**
	 * @return 保証金現金
	 */
	public String getSecurityCash() {
		return securityCash;
	}

	/**
	 * 保証金現金
	 * 
	 * @param securityCash
	 */
	public void setSecurityCash(String securityCash) {
		this.securityCash = securityCash;
	}

	/**
	 * @return 預り金
	 */
	public String getKeepCash() {
		return keepCash;
	}

	/**
	 * 預り金
	 * 
	 * @param keepCash
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
	 * 入金額
	 * 
	 * @param cashReceipt
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
	 * 支払額
	 * 
	 * @param paymentAmount
	 */
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return 未約定信用決済損
	 */
	public String getUnexecPayBgLoss() {
		return unexecPayBgLoss;
	}

	/**
	 * 未約定信用決済損
	 * 
	 * @param unexecPayBgLoss
	 */
	public void setUnexecPayBgLoss(String unexecPayBgLoss) {
		this.unexecPayBgLoss = unexecPayBgLoss;
	}

	/**
	 * @return 未約定買注文額
	 */
	public String getFixedOrderAmount() {
		return fixedOrderAmount;
	}

	/**
	 * 未約定買注文額
	 * 
	 * @param fixedOrderAmount
	 */
	public void setFixedOrderAmount(String fixedOrderAmount) {
		this.fixedOrderAmount = fixedOrderAmount;
	}

	/**
	 * @return 出金・振替指示額
	 */
	public String getCashPayment() {
		return cashPayment;
	}

	/**
	 * 出金・振替指示額
	 * 
	 * @param cashPayment
	 */
	public void setCashPayment(String cashPayment) {
		this.cashPayment = cashPayment;
	}

	/**
	 * @return 受取額
	 */
	public String getReceiveAmountValue() {
		return receiveAmountValue;
	}

	/**
	 * 受取額
	 * 
	 * @param receiveAmountValue
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
	 * 受取額（日計り分）
	 * 
	 * @param fixedDayTradeAmount
	 */
	public void setFixedDayTradeAmount(String fixedDayTradeAmount) {
		this.fixedDayTradeAmount = fixedDayTradeAmount;
	}

	/**
	 * @return SBIハイブリッド預金精算額
	 */
	public String getSettlementBk() {
		return settlementBk;
	}

	/**
	 * SBIハイブリッド預金精算額
	 * 
	 * @param settlementBk
	 */
	public void setSettlementBk(String settlementBk) {
		this.settlementBk = settlementBk;
	}

	/**
	 * @return 残高（当社）
	 */
	public String getSettleEtBalance() {
		return settleEtBalance;
	}

	/**
	 * 残高（当社）
	 * 
	 * @param settleEtBalance
	 */
	public void setSettleEtBalance(String settleEtBalance) {
		this.settleEtBalance = settleEtBalance;
	}

	/**
	 * @return 残高（SBIハイブリッド預金）
	 */
	public String getSettleBkBalance() {
		return settleBkBalance;
	}

	/**
	 * 残高（SBIハイブリッド預金）
	 * 
	 * @param settleBkBalance
	 */
	public void setSettleBkBalance(String settleBkBalance) {
		this.settleBkBalance = settleBkBalance;
	}

	/**
	 * @return SBIハイブリッド預金拘束額
	 */
	public String getSettleBkBalanceCashHold() {
		return settleBkBalanceCashHold;
	}

	/**
	 * SBIハイブリッド預金拘束額
	 * 
	 * @param settleBkBalanceCashHold
	 */
	public void setSettleBkBalanceCashHold(String settleBkBalanceCashHold) {
		this.settleBkBalanceCashHold = settleBkBalanceCashHold;
	}

	/**
	 * @return 振替予定額（総合口座→ジュニアNISA口座）
	 */
	public String getTransferEstimatedAmount() {
		return transferEstimatedAmount;
	}

	/**
	 * 振替予定額（総合口座→ジュニアNISA口座）
	 * 
	 * @param transferEstimatedAmount
	 */
	public void setTransferEstimatedAmount(String transferEstimatedAmount) {
		this.transferEstimatedAmount = transferEstimatedAmount;
	}

	/**
	 * @return 振替可能額（総合口座→ジュニアNISA口座）
	 */
	public String getTransferPossibleAmount() {
		return transferPossibleAmount;
	}

	/**
	 * 振替可能額（総合口座→ジュニアNISA口座）
	 * 
	 * @param transferPossibleAmount
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
	 * 必要精算額
	 * 
	 * @param needSettlementValue
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
	 * 買付余力
	 * 
	 * @param buyPossibleAmount
	 */
	public void setBuyPossibleAmount(String buyPossibleAmount) {
		this.buyPossibleAmount = buyPossibleAmount;
	}
}
