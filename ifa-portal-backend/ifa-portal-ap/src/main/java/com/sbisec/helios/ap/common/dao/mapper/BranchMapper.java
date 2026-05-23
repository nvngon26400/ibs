package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.model.Branch;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BranchMapper {

	public Branch getBranch(@Param("branchCode") String branchCode) throws Exception;

}
