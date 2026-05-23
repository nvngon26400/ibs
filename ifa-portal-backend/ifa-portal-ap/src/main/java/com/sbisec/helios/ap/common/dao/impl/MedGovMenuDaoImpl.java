package com.sbisec.helios.ap.common.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.MedGovMenuDao;
import com.sbisec.helios.ap.common.dao.mapper.MedGovMenuMapper;
import com.sbisec.helios.ap.common.model.MedGovMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedGovMenuDaoImpl implements MedGovMenuDao {

	private static final Logger logger = LoggerFactory.getLogger(MedGovMenuDaoImpl.class);

	@Autowired
	private MedGovMenuMapper mapper;

	@Override
	public MedGovMenu getMedGovMenu(String userId, String menuId) throws Exception {
		return mapper.getMedGovMenu(userId, menuId);
	}

	@Override
	public List<MedGovMenu> getMedGovMenuList(String userId) throws Exception {
		return mapper.getMedGovMenuList(userId);
	}

	@Override
	public int insertMedGovMenu(MedGovMenu medGovMenu) throws Exception {
		return mapper.insertMedGovMenu(medGovMenu);
	}

	@Override
	public int deleteMedGovMenu(MedGovMenu medGovMenu) throws Exception {
		return mapper.deleteMedGovMenu(medGovMenu);
	}
}
