package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

/**
 * 仲介業者営業員情報
 * @author yoshitaka.nishida
 *
 */
public class MediateChargeInfo extends ModelBase {
	private String brokerCode;				//仲介業者コード
	private String brokerBranchCode;		//仲介業支店コード
	private String brokerChargeCode;		//仲介業者営業員コード
	private String brokerChargeName;		//仲介業者担当者名

	public String getBrokerCode() {
		return brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	public String getBrokerBranchCode() {
		return brokerBranchCode;
	}

	public void setBrokerBranchCode(String brokerBranchCode) {
		this.brokerBranchCode = brokerBranchCode;
	}

	public String getBrokerChargeCode() {
		return brokerChargeCode;
	}

	public void setBrokerChargeCode(String brokerChargeCode) {
		this.brokerChargeCode = brokerChargeCode;
	}

	public String getBrokerChargeName() {
		return brokerChargeName;
	}

	public void setBrokerChargeName(String brokerChargeName) {
		this.brokerChargeName = brokerChargeName;
	}

}
