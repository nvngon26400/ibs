package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.MarginPower;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;

/**
 * 外国株式取引サービス - 外国株式信用新規建注文確認.
 * 
 * @author mengzhe.li
 * @date: 02/17/2022
 */
public class ConfirmForeignStockCreatedMarginOrderResp {

	public ConfirmForeignStockCreatedMarginOrderResp() {
	}

	private MarginOrder marginOrder;// 信用注文情報
	private String estimatePrice;// 見積価格
	private String appliedInterestRate;// 適用金利率
	private String appliedLendingFeeRate;// 適用貸株料率
	private MarginPower currentPower;// 受付前余力情報
	private MarginPower afterPower;// 受付後余力情報
	private List<String> warningStatuses;// 注文ワーニングステータス

	/**
	 * @return 信用注文情報
	 */
	public MarginOrder getMarginOrder() {
		return marginOrder;
	}

	public void setMarginOrder(MarginOrder marginOrder) {
		this.marginOrder = marginOrder;
	}

	/**
	 * @return 見積価格
	 */
	public String getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(String estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

	/**
	 * @return 適用金利率
	 */
	public String getAppliedInterestRate() {
		return appliedInterestRate;
	}

	public void setAppliedInterestRate(String appliedInterestRate) {
		this.appliedInterestRate = appliedInterestRate;
	}

	/**
	 * @return 適用貸株料率
	 */
	public String getAppliedLendingFeeRate() {
		return appliedLendingFeeRate;
	}

	public void setAppliedLendingFeeRate(String appliedLendingFeeRate) {
		this.appliedLendingFeeRate = appliedLendingFeeRate;
	}

	/**
	 * @return 受付前余力情報
	 */
	public MarginPower getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(MarginPower currentPower) {
		this.currentPower = currentPower;
	}

	/**
	 * @return 受付後余力情報
	 */
	public MarginPower getAfterPower() {
		return afterPower;
	}

	public void setAfterPower(MarginPower afterPower) {
		this.afterPower = afterPower;
	}

	/**
	 * @return 注文ワーニングステータス
	 */
	public List<String> getWarningStatuses() {
		return warningStatuses;
	}
	public void setWarningStatuses(List<String> warningStatuses) {
		this.warningStatuses = warningStatuses;
	}


}
