package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

/**
 * 評価情報 Dto.
 * 
 * @author yunhui.zhao
 * @date 03/11/2022
 */
public class EvaluationProfitLoss implements Serializable {

	private static final long serialVersionUID = -181685067076191022L;

	public EvaluationProfitLoss() {
	}

	// 評価額（外貨）
	private String frnEvaluationAmount;
	// 評価額（円貨）
	private String evaluationAmount;
	// 評価損益（外貨）
	private String frnEvaluationProfitLoss;
	// 評価損益（円貨）
	private String evaluationProfitLoss;
	// 評価損益率（外貨）
	private String frnEvaluationProfitLossPercent;
	// 評価損益率（円貨）
	private String evaluationProfitLossPercent;
	// 評価為替レート
	private String evaluationExchangeRate;

	public String getFrnEvaluationAmount() {
		return frnEvaluationAmount;
	}

	public void setFrnEvaluationAmount(String frnEvaluationAmount) {
		this.frnEvaluationAmount = frnEvaluationAmount;
	}

	public String getEvaluationAmount() {
		return evaluationAmount;
	}

	public void setEvaluationAmount(String evaluationAmount) {
		this.evaluationAmount = evaluationAmount;
	}

	public String getFrnEvaluationProfitLoss() {
		return frnEvaluationProfitLoss;
	}

	public void setFrnEvaluationProfitLoss(String frnEvaluationProfitLoss) {
		this.frnEvaluationProfitLoss = frnEvaluationProfitLoss;
	}

	public String getEvaluationProfitLoss() {
		return evaluationProfitLoss;
	}

	public void setEvaluationProfitLoss(String evaluationProfitLoss) {
		this.evaluationProfitLoss = evaluationProfitLoss;
	}

	public String getFrnEvaluationProfitLossPercent() {
		return frnEvaluationProfitLossPercent;
	}

	public void setFrnEvaluationProfitLossPercent(String frnEvaluationProfitLossPercent) {
		this.frnEvaluationProfitLossPercent = frnEvaluationProfitLossPercent;
	}

	public String getEvaluationProfitLossPercent() {
		return evaluationProfitLossPercent;
	}

	public void setEvaluationProfitLossPercent(String evaluationProfitLossPercent) {
		this.evaluationProfitLossPercent = evaluationProfitLossPercent;
	}

	public String getEvaluationExchangeRate() {
		return evaluationExchangeRate;
	}

	public void setEvaluationExchangeRate(String evaluationExchangeRate) {
		this.evaluationExchangeRate = evaluationExchangeRate;
	}
}
