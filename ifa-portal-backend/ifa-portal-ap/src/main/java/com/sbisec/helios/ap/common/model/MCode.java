package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

public class MCode extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;
	private String codeType;
	private String code1;
	private String code2;
	private String name;
	private String sysUserid;
	private String sysMachineid;
	private String sysTrigger;
	private String sysAppdate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSysUserid() {
		return sysUserid;
	}

	public void setSysUserid(String sysUserid) {
		this.sysUserid = sysUserid;
	}

	public String getSysMachineid() {
		return sysMachineid;
	}

	public void setSysMachineid(String sysMachineid) {
		this.sysMachineid = sysMachineid;
	}

	public String getSysTrigger() {
		return sysTrigger;
	}

	public void setSysTrigger(String sysTrigger) {
		this.sysTrigger = sysTrigger;
	}

	public String getSysAppdate() {
		return sysAppdate;
	}

	public void setSysAppdate(String sysAppdate) {
		this.sysAppdate = sysAppdate;
	}
}
