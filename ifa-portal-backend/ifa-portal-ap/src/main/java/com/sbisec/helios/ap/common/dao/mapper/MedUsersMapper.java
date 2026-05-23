package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.model.MedUsers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MedUsersMapper {

	public MedUsers getMedUsers(@Param("userId") String userId) throws Exception;

/*
	public List<MedUsers> getMedUsersList() throws Exception;

	public List<MedUsers> getMedUsersListByCondition(@Param("userId") String userId,
													 @Param("bbName") String bbName,
													 @Param("euName") String euName) throws Exception;

	public int insertMedUsers(@Param("medUsers") MedUsers medUsers) throws Exception;

	public int updateMedUsers(@Param("medUsers") MedUsers medUsers) throws Exception;

	public int deleteMedUsers(@Param("medUsers") MedUsers medUsers) throws Exception;
	*/
	public Integer updateMedUsersPassword(@Param("userId") String userId, @Param("password") String password) throws Exception;

}
