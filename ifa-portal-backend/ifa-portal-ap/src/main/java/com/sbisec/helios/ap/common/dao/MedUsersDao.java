package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.MedUsers;


public interface MedUsersDao {

	public MedUsers getMedUsers(String userId) throws Exception;
//	public List<MedUsers> getMedUsersList() throws Exception;
//	public List<MedUsers> getMedUsersList(String userId, String bbName, String euName) throws Exception;
//	public int insertMedUsers(MedUsers medUsers) throws Exception;
//	public int updateMedUsers(MedUsers medUsers) throws Exception;
//	public int deleteMedUsers(MedUsers medUsers) throws Exception;
	public Integer updateMedUsersPassword(String userId, String password) throws Exception;
}
