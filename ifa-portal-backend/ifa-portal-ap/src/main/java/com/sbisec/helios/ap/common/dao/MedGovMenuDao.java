package com.sbisec.helios.ap.common.dao;

import java.util.List;

import com.sbisec.helios.ap.common.model.MedGovMenu;

public interface MedGovMenuDao {

	public MedGovMenu getMedGovMenu(String userId, String menuId) throws Exception;
	public List<MedGovMenu> getMedGovMenuList(String userId) throws Exception;
	public int insertMedGovMenu(MedGovMenu medGovMenu) throws Exception;
	public int deleteMedGovMenu(MedGovMenu medGovMenu) throws Exception;
}
