package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

public class AccControl extends ModelBase {
	private String menuId;
	private String itemId;
	private String userId;
	private Integer accControl;

	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getAccControl() {
		return accControl;
	}
	public void setAccControl(Integer accControl) {
		this.accControl = accControl;
	}

}
