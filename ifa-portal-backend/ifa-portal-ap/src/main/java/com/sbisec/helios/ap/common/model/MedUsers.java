package com.sbisec.helios.ap.common.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbibits.earth.model.ModelBase;

public class MedUsers extends ModelBase {

	@JsonProperty("userId")
	private String userId;
	@JsonProperty("userNm")
	private String userNm;
	@JsonProperty("password")
	private String password;
	private Date lastpwuptime;
	private String privId;
	private String branchId;
	private String brokerId;
	private String subBrokerId;
	private String employeeId;
	private String employeeName;
	private String governorFlag;
	private String loginStatus;
	private String partnerUserId;
	private String partnerUserPw;
	private String ccsUserId;
	private String ccsUserPw;
	private String createUser;
	private Date createTime;
	private String uptimestampUser;
	private Date uptimestampTime;
	private String mailAddress;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastpwuptime() {
		return lastpwuptime;
	}

	public void setLastpwuptime(Date lastpwuptime) {
		this.lastpwuptime = lastpwuptime;
	}

	public String getPrivId() {
		return privId;
	}

	public void setPrivId(String privId) {
		this.privId = privId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getSubBrokerId() {
		return subBrokerId;
	}

	public void setSubBrokerId(String subBrokerId) {
		this.subBrokerId = subBrokerId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGovernorFlag() {
		return governorFlag;
	}

	public void setGovernorFlag(String governorFlag) {
		this.governorFlag = governorFlag;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getPartnerUserId() {
		return partnerUserId;
	}

	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	public String getPartnerUserPw() {
		return partnerUserPw;
	}

	public void setPartnerUserPw(String partnerUserPw) {
		this.partnerUserPw = partnerUserPw;
	}

	public String getCcsUserId() {
		return ccsUserId;
	}

	public void setCcsUserId(String ccsUserId) {
		this.ccsUserId = ccsUserId;
	}

	public String getCcsUserPw() {
		return ccsUserPw;
	}

	public void setCcsUserPw(String ccsUserPw) {
		this.ccsUserPw = ccsUserPw;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUptimestampUser() {
		return uptimestampUser;
	}

	public void setUptimestampUser(String uptimestampUser) {
		this.uptimestampUser = uptimestampUser;
	}

	public Date getUptimestampTime() {
		return uptimestampTime;
	}

	public void setUptimestampTime(Date uptimestampTime) {
		this.uptimestampTime = uptimestampTime;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
}
