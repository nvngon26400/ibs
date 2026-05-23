package com.sbisec.helios.ap.common.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.dao.MedUsersDao;
import com.sbisec.helios.ap.common.dao.mapper.MedUsersMapper;
import com.sbisec.helios.ap.common.model.MedUsers;


@Component
public class MedUsersDaoImpl implements MedUsersDao {

	private static final Logger logger = LoggerFactory.getLogger(MedUsersDaoImpl.class);

	@Autowired
	protected MedUsersMapper mapper;

	@Override
	public MedUsers getMedUsers(String userId) throws Exception {
		return mapper.getMedUsers(userId);
	}
/*
	@Override
	public List<MedUsers> getMedUsersList() throws Exception {
		return mapper.getMedUsersList();
	}

	@Override
	public List<MedUsers> getMedUsersList(String userId, String bbName, String euName) throws Exception {
		return mapper.getMedUsersListByCondition(userId, bbName, euName);
	}

	@Override
	public int insertMedUsers(MedUsers medUsers) throws Exception {
		return mapper.insertMedUsers(medUsers);
	}

	@Override
	public int updateMedUsers(MedUsers medUsers) throws Exception {
		return mapper.updateMedUsers(medUsers);
	}

	@Override
	public int deleteMedUsers(MedUsers medUsers) throws Exception {
		return mapper.deleteMedUsers(medUsers);
	}
*/

	@Override
	public Integer updateMedUsersPassword(String userId, String password) throws Exception {
		password = StringUtil.emptyToNull(password);
		return mapper.updateMedUsersPassword(userId, password);
	}
}
