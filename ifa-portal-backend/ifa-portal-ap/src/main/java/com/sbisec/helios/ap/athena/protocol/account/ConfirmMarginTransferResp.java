package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.MarginPower;
import com.sbisec.helios.ap.athena.protocol.account.dto.MarginTransferExpectation;

/**
 * 委託保証金振替確認 Response
 * 
 * @author shuchen.xin
 * @date 02/22/2022
 */
public class ConfirmMarginTransferResp {

	public ConfirmMarginTransferResp() {
	}

	// 振替指定日
	private String transferDate;
	// 通貨コード
	private String currencyCode;
	// 振替区分
	private String cashTransferType;
	// 振替金額
	private String amount;
	// 現在の余力情報
	private MarginPower currentPower;
	// 受付後余力情報
	private MarginPower afterPower;
	// 保証金振替予定情報
	private List<MarginTransferExpectation> marginTransferExpectations;

	/**
	 * @return 振替指定日
	 */
	public String getTransferDate() {
		return transferDate;
	}

	/**
	 * @param transferDate 振替指定日
	 */
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	/**
	 * @return 通貨コード
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param enums - 通貨コード - CurrencyCode
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return 振替区分
	 */
	public String getCashTransferType() {
		return cashTransferType;
	}

	/**
	 * @param enums - 振替区分 - TransferType
	 */
	public void setCashTransferType(String cashTransferType) {
		this.cashTransferType = cashTransferType;
	}

	/**
	 * @return 振替金額
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount 振替金額
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return 現在の余力情報
	 */
	public MarginPower getCurrentPower() {
		return currentPower;
	}

	/**
	 * @param currentPower 現在の余力情報
	 */
	public void setCurrentPower(MarginPower currentPower) {
		this.currentPower = currentPower;
	}

	/**
	 * @return 受付後余力情報
	 */
	public MarginPower getAfterPower() {
		return afterPower;
	}

	/**
	 * @param afterPower 受付後余力情報
	 */
	public void setAfterPower(MarginPower afterPower) {
		this.afterPower = afterPower;
	}

	/**
	 * @return 保証金振替予定情報
	 */
	public List<MarginTransferExpectation> getMarginTransferExpectations() {
		return marginTransferExpectations;
	}

	/**
	 * @param marginTransferExpectations 保証金振替予定情報
	 */
	public void setMarginTransferExpectations(List<MarginTransferExpectation> marginTransferExpectations) {
		this.marginTransferExpectations = marginTransferExpectations;
	}
}
