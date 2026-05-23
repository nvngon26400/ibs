package com.sbisec.helios.ap.common.model;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.ModelBase;

public class UserPermissionInfo extends ModelBase {

	private List<String> accessibleViewList = new ArrayList<String>(64);

	public void addViewPermissions(List<MedGovMenu> list) {

		if (list == null) return;

		for (int i = 0; i < list.size(); i++) {

			MedGovMenu medGovMenu = list.get(i);
			String menuId = medGovMenu.getMenuId();

			if (! accessibleViewList.contains(menuId)) {
				accessibleViewList.add(menuId);
			}
		}
	}

	public boolean isAccessible(String menuId) {
		return accessibleViewList.contains(menuId);
	}

	public List<String> getAccessibleViewList() {
		return accessibleViewList;
	}

	public boolean removeAccessibleView(String menuId) {
		return accessibleViewList.remove(menuId);
	}
}
