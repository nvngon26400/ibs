package com.sbisec.helios.ap.common.model;

import java.util.Date;

import com.sbibits.earth.model.ModelBase;

public class MedGovMenu extends ModelBase {

	private String privId;
	private String menuId;
	private String createUser;
	private Date createTime;
	private String uptimestampUser;
	private Date uptimestampTime;

	public String getPrivId() {
		return privId;
	}

	public void setPrivId(String privId) {
		this.privId = privId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
}
