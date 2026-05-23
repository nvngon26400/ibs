package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.service.Service;

//import com.sbibits.horus.ap.common.model.MedMenuPriv;
//import com.sbibits.horus.ap.common.model.MedUsers;
//import com.sbibits.horus.ap.common.model.UserPermissionInfo;

public interface UserAdministrationService extends Service {

	public UserAccount getUserAccount(
	String userIdi, AuthCtrlListExclusionSettings settings 
	) throws Exception;
//	public UserPermissionInfo getUserPermissionInfo(
//	String userId
//	) throws Exception;
//	public List<MedMenuPriv> getDefaultMedMenuPrivList(
//	) throws Exception;
//
//	public boolean addUserAccount(
//	MedUsers medUsers, List<String> accessibleMenuIdList
//	) throws Exception;
//	public boolean updateUserAccount(
//	MedUsers medUsers, List<String> accessibleMenuIdList
//	) throws Exception;
//	public boolean deleteUserAccount(
//	MedUsers medUsers
//	) throws Exception;
//	
	public Integer updateMedUsersPassword(
    		String userId, String password
    		) throws Exception;
}
