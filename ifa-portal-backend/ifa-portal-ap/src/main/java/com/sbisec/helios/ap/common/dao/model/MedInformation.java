package com.sbisec.helios.ap.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sbibits.earth.model.ModelBase;

public class MedInformation extends ModelBase {

	private BigDecimal infoId;
	private String infoDetail;
	private String importantFlg;
	private BigDecimal displayOrder;
	private String deleteFlg;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;

	public BigDecimal getInfoId() {
		return infoId;
	}

	public void setInfoId(BigDecimal infoId) {
		this.infoId = infoId;
	}
	public String getInfoDetail() {
		return infoDetail;
	}

	public void setInfoDetail(String infoDetail) {
		this.infoDetail = infoDetail;
	}

	public String getImportantFlg() {
		return importantFlg;
	}

	public void setImportantFlg(String importantFlg) {
		this.importantFlg = importantFlg;
	}

	public BigDecimal getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(BigDecimal displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
