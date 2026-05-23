package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

/**
 * 本支店
 *
 * @author yoshitaka.nishida
 *
 */
public class Branch extends ModelBase {
	private String branchCode;				//本支店コード
	private String branchKind;				//本支店種別
	private String branchName;				//本支店名

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchKind() {
		return branchKind;
	}

	public void setBranchKind(String branchKind) {
		this.branchKind = branchKind;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
